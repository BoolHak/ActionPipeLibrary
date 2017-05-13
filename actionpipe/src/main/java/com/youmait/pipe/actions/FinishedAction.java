package com.youmait.pipe.actions;

import com.youmait.pipe.pipe.Pipe;

public class FinishedAction extends SimpleAction {


    public FinishedAction(Pipe pipe) {
        super(pipe);
    }

    @Override
    public void run() {
        pipe.log("Hub Progress finished");

    }
}
