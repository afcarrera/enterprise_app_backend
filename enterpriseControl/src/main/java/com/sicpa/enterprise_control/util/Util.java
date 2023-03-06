package com.sicpa.enterprise_control.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util{
	private Util (){}
    public static Date getCurrentDate(){
        long millis = System.currentTimeMillis();
        return new Date(millis);
    }
	
    public static String validateLetters(String string){
        if (string == null || string.length() == 0){
            return null;
        }
        string = string.trim();
        string = string.replaceAll(" +", " ");
        string = string.toUpperCase();
        Pattern pattern = Pattern
                .compile("^[A-Z"
                        + "Ñ"
                        + "ÁÉÍÓÚ"
                        + "ÀÈÌÒÙ"
                        + "ÄËÏÖÜ"
                        + "']"
                        + "+( {1}[A-Z"
                        + "Ñ"
                        + "ÁÉÍÓÚ"
                        + "ÀÈÌÒÙ"
                        + "ÄËÏÖÜ"
                        + "']*){0,}+$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return string;
        }
        return null;
    }
	
    public static String validatePhoneNumber(String string){
        if (string == null || string.length() == 0){
            return null;
        }
        string = string.trim();
        Pattern pattern = Pattern
                .compile("^\\d{7,10}+$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return string;
        }
        return null;
    }

    public static String validateEmail(String email){
        if (email == null || email.length() == 0){
            return null;
        }
        Pattern pattern = Pattern.compile("^([a-zA-Z\\d_.\\-])+@(([a-zA-Z\\d\\-])+\\.)"
                + "++([a-zA-Z\\d]{2,4})$");
        Matcher mather = pattern.matcher(email);
        if(Boolean.TRUE.equals(mather.find())){
            return email;
        }
        return null;
    }
}
