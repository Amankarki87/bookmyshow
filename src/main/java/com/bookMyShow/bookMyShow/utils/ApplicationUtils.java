package com.bookMyShow.bookMyShow.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationUtils {
    public static boolean emailValidation(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
