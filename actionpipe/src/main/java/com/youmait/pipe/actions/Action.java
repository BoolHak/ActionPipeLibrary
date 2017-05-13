package com.youmait.pipe.actions;


public interface Action {
    interface OnActionStarted{
        void onStart();
    }
    interface OnActionSuccess{
        void onSuccess();
    }
    interface OnActionFailed{
        void onFailed();
    }
    interface OnActionStopped{
        void onStopped();
    }

    void run();
    void success();
    void failure();
    void stop();
    void setNextAction(Action action);
    void setOnFailAction(Action action);
}
