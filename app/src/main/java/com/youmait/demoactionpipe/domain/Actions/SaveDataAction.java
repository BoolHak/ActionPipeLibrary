package com.youmait.demoactionpipe.domain.Actions;

import com.youmait.demoactionpipe.domain.Keys;
import com.youmait.pipe.actions.SimpleAction;
import com.youmait.pipe.pipe.Pipe;

import io.paperdb.Paper;

public class SaveDataAction extends SimpleAction {

    public SaveDataAction(Pipe pipe) {
        super(pipe);
    }

    @Override
    public void run() {
        broadcastMessage("Saving data...", Keys.EVENT_SHOW_MESSAGE);
        int result = getData(Keys.MATH_RESULT);
        Paper.book().write(Keys.DATA_RESULT,result);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            pipe.log(e.getMessage());
            failure();
        }
        success();
    }
}
