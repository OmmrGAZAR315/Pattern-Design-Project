package org.example.dpproject.DB;

import org.example.dpproject.app.Helpers.HttpResponse;

import java.util.List;
import java.util.Map;

public class QBResults {
    private Map<String, List<Map<String, Object>>> results;

    public QBResults(Map<String, List<Map<String, Object>>> results) {
        this.results = results;
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
        return (int) results.get("messages").get(0).get("status_code");
    }

    public Object getMessage() {
        return results.get("messages").get(0).get("message");
    }

    public QBResults setMessage(HttpResponse httpResponse) {
        results.get("messages").get(0).put("status_code", httpResponse.getCode());
        results.get("messages").get(0).put("message", httpResponse.getMessage());
        return this;
    }

    public QBResults setCustom_message(String customErrorMessage) {
        results.get("messages").get(0).put("custom_error_message", customErrorMessage);
        return this;
    }

    public Object getCustom_message() {
        return results.get("messages").get(0).get("custom_error_message");
    }
}
