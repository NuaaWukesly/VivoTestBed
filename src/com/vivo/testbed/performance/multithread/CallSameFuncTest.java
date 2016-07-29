
package com.vivo.testbed.performance.multithread;

import java.util.Random;
import com.vivo.testbed.RunCaseActivity;
import com.vivo.testbed.bean.Config;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class CallSameFuncTest extends RunCaseActivity {
    
    private Random mRandom;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        new Thread(runnable).start();
    }

    private void func(int i) {
        try {
            Log.i("CallSameFuncTest", "into func");
            String obj = null;
            op(2000);
            obj =  "func    " +i;
            op(1000);
            if (obj != null) {
                Log.d("CallSameFuncTest", i + " func :" + obj);
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("CallSameFuncTest", "func exception = " + e);
            Message msg= mHandler.obtainMessage();
            msg.what = MSG_INFO;
            msg.obj = "func exception = " + e;
            mHandler.sendMessage(msg);
        }
        
    }
    
    private  int ID = 0;
    private Runnable runnable = new Runnable() {
        
        @Override
        public void run() {
            // TODO 自动生成的方法存根
            func(ID++);
        }
    };
    
    private void op(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initValue() {
        // TODO 自动生成的方法存根
        mRandom = new Random();
    }

    @Override
    public void commitTask() {
        // TODO 自动生成的方法存根
        for (int i = 0; i< 5 ;i++) {
            Config.executorService.submit(runnable);
            op(mRandom.nextInt(350));
        }
    }
}
