package com.utility;

import com.constans.Env;

import java.io.*;
import java.util.Properties;

public final class PropertiesUtil {

    private PropertiesUtil() {
    }

    public static String readProperty(Env env, String propertyName) {
        String path = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + env.name().toLowerCase() + ".properties";

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + path, e);
        }

        String value = properties.getProperty(propertyName.toUpperCase());

        if (value == null) {
            throw new RuntimeException(
                    "Property '" + propertyName + "' not found in " + path);
        }
        return value.trim();
    }
}
