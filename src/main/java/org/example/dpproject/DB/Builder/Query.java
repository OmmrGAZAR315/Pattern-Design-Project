package org.example.dpproject.DB.Builder;

import org.example.dpproject.DB.QueryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public  String query;
    public  QueryType queryType;
    public  boolean isCallParameterSet;
    public  boolean isInsertColumnSet;
    public  boolean isParameterSet;
    public  boolean isWhereSet;
    public  boolean selectAll;
    public  List<String> columns= new ArrayList<>();
    public  List<Object> parameters = new ArrayList<>();
    public  String tableName;
    public  Map<String, List<Map<String, Object>>> importedData = new HashMap<>();

}
