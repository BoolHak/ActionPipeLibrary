package com.youmait.demoactionpipe.domain.Actions;

import com.youmait.demoactionpipe.domain.Keys;
import com.youmait.pipe.actions.SimpleAction;
import com.youmait.pipe.pipe.Pipe;

public class MathOperationAction extends SimpleAction {

    public MathOperationAction(Pipe pipe) {
        super(pipe);
    }

    @Override
    public void run() {
        broadcastMessage("Doing a complex math operation...", Keys.EVENT_SHOW_MESSAGE);
        int x = 5;
        int y = 6;
        int result = x + y;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            pipe.log(e.getMessage());
            failure();
        }
        sendData(Keys.MATH_RESULT, result);
        success();
    }
}
