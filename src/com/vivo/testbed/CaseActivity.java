
package com.vivo.testbed;

import java.util.ArrayList;
import java.util.List;

import com.vivo.testbed.adapter.UsecaseAdapter;
import com.vivo.testbed.bean.ConfigEngineParam;
import com.vivo.testbed.bean.Usecase;
import com.vivo.testbed.callback.ConfigCallback;
import com.vivo.testbed.engine.ConfigEngine;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public abstract class CaseActivity extends Activity implements OnItemClickListener, ConfigCallback, OnItemLongClickListener{

    private String TAG = "CaseActivity";
    private ListView mListView;
    private UsecaseAdapter mAdapter;
    private List<Usecase> mUsecases;
    protected ConfigEngineParam mParam = new ConfigEngineParam();
    
    
    protected abstract void initEngineParam();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        initView();
        initEngineParam();
        config(mParam);
    }
    
    protected void config(ConfigEngineParam param) {
        ConfigEngine<?> configEngine = new ConfigEngine<Object>(param, this);
        if (Build.VERSION.SDK_INT < 14) {
            configEngine.execute(param);
        } else {
            configEngine.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, param);
        }
    }
    
    protected void initView() {
        mListView = (ListView) findViewById(R.id.case_list);
        if (mUsecases == null) {
            mUsecases = new ArrayList<Usecase>();
            mAdapter = new UsecaseAdapter(mUsecases, this);
        }
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO 自动生成的方法存根
        if (mAdapter != null) {
            Usecase usecase = (Usecase) mAdapter.getItem(position);
            if (usecase != null) {
                Log.d(TAG, "start usecase,action is " + usecase.mAction);
                Intent i = new Intent(usecase.mAction);
                startActivity(i);
            }
        }
    }

    
    
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO 自动生成的方法存根
        if (position >= 0 && mAdapter != null && position < mAdapter.getCount()) {
            Usecase usecase = (Usecase) mAdapter.getItem(position);
            if (usecase != null) {
                Toast.makeText(getApplicationContext(), usecase.mInfo, Toast.LENGTH_LONG).show();;
            }
            return true;
        }
        return false;
    }

    @Override
    public void getConfig(List config) {
        // TODO 自动生成的方法存根
        if (config != null) {
            mUsecases = config;
            if (mAdapter != null) {
                mAdapter.setUsecase(mUsecases);
            } else {
                mAdapter = new UsecaseAdapter(mUsecases, this);
                mListView.setAdapter(mAdapter);
                mListView.invalidate();
            }
        }
    }
}
