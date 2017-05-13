package com.youmait.demoactionpipe.domain.Actions;


import com.youmait.demoactionpipe.domain.Keys;
import com.youmait.pipe.actions.RetryAction;
import com.youmait.pipe.actions.SimpleAction;
import com.youmait.pipe.pipe.Pipe;

public class WebServiceAction extends RetryAction {

    public WebServiceAction(Pipe pipe) {
        super(pipe);
    }

    @Override
    public void run() {
        broadcastMessage("Getting important data from the server...", Keys.EVENT_SHOW_MESSAGE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            pipe.log(e.getMessage());
            retry();
        }
        success();
    }
}
