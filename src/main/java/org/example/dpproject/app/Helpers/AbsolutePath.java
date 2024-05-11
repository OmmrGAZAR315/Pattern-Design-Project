package org.example.dpproject.app.Helpers;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class AbsolutePath {
    public static String getPath(Class<?> clazz) {
        String absolutePath = clazz.getResource(".").getPath();
        String decodedPath = URLDecoder.decode(absolutePath, StandardCharsets.UTF_8);

        return decodedPath.substring(0, absolutePath.indexOf("/target/"));
    }

}
