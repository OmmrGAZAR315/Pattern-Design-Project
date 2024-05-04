package org.example.dpproject.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        String absolutePath = EnvLoader.class.getResource(".").getPath();

        try (FileInputStream fis = new FileInputStream(absolutePath.substring(0, absolutePath.indexOf("/target/")) + "/.env")) {
            Properties properties = new Properties();
            properties.load(fis);
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                System.setProperty(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
