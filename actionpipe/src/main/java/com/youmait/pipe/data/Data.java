package com.youmait.pipe.data;

public class Data  <T> {
    private String key;
    private T object;

    public Data(String key, T object) {
        this.key = key;
        this.object = object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

}
