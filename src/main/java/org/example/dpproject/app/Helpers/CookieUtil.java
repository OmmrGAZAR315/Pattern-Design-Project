package org.example.dpproject.app.Helpers;

import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dpproject.app.Models.Post;

public class CookieUtil {

    public static void setObjectAsCookie(Post obj, HttpServletResponse response, String cookieName) {
        Gson gson = new Gson();
        System.out.println("Setting cookie "+obj);
        String json = gson.toJson(obj);
        Cookie cookie = new Cookie(cookieName, json);
        response.addCookie(cookie);
    }

    public static String getObjectFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            System.out.println("Cookies found");
            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(cookieName)) {
//                    return cookie.getValue();
//                }
                System.out.println("cookie name: " + cookie.getName() + " cookie value: " + cookie.getValue());
            }
        }
        System.out.println("No cookie found");
        return null;
    }
}
