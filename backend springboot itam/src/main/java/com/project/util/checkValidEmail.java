package com.project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkValidEmail {

    public static boolean isValidEmail(String email) {

        String emailRegex = "^\"?[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}\"?$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
