package org.example.dpproject.DB.Builder;

import org.example.dpproject.DB.QueryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public static String query;
    public static QueryType queryType;
    public static boolean isCallParameterSet;
    public static boolean isInsertColumnSet;
    public static boolean isParameterSet;
    public static boolean isWhereSet;
    public static boolean selectAll;
    public static List<String> columns= new ArrayList<>();
    public static List<Object> parameters = new ArrayList<>();
    public static String tableName;
    static Map<String, String> insertData = new HashMap<>();
    //    static Map<String, String> dataTypes = new HashMap<>();
    public static Map<String, List<Map<String, Object>>> importedData = new HashMap<>();

    public static void resetBooleans() {
        isCallParameterSet = false;
        isInsertColumnSet = false;
        isParameterSet = false;
        isWhereSet = false;
        selectAll = false;
    }
}
