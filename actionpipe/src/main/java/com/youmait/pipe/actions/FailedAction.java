package com.youmait.pipe.actions;

import com.youmait.pipe.pipe.Pipe;


public class FailedAction extends AbstractAction {

    private String message;
    public FailedAction(Pipe pipe, String errorMessage) {
        super(pipe);
        this.message = errorMessage;
    }

    @Override
    public void run() {
        pipe.log("Hub finished with Error : " + getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
