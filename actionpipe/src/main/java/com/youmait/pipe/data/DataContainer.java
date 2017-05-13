package com.youmait.pipe.data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContainer  implements DataContainable {

    private Map<String, Data> map = new HashMap<>();


    @Override
    public void setData(String key, Data object) {
        map.put(key,object);
    }

    @Override
    public Data getData(String key) {
        if(map.containsKey(key))
            if(map.get(key) != null) return map.get(key);
        return new Empty();
    }

    @Override
    public List<String> getAllKey() {
        return new ArrayList<>(map.keySet());
    }
}