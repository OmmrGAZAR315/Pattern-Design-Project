package org.example.socialmedia_proxy.DB_CRUD.Builder;

import org.example.socialmedia_proxy.QueryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public static String query;
    public static QueryType queryType;
    public static boolean isCallParameterSet;
    public static boolean isInsertColumnSet;
    public static boolean isInsertParameterSet;
    public static boolean isWhereSet;
    public static String tableName;
    //    static Map<String, String> values = new HashMap<>();
//    static Map<String, String> dataTypes = new HashMap<>();
    public static List<Object> parameters = new ArrayList<>();
    public static Map<String, Object[]> importedData = new HashMap<>();
    public static void resetBooleans() {
        isCallParameterSet = false;
        isInsertColumnSet = false;
        isInsertParameterSet = false;
        isWhereSet = false;
    }
}
