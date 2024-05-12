package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.Builder.Builder;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Http.DTOs.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRepository {
    private final String table;

    public UserRepository(String table) {
        this.table = table;
    }

    public Map<String, Object> deleteUser(UserDto dto) {
        return new QueryBuilder()
                .table(table)
                .delete()
                .whereId(dto.getId())
                .build()
                .getMessages();
    }

    public Map<String, Object> updateUser(UserDto dto) {
        // Get the columns from the dto
        String[] columns = new String[dto.toMap().size()];
        List<String> columnsList = new ArrayList<>(dto.toMap().keySet());
        columns = columnsList.toArray(columns);

        QueryBuilder query = new QueryBuilder()
                .table(table)
                .update(columns);
        dto.toMap().values().forEach(query::setParameter);
        query.where("id", dto.getId())
                .build();
        return query.getMessages();
    }

    public  Map<String, Object> getUserById(String id) {
        return new QueryBuilder()
                .table(table)
                .select("secretKey")
                .where("id", id)
                .build()
                .first();
    }
}
