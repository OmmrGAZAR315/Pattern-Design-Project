package org.example.dpproject.DB;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QBResults {
    private Map<String, List<Map<String, Object>>> results;

    public QBResults(Map<String, List<Map<String, Object>>> results) {
        this.results = results;
    }

    public QBResults() {
        this.results = new HashMap<>();
        this.results.put("results", null);
        this.results.put("messages", new ArrayList<>());
        results.get("messages").add(new HashMap<>());
        results.get("messages").get(0).put("status_code", 500);
        results.get("messages").get(0).put("message", "No operations allowed after connection closed.");
    }

    public Map<String, Object> first() {
        if (results.get("results") != null && !results.get("results").isEmpty())
            return results.get("results").get(0);

        return null;
    }

    public Map<String, Object> last() {

        if (results.get("results") != null && !results.get("results").isEmpty())
            return results.get("results").get(results.get("results").size() - 1);

        return null;
    }

    public List<Map<String, Object>> all() {

        if (results.get("results") != null && !results.get("results").isEmpty())
            return results.get("results");

        return null;
    }

    public int getStatusCode() {
        if (results.get("messages") != null)
            return (int) results.get("messages").get(0).get("status_code");
        else return 0;
    }

    public Object getMessage() {
        if (results.get("messages") != null)
            return results.get("messages").get(0).get("message");
        else return null;
    }


    public QBResults setCustom_message(String customErrorMessage) {
        if (results.get("messages") != null) {
            results.get("messages").get(0).put("custom_error_message", customErrorMessage);
            return this;
        }
        return null;
    }

    public Object getCustom_message() {
        if (results.get("messages") != null)
            return results.get("messages").get(0).get("custom_error_message");
        else return null;
    }

    public QBResults setStatus_code(int customErrorMessage) {
        if (results.get("messages") != null) {
            results.get("messages").get(0).put("status_code", customErrorMessage);
            return this;
        }
        return null;
    }
}
