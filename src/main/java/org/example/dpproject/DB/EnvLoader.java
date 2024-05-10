package org.example.dpproject.DB;

import org.example.dpproject.app.Models.AbsolutePath;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        String absolutePath = AbsolutePath.getPath(EnvLoader.class);

        try (FileInputStream fis = new FileInputStream(absolutePath + "/.env")) {
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
