package org.example.dpproject.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        String absolutePath = EnvLoader.class.getResource(".").getPath();
        String decodedPath = URLDecoder.decode(absolutePath, StandardCharsets.UTF_8);

        try (FileInputStream fis = new FileInputStream(decodedPath.substring(0, absolutePath.indexOf("/target/")) + "/.env")) {
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
