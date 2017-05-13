package com.youmait.pipe.data;

import java.util.List;

public interface DataContainable {
    void setData(String key, Data object);
    Data getData(String key);
    List<String> getAllKey();
}
