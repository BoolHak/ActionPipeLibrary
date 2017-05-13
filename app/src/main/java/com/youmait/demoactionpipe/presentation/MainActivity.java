package com.youmait.demoactionpipe.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.youmait.demoactionpipe.R;
import com.youmait.demoactionpipe.domain.Keys;
import com.youmait.demoactionpipe.domain.MainPipe;
import com.youmait.pipe.events.PipeEvent;
import com.youmait.pipe.pipe.Pipe;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements Pipe.OnProgressListener {

    private TextView text;
    private float progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(this);
        text = (TextView) findViewById(R.id.message);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainPipe pipe = new MainPipe();
                pipe.setDebug(true);
                pipe.setProgressListener(MainActivity.this);
                pipe.startProcess();
            }
        }).start();
    }

    private void setMessage(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!isFinishing())
                    text.setText(message + " " + progress + "%");
            }
        });
    }

    @Override
    public void onProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public void onEvent(PipeEvent event) {
        switch (event.getKey()){
            case PipeEvent.STARTED:
                setMessage("Pipe started");
                break;
            case PipeEvent.FAILED:
                setMessage("Pipe error");
                break;
            case PipeEvent.FINISHED:
                setMessage("Pipe finished");
                break;
            case Keys.EVENT_SHOW_MESSAGE:
                setMessage(event.getMessage());
                break;
        }
    }
}
