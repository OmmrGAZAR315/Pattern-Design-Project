package org.example.dpproject.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\omara\\IdeaProjects\\DPProject\\.env")) {
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
