package com.youmait.pipe.pipe;

import com.youmait.pipe.actions.Action;
import com.youmait.pipe.data.Data;
import com.youmait.pipe.events.PipeEvent;

public interface Pipe {

    interface OnProgressListener {
        void onProgress(float progress);
        void onEvent(PipeEvent event);
    }

    Action getCurrentAction();

    void setNextAction(Action action);

    void startProcess();

    void stopProcess();

    void pauseProcess();

    void setProgressListener(OnProgressListener progressListener);

    void removeProgressListener();

    void broadcastProgress(float i);

    void setData(Data dataObject);

    Data getData(String key);

    void setDebug(boolean debug);

    boolean getDebug();

    void log(String message);

    void broadcastEvent(PipeEvent event);
}
