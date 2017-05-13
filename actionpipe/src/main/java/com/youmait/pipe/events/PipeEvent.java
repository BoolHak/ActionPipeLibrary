package com.youmait.pipe.events;

public class PipeEvent {

    public static final int STARTED = 1;
    public static final int FINISHED = 2;
    public static final int FAILED = 3;


    private String message = "No Message Set";
    private int key = -1;
    private Object data = null;

    public PipeEvent(int key) {
        this.key = key;
    }

    public PipeEvent(int key, String message) {
        this.message = message;
        this.key = key;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void resetEvent(){
        message = "No Message Set";
        data = null;
    }
}
