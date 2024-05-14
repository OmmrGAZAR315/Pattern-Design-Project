package org.example.dpproject.app.Helpers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dpproject.DB.QBResults;
import org.example.dpproject.app.Http.Responses.Responses;
import org.example.dpproject.app.Models.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class HelperClass {
    public static <T> T[] convertListMapToArray(Class<T> clazz, List<Map<String, Object>> list) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, list.size());
        try {
            Constructor<T> constructor = clazz.getConstructor(Map.class);
            for (int i = 0; i < list.size(); i++) {
//                System.out.println("list.get(" + i + ") " + list.get(i));
                array[i] = constructor.newInstance(list.get(i));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void login(HttpServletRequest request, HttpServletResponse resp, QBResults qbResults) {
        Responses userResponse = new Responses();
        if (qbResults.getStatusCode() == HttpResponse.OK.getCode())
            userResponse = new Responses() {
                @Override
                public void anonymousFunctionInSuccessCase() {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", new User(qbResults.first()));
                    session.setAttribute("authenticated", true);
                }
            };
        userResponse
                .forwardInSuccess("home.jsp")
                .dispatch(request, resp, qbResults, "login", HttpResponse.OK.getCode());

    }

}
