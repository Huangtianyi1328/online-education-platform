package com.huangtianyi.commonutils;

public class CodeUtil {
    public static String sendCodeByShortMessage(String phoneNum){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random()*10);
            builder.append(random);
        }
        String code = builder.toString();
        return code;

    }
}
