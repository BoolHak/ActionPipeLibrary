package com.youmait.pipe.actions;


import com.youmait.pipe.data.Data;
import com.youmait.pipe.events.PipeEvent;
import com.youmait.pipe.pipe.Pipe;

public abstract class AbstractAction implements Action {

    protected Pipe pipe;
    private Action nextAction;
    private Action failedAction;
    private int progress = -1;

    public AbstractAction(Pipe pipe){
        this.pipe = pipe;
    }


    @Override
    public void success() {
        if(nextAction == null){
            pipe.setNextAction(new FailedAction(pipe, "No next action set!"));
            return;
        }
        if(progress != -1) pipe.broadcastProgress(progress);
        pipe.setNextAction(nextAction);
    }

    @Override
    public void failure() {
        if(failedAction == null){
            pipe.setNextAction(new FailedAction(pipe, "No failed action set!"));
            return;
        }
        pipe.setNextAction(failedAction);
    }

    @Override
    public void stop() {
        //todo: stop the process
    }

    @Override
    public void setNextAction(Action action) {
        this.nextAction = action;
    }

    @Override
    public void setOnFailAction(Action action) {
        this.failedAction = action;
    }

    public Action getNextAction() {
        return nextAction;
    }

    public Action getFailedAction() {
        return failedAction;
    }


    public AbstractAction next(Action action){
        this.nextAction = action;
        return this;
    }

    public AbstractAction progress(int value){
        this.progress = value;
        return this;
    }

    public AbstractAction failed(Action action){
        this.failedAction = action;
        return this;
    }

    public AbstractAction failed(String message){
        this.failedAction = new FailedAction(pipe,message);
        return this;
    }

    public AbstractAction isFinal(){
        this.nextAction = new FinishedAction(pipe);
        return this;
    }

    public <T> T getData(String key){
        Data data = pipe.getData(key);
        Object object = data.getObject();
        try {
            return (T) object;
        }catch (ClassCastException exp){
            return null;
        }

    }

    protected void broadcastMessage(String message,int key) {
        PipeEvent event = new PipeEvent(key,message);
        pipe.broadcastEvent(event);
    }

    public void sendData(String key, Object value){
        pipe.setData(new Data(key,value));
    }

}
