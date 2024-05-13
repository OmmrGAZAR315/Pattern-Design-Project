package org.example.dpproject.app.Repositories;

import org.example.dpproject.DB.QBResults;
import org.example.dpproject.DB.QueryBuilder;
import org.example.dpproject.app.Http.DTOs.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserRepository {
    private final String table;
    private String[] pluck = {"*"};

    public UserRepository(String table) {
        this.table = table;
    }

    public UserRepository pluck(String... pluck) {
        this.pluck = pluck;
        return this;
    }

    public QBResults deleteUser(UserDto dto) {
        return new QueryBuilder()
                .table(table)
                .delete()
                .whereId(dto.getId())
                .build();
    }

    public QBResults updateUser(UserDto dto) {
        // Get the columns from the dto
        String[] columns = new String[dto.toMap().size()];
        List<String> columnsList = new ArrayList<>(dto.toMap().keySet());
        columns = columnsList.toArray(columns);

        QueryBuilder query = new QueryBuilder()
                .table(table)
                .update(columns);
        dto.toMap().values().forEach(query::setParameter);
        query.where("id", dto.getId());
        return query.build();
    }

    public QBResults getUserBy(String column, String value) {
        return new QueryBuilder()
                .table(table)
                .select(pluck)
                .where(column, value)
                .build();
    }

}
