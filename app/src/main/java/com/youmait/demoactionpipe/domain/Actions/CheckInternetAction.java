package com.youmait.demoactionpipe.domain.Actions;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.youmait.demoactionpipe.MyApplication;
import com.youmait.demoactionpipe.domain.Keys;
import com.youmait.pipe.actions.RetryAction;
import com.youmait.pipe.pipe.Pipe;

public class CheckInternetAction extends RetryAction {

    public CheckInternetAction(Pipe pipe) {
        super(pipe);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            pipe.log(e.getMessage());
            retry();
        }
        success();
    }
}
