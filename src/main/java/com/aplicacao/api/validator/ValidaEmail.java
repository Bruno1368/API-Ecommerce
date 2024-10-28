package com.aplicacao.api.validator;

import java.util.regex.Pattern;

public class ValidaEmail {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email){
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
