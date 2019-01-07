package com.cyb.chat.util;

public class IPUtil {
    public static boolean isIP(String ip) {
        String[] s = ip.split("\\.");
        if (s.length != 4) {
            return false;
        }
        try {
            for (String s1 : s) {
                if (Integer.valueOf(s1) > 255 || Integer.valueOf(s1) < 0) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
