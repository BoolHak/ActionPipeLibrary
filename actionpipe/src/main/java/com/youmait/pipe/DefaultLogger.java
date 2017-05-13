package com.youmait.pipe;

public class DefaultLogger implements PipeLogger{

    @Override
    public void log(String message) {
        System.out.print(message);
    }

    @Override
    public void log(String tag, String message) {
        System.out.println(tag + ":" + message);
    }
}
