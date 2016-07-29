
package com.vivo.testbed.performance;

import java.util.Date;

import com.vivo.testbed.R;
import com.vivo.testbed.R.layout;
import com.vivo.testbed.RunCaseActivity;

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

public class ResTimeTestActivity extends RunCaseActivity {

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
            String info = null;
            switch (msg.what) {
                case MSG_BEDIN:
                    info = "\n******* "+ count + " *******" 
                        +"\n begin time:" + new Date(System.currentTimeMillis()).toGMTString() + 
                            "\n and  detail is " + System.currentTimeMillis() + " ms";
                    count ++;
                    break;

                case MSG_END:
                    info = "\n end time:" + new Date(System.currentTimeMillis()).toGMTString() + 
                    "\n and  detail is " + System.currentTimeMillis() + " ms";
                    break;
                case MSG_SUMMARY:
                    info = "\n consume time is : " + msg.obj + " ms \n";
                    count = 0;
                default:
                    break;
            }
            msg = mHandler.obtainMessage();
            msg.obj = info;
            msg.what = MSG_INFO;
            mHandler.sendMessage(msg);
        }
        
    };

    @Override
    public void initValue() {
        // TODO 自动生成的方法存根
        
    }

    @Override
    public void commitTask() {
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
    
    
}
