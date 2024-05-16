package org.example.dpproject.app.Proxy;


import java.util.List;
import java.util.Map;

public interface Cacheable {
    public List<Object> getCache();

    public Map<Integer, Object> getMapCache();

}

