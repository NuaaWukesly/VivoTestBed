
package com.vivo.testbed;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public abstract class RunCaseActivity extends Activity {

    protected TextView mConsole;
    protected final int MSG_INFO = 0;
    HandlerThread mThread;
    Handler exHandler;
    
    protected Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO 自动生成的方法存根
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_INFO:
                    if (mConsole != null) {
                        mConsole.append("\n" + msg.obj + "\n");
                    }
                    break;

                default:
                    break;
            }
        }
        
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_case);
        initView();
        initValue();
        runTask();
    }
    
    private void runTask() {
        mThread = new HandlerThread("vivo_test_bed_runTask_handlerthread");
        mThread.start();
        exHandler = new Handler(mThread.getLooper());
        exHandler.post(new Runnable() {
            
            @Override
            public void run() {
                // TODO 自动生成的方法存根
                commitTask();
            }
        });
    }
    
    public abstract void initValue();
    
    public abstract void commitTask();

    protected void initView() {
        mConsole = (TextView) findViewById(R.id.running_info);
        mConsole.setMovementMethod(new ScrollingMovementMethod());
    }
    
    @Override
    protected void onDestroy() {
        // TODO 自动生成的方法存根
        super.onDestroy();
        if (exHandler != null) {
            exHandler.removeCallbacksAndMessages(null);
            exHandler.getLooper().quit();
            exHandler = null;
        }
    }
}
