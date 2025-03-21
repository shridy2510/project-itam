package com.project.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.displayDto.UserDisplayDto;
import com.project.dto.TokenDto;
import com.project.dto.UserDto;
import com.project.repository.RoleRepository;
import com.project.repository.entities.RoleEntity;
import com.project.repository.entities.UserEntity;
import com.project.security.KeycloakUtilSecurity;
import com.project.repository.UserRepository;
import com.project.util.Mapper;
import com.project.util.checkValidEmail;
import jakarta.transaction.Transactional;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.project.util.Mapper.convertRolesToRoleNames;

@Service
public class UserService {
    @Value("${realm}")
    private String realm;
    @Value("${server-url}")
    private String serverUrl;
    @Value("${client-id}")
    private String clientId;
    @Value("${grant-type}")
    private String grantType;
    @Value("${avatar_Path}")
    private String avatarPath;




    @Autowired
    private KeycloakUtilSecurity keycloakUtilSecurity;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;



    @Transactional
    public void updateUser(UserDto userDto) {
        //update keycloak
        try{
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        UserRepresentation userRepresentation = new UserRepresentation();
        if(userDto.getEmail() != null) {userRepresentation.setEmail(userDto.getEmail());}
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        if(userDto.getUserName()!=null) {userRepresentation.setUsername(userDto.getUserName());}
        List<CredentialRepresentation> credentials = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        if(userDto.getPassword() != null) {
            cred.setTemporary(false);
            cred.setValue(userDto.getPassword());
            credentials.add(cred);
            userRepresentation.setCredentials(credentials);}
        userRepresentation.setEnabled(true);
        keycloak.realm(realm).users().get(userDto.getUserId()).update(userRepresentation);}
        catch(Exception e ){
            System.out.println(e.getMessage());

        }

        //updateDb
        Optional<UserEntity> optionalUser = userRepository.findByUserId(userDto.getUserId());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            // Cập nhật các trường của user với dữ liệu từ userDto
            if(userDto.getUserName()!=null){
            userEntity.setUsername(userDto.getUserName());}
            if(userDto.getEmail()!=null){userEntity.setEmail(userDto.getEmail());}
            userEntity.setLastName(userDto.getLastName());
           userEntity.setFirstName(userDto.getFirstName());
           userEntity.setPhoneNumber(userDto.getPhoneNumber());
            if(userDto.getRoles()!=null){
                Set<RoleEntity> roles = roleRepository.findByRoleIn(userDto.getRoles());
                userEntity.setRoles(roles);}
            // Lưu lại cập nhật vào cơ sở dữ liệu
            userRepository.save(userEntity);
        } else {
            // Xử lý khi không tìm thấy người dùng với userId cụ thể
            throw new RuntimeException("User not found with userId: " + userDto.getUserId());
        }


    }
    public void updatePassword(UserDto userDto) {
        try {
            Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
            UserRepresentation userRepresentation = new UserRepresentation();
            List<CredentialRepresentation> credentials = new ArrayList<>();
            CredentialRepresentation cred = new CredentialRepresentation();

            // Check if the new password is provided and if the old password matches
            if (userDto.getPassword() != null ) {
                cred.setTemporary(false);
                cred.setValue(userDto.getPassword()); // Set the new password
                credentials.add(cred); // Add the new password to the credentials list
                userRepresentation.setCredentials(credentials); // Set the updated credentials
                userRepresentation.setEnabled(true); // Enable the user
                // Update the user in Keycloak
                keycloak.realm(realm).users().get(userDto.getUserId()).update(userRepresentation);
            } else {
                throw new IllegalArgumentException(" new password is not provided.");
            }
        } catch (Exception e) {
            // Log the error and rethrow as needed
            System.out.println("Error updating password: " + e.getMessage());
            // Optionally, throw a more specific exception if needed
            throw new RuntimeException("Password update failed.", e);
        }
    }




    @Transactional
    public void deleteUser(UserDto userDto) {
        //xoá keycloak
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        keycloak.realm(realm).users().delete(userDto.getUserId());
        //xoá db
        userRepository.deleteByUserId(userDto.getUserId());


    }
    @Transactional
    public void createUser(UserDto userDto) {
        //lập trong db
        //check trùng userName và Gmail
        if(userRepository.existsByUsername(userDto.getUserName())){
            throw new RuntimeException("UserName already exists");
        }
        if(userRepository.existsByEmail(userDto.getEmail()) ){
            throw new RuntimeException("Email already exists");
        }

        //lạp tk keycloak
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation newUser = new UserRepresentation();
        newUser.setEmail(userDto.getEmail());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setUsername(userDto.getUserName());
        newUser.setEnabled(true);
        List<CredentialRepresentation> credentials = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(userDto.getPassword());
        credentials.add(cred);
        newUser.setCredentials(credentials);
        usersResource.create(newUser);


        // lấy id vừa tạo
        UserRepresentation user=usersResource.search(userDto.getUserName()).get(0);
        //lưu vào db
        UserEntity userEntity=new UserEntity();
        Set<RoleEntity> setRoles=roleRepository.findByRoleIn(userDto.getRoles());
        userEntity.setUsername(userDto.getUserName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setUserId(user.getId());
        userEntity.setRoles(setRoles);
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(userEntity);


        System.out.println("UserEntity saved: " + userEntity);

        //assign role cho người dùng


        for(RoleEntity roleEntity:setRoles){
        RoleRepresentation role = keycloak.realm(realm).roles().get(roleEntity.getRole()).toRepresentation();
        usersResource.get(user.getId()).roles().realmLevel().add(Collections.singletonList(role));
        }


        // Lấy thông tin realm
        RealmResource realmResource = keycloak.realm(realm);

        // Lấy thông tin client dựa trên clientId
        List<ClientRepresentation> clients = realmResource.clients().findByClientId(clientId);
        if (clients.isEmpty()) {
            throw new IllegalArgumentException("Client not found");
        }
        ClientRepresentation clientRepresentation = clients.get(0);
        ClientResource clientResource = realmResource.clients().get(clientRepresentation.getId());
        for(RoleEntity roleEntity:setRoles){
        // Lấy thông tin về vai trò của client
        RoleRepresentation clientRole = clientResource.roles().get(roleEntity.getRole()).toRepresentation();

        // Lấy thông tin người dùng
        UserResource userResource = usersResource.get(user.getId());

        // Gán vai trò cho người dùng ở cấp độ client
        userResource.roles().clientLevel(clientRepresentation.getId()).add(Collections.singletonList(clientRole));}

        System.out.println("Role added to user for client");
    }


    public List<UserDto> getUserListFromKeycloak() {
        Keycloak keycloak = keycloakUtilSecurity.KeycloakInstance();
        List<UserRepresentation> userRepresentation = keycloak.realm(realm).users().list();

        List<UserDto> userDtos = new ArrayList<>();
        if (userRepresentation != null) {
            for (UserRepresentation userRep : userRepresentation) {
                UserDto userDto = new UserDto();
                userDto.setUserId(userRep.getId());
                userDto.setUserName(userRep.getUsername());
                userDto.setEmail(userRep.getEmail());
                userDto.setFirstName(userRep.getFirstName());
                userDto.setLastName(userRep.getLastName());
                userDtos.add(userDto);
            }

        }
        return userDtos;
    }

    public List<UserDisplayDto> getUserListFromDb() {
        List<UserEntity> userEntities = userRepository.findAll();
        return  userEntities.stream().map(Mapper::toUserDisplayDto).collect(Collectors.toList());

    }

    public UserDisplayDto getUserInfoFromDb(String input) {
        boolean validEmail = checkValidEmail.isValidEmail(input);
        Optional<UserEntity> response;

        if (validEmail) {
            response = userRepository.findByEmail(input);
        } else {
            response = userRepository.findByUsername(input);
        }

        if (response.isPresent()) {
            UserEntity userEntity = response.get();

            UserDisplayDto userDto = new UserDisplayDto();
            userDto.setUserId(userEntity.getUserId());
            userDto.setFirstName(userEntity.getFirstName());
            userDto.setLastName(userEntity.getLastName());
            userDto.setEmail(userEntity.getEmail());
            userDto.setPhoneNumber(userEntity.getPhoneNumber());
            userDto.setId(userEntity.getId());
            userDto.setUserName(userEntity.getUsername());
            userDto.setRoles(convertRolesToRoleNames(userEntity.getRoles()));



            return userDto;
        }
        System.out.println("có vấn đề");
        return null ;}


        public UserDisplayDto getUserInfoById(String userId) {

            Optional<UserEntity> response;
            response = userRepository.findByUserId(userId);
            if (response.isPresent()) {
                UserEntity userEntity = response.get();

                UserDisplayDto userDto = new UserDisplayDto();
                userDto.setUserId(userEntity.getUserId());
                userDto.setFirstName(userEntity.getFirstName());
                userDto.setLastName(userEntity.getLastName());
                userDto.setEmail(userEntity.getEmail());
                userDto.setPhoneNumber(userEntity.getPhoneNumber());
                userDto.setId(userEntity.getId());
                userDto.setUserName(userEntity.getUsername());
                userDto.setRoles(convertRolesToRoleNames(userEntity.getRoles()));

                return userDto;
            }
            System.out.println("có vấn đề");
            return null ;


    }





    public ResponseEntity<?> login(UserDto userDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", grantType);
        map.add("username", userDto.getUserName());
        map.add("password", userDto.getPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            String accessToken = root.path("access_token").asText();
            String refreshToken = root.path("refresh_token").asText();
            Map<String, String> tokenResponse = new HashMap<>();
            tokenResponse.put("access_token", accessToken);
            tokenResponse.put("refresh_token", refreshToken);

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error extracting access token");
        }
    }
    public ResponseEntity<?> logout(TokenDto tokenDto) {

        RestTemplate restTemplate = new RestTemplate();
        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/logout";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("refresh_token",tokenDto.getRefresh_token());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        restTemplate.postForEntity(url, request, Object.class);
        return ResponseEntity.ok().build();

    }

    public void saveAvatar(String userId, MultipartFile file) throws IOException {
        //save file
        String filename = userId + ".png";
        Path filePath= Paths.get(avatarPath,filename);
        file.transferTo(new File(filePath.toString()));
        String fileLocation=filePath.toString();
        //save path
        Optional<UserEntity> user=userRepository.findByUserId(userId);
        if(user.isPresent()) {
            user.get().setAvatarPath(fileLocation);
            userRepository.save(user.get());
        }
    }
    public String getAvatarPath(String userId) {
        Optional<UserEntity> user=userRepository.findByUserId(userId);
        if(user.isPresent()) {
            return user.get().getAvatarPath();

        }
        return null;
    }
    public long getTotalUser(){
        return userRepository.count();
    }





}



