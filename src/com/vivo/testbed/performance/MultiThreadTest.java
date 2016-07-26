
package com.vivo.testbed.performance;

import com.vivo.testbed.CaseActivity;
import com.vivo.testbed.bean.Config;

public class MultiThreadTest extends CaseActivity{

    @Override
    protected void initEngineParam() {
        // TODO 自动生成的方法存根
        mParam.mConfigType = Config.CONDIG_USECASE_MULTITHREAD;
        mParam.mContext = getApplicationContext();
    }
}
