
package com.vivo.testbed.performance;

import java.util.Date;

import com.vivo.testbed.R;
import com.vivo.testbed.R.layout;

import android.app.Activity;
import android.mtp.MtpStorageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResTimeTestActivity extends Activity {

    HandlerThread mThread;
    Handler mHandler;
    private TextView Info;
    private final int MSG_END = 0;
    private final int MSG_BEDIN = 1;
    private final int MSG_SUMMARY = 2;
    private final int exeTime = 1000;
    private int count = 0;
    
    private Handler mainHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO 自动生成的方法存根
            super.handleMessage(msg);
            String info;
            switch (msg.what) {
                case MSG_BEDIN:
                    info = "\n******* "+ count + " *******" 
                        +"\n begin time:" + new Date(System.currentTimeMillis()).toGMTString() + 
                            "\n and  detail is " + System.currentTimeMillis() + " ms";
                    Info.append(info);
                    count ++;
                    break;

                case MSG_END:
                    info = "\n end time:" + new Date(System.currentTimeMillis()).toGMTString() + 
                    "\n and  detail is " + System.currentTimeMillis() + " ms";
                    Info.append(info);
                    break;
                case MSG_SUMMARY:
                    info = "\n consume time is : " + msg.obj + " ms \n";
                    Info.append(info);
                    count = 0;
                default:
                    break;
            }
            
        }
        
    };
    
    private Runnable exeRunnable = new Runnable() {
        
        @Override
        public void run() {
            // TODO 自动生成的方法存根
            long beginTime;
            long endTime;
            beginTime = System.currentTimeMillis();
            for (int i = 0 ; i < exeTime ; i ++) {
                //mainHandler.sendEmptyMessage(MSG_BEDIN);
                getApplicationContext().getString(R.string.unknown_artist_name);
                //mainHandler.sendEmptyMessage(MSG_END);
            }
            endTime = System.currentTimeMillis();
            Message msg = new Message();
            msg.what = MSG_SUMMARY;
            msg.obj = (endTime - beginTime) + "";
            mainHandler.sendMessage(msg);
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_time_test);
        Info = (TextView) findViewById(R.id.resTestInfo);
        Info.setMovementMethod(new ScrollingMovementMethod());
        mThread = new HandlerThread("vivo_test_bed_res_time_test_handlerthread");
        mThread.start();
        mHandler = new Handler(mThread.getLooper());
        mHandler.post(exeRunnable);
    }

    @Override
    protected void onDestroy() {
        // TODO 自动生成的方法存根
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.getLooper().quit();
            mHandler = null;
        }
    }
    
    
}
