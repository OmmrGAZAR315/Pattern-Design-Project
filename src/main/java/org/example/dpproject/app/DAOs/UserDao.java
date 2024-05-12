package org.example.dpproject.app.DAOs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Models.UserProfile;


import java.util.Map;

public class UserDao {

    public String retrieveUserId(HttpServletRequest request) {


        QueryBuilder query2 = new QueryBuilder();
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserProfile user = (UserProfile) session.getAttribute("user");
            if (user != null) {
                String username = user.getUsername();

                Map<String, Object> result = query2.table("users")
                        .select("id")
                        .where("username", username)
                        .build()
                        .first();

                if (result != null && result.containsKey("id")) {

                    String userId = String.valueOf(result.get("id"));
                    return userId;
                } else {
                    System.out.println(username + " not found");
                }
            } else {
                System.out.println("User is not logged in");
            }
        } else {
            System.out.println("no session is found");
        }
        return null;
    }
}
