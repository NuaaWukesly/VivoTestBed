
package com.vivo.testbed;

import com.vivo.testbed.bean.Config;

public class BedActivity extends CaseActivity{

    @Override
    protected void initEngineParam() {
        // TODO 自动生成的方法存根
        mParam.mConfigType = Config.CONFIG_USECASE;
        mParam.mContext = getApplicationContext();
    }
}
