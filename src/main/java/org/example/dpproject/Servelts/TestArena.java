package org.example.dpproject.Servelts;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dpproject.app.Helpers.CookieUtil;
import org.example.dpproject.app.Models.Post;


@WebServlet("/testing")
public class TestArena extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        String postStr = CookieUtil.getObjectFromCookie(request, "lastPost");
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
    }
}
