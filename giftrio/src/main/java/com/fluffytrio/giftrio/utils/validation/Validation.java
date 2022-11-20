package com.fluffytrio.giftrio.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean isValidEmail(String email){
        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
