package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QueryBuilder;

import java.util.List;
import java.util.Map;

public class UserRepository {
    public Map<String, Object> deleteUser(int id) {
       return new QueryBuilder()
                .table("users")
                .delete()
                .whereId(id)
                .build()
                .first();
    }
}
