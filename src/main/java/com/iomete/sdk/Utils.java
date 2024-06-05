package com.iomete.sdk;

public class Utils {
    public static String loadEnvironmentVariable(String variableName, String defaultValue) {
        String value = System.getenv(variableName);
        return value != null ? value : defaultValue;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
