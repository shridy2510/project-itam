package com.project.controller;
import com.project.displayDto.UserDisplayDto;
import com.project.dto.TokenDto;
import com.project.dto.UserDto;
import com.project.repository.UserRepository;
import com.project.repository.entities.UserEntity;
import com.project.service.UserService;
import com.project.util.CheckRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    //nhận  login, trả về thông tin user token
    @Value("${realm}")
    private String realm;
    @Value("${server-url}")
    private String serverUrl;
    @Value("${client-id}")
    private String clientId;
    @Value("${grant-type}")
    private String grantType;
    @Value("${clientSecret}")
    private String clientSecret;
    @Autowired
    private CheckRole checkRole;




    //login trả về accesstoken và refreshtoken
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto,HttpServletRequest request) {
        return userService.login(userDto);
    }

//lấy list user từ keycloak

    @GetMapping("/keycloak/users")
    @PreAuthorize("hasRole('Admin')")
//   @PreAuthorize("hasRole('User')or hasRole('Admin')")
    public ResponseEntity<List< UserDto>> getUser(HttpServletRequest request){
        if(checkRole.checkRoleAdmin(request)) {

        List<UserDto> userDto = userService.getUserListFromKeycloak();
        return ResponseEntity.ok(userDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    //lấy list user từ db
    @GetMapping("/list/users")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<UserDisplayDto>> getUserList(HttpServletRequest request){
//        if(checkRole.checkRoleAdmin(request)) {

        List<UserDisplayDto> userEntity = userService.getUserListFromDb();
        return ResponseEntity.ok(userEntity);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }


    }
//tạo user mới
    @PostMapping("/create/user")
    @PreAuthorize("hasRole('Admin')")

    public ResponseEntity<?> createUser(@RequestBody UserDto userDto,HttpServletRequest request) {
//        if(checkRole.checkRoleAdmin(request)) {
            try{userService.createUser(userDto);
                return ResponseEntity.status(HttpStatus.CREATED).body("đã tạo thành công");}
            catch(Exception e){return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());}

//        }
//        else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Role");
//        }
    }
    //đổi thông tin user
    @PutMapping("/update/users")
    @PreAuthorize("hasRole('Admin') or hasRole('User')")


    public ResponseEntity<String> update(@RequestBody UserDto userDto, HttpServletRequest request) {


        userService.updateUser(userDto);
        return ResponseEntity.ok("Info updated successfully");


    }
    //xoá user
    @DeleteMapping("/delete/users")
    @PreAuthorize("hasRole('Admin')")
    //@PreAuthorize("hasRole('User')or hasRole('Admin')")
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto,HttpServletRequest request) {


        userService.deleteUser(userDto);
        return ResponseEntity.ok("User deleted successfully");


    }

    //logout keycloak session
    @PostMapping("/keycloak/logout")

    public ResponseEntity<?> logout(@RequestBody TokenDto tokenDto,HttpServletRequest request) {
        return userService.logout(tokenDto);


    }












}
