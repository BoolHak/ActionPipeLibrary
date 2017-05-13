package com.youmait.pipe.pipe;

import com.youmait.pipe.DefaultLogger;
import com.youmait.pipe.PipeLogger;
import com.youmait.pipe.actions.Action;
import com.youmait.pipe.actions.FailedAction;
import com.youmait.pipe.actions.FinishedAction;
import com.youmait.pipe.data.Data;
import com.youmait.pipe.data.DataContainer;
import com.youmait.pipe.events.PipeEvent;

public abstract class AbstractPipe implements Pipe {

    private Action currentAction;
    protected PipeLogger logger;
    protected boolean debug;
    protected OnProgressListener progressListener;
    private DataContainer dataContainer;

    public AbstractPipe(){
        logger = new DefaultLogger();
        dataContainer = new DataContainer();
        initActions();
        linkActions();
    }

    protected abstract void linkActions();

    protected abstract void initActions();

    @Override
    public Action getCurrentAction() {
        return currentAction;
    }

    public void startWithAction(Action action){
        broadcastEvent(new PipeEvent(PipeEvent.STARTED));
        broadcastProgress(0);
        setNextAction(action);
    }

    @Override
    public void broadcastProgress(float i) {
        if(progressListener != null){
            progressListener.onProgress(i);
        }
    }

    public void broadcastEvent(PipeEvent event){
        if(progressListener != null){
            progressListener.onEvent(event);
        }
    }

    @Override
    public void setNextAction(Action action) {

        if(action instanceof FailedAction){
            broadCastFailedEvent((FailedAction) action);
            broadcastProgress(0);
            return;
        }

        if(action instanceof FinishedAction){
            broadcastEvent(new PipeEvent(PipeEvent.FINISHED));
            broadcastProgress(100);
            return;
        }

        this.currentAction = null;
        this.currentAction = action;
        action.run();
    }

    private void broadCastFailedEvent(FailedAction action) {
        broadcastEvent(new PipeEvent(PipeEvent.FAILED, action.getMessage()));
    }

    @Override
    public void stopProcess() {
        if(currentAction != null){
            currentAction.stop();
            log("Progress Stopped");
        }else{
            log("Current Action is null");
        }
    }


    @Override
    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public void removeProgressListener() {
        progressListener = null;
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean getDebug() {
        return debug;
    }

    @Override
    public void log(String message) {
        if(debug)
            logger.log(message);
    }

    @Override
    public void pauseProcess() {
        //TODO: Pause Thread then save current state
    }

    @Override
    public void setData(Data data) {
        log("Data set key:" + data.getKey());
        dataContainer.setData(data.getKey(),data);
    }

    @Override
    public Data getData(String key) {
        log("Data requested key:" + key);
        return dataContainer.getData(key);
    }

}
