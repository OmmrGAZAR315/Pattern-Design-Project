package org.example.socialmedia_proxy.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        try (FileInputStream fis = new FileInputStream("D:\\College\\Projects\\Pattern-Design-Project-master\\Pattern-Design-Project-master\\.env")) {
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
