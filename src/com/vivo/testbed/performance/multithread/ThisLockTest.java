
package com.vivo.testbed.performance.multithread;

import com.vivo.testbed.R;
import com.vivo.testbed.bean.Config;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ThisLockTest extends Activity implements OnClickListener{

    private final String TAG = "ThisLockTest";
    private int mOpTime = 1000;
    private final int MSG_NOOP = 0;
    private TextView mConsole;
    
    
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO 自动生成的方法存根
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_NOOP:
                    nooper();
                    break;
                default:
                    break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thislock_test);
        Config.executorService.submit(mThisLock);
        initView();
    }
    
    private void initView() {
        mConsole = (TextView) findViewById(R.id.thislock_console);
        mConsole.setOnClickListener(this);
    }
    
    private Runnable mThisLock = new Runnable() {
        
        @Override
        public void run() {
            consumeOp();
        }
    };
    
    private void nooper(){
        synchronized (this) {
            //this is a no op
            Log.d(TAG, "nooper begin");
            int i = 0;
            i++;
        }
    }
    
    private void consumeOp(){
        synchronized (this) {
            //mHandler.sendEmptyMessage(MSG_NOOP);
            try {
                Log.d(TAG, "consumeOp begin");
                Thread.sleep(mOpTime * 20);
                Log.d(TAG, "consumeOp end");
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO 自动生成的方法存根
        switch (v.getId()) {
            case R.id.thislock_console:
                nooper();
                break;

            default:
                break;
        }
    }
}
/*
 * 结论：
 *      this对应当前ThisLockTest对象，当consumeOp运行时，其将被
 *      持有，即ThisLock无法操作，直到consumeOp运行结束，释放ThisLockTest.
 *      
 *      
 * 验证：
 *     1、进入-->点击返回-->无响应   （20s以内，因为该短时间内consumeOp持有this）
 * 
 *     2、进入--点击helloworld（会执行noop）--查看log会发现，noop在consumeOp结束后才开始执行。
 * 
 * 
 * 
 * 
 * 
 */



