
package com.vivo.testbed.engine;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.vivo.testbed.bean.Config;
import com.vivo.testbed.bean.ConfigEngineParam;
import com.vivo.testbed.bean.Usecase;
import com.vivo.testbed.callback.ConfigCallback;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

/**
 * the engine to excute praser config information task.
 * 
 * @author wuxiangli
 */
public class ConfigEngine<T> extends AsyncTask<ConfigEngineParam, Void, List<T>> {

    private ConfigEngineParam param;
    private ConfigCallback mCallback;

    @Override
    protected List<T> doInBackground(ConfigEngineParam... params) {
        // TODO 自动生成的方法存根
        return executor(param);
    }
    
    @Override
    protected void onPostExecute(List<T> result) {
        // TODO 自动生成的方法存根
        super.onPostExecute(result);
        if (result != null) {
            Log.d("executor", "result size = "  + result.size());
        }
        mCallback.getConfig(result);
    }



    public ConfigEngine(ConfigEngineParam param, ConfigCallback callback) {
        super();
        this.param = param;
        this.mCallback = callback;
    }

    private List<T> executor(ConfigEngineParam param) {
        if (param == null || param.mContext == null || TextUtils.isEmpty(param.mConfigType)) {
            return null;
        }
        List configs = new ArrayList();
        Object config = null;
        InputStream stream = null;
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            stream = param.mContext.getResources().getAssets().open(Config.GONFIG_FILE_PATH);
            xmlPullParser.setInput(stream, "utf-8");
            int evtType = xmlPullParser.getEventType();
            while (evtType != XmlPullParser.END_DOCUMENT) {
                switch (evtType) {
                    case XmlPullParser.START_TAG:
                        String tag = xmlPullParser.getName();
                        Log.d("executor", "start_tag = "  + tag);
                        if (tag.equalsIgnoreCase(param.mConfigType)) {
                            config = new Usecase();
                            ((Usecase) config).mName = xmlPullParser.getAttributeValue(null,
                                    Config.GONFIG_NAME);
                            ((Usecase) config).mInfo = xmlPullParser.getAttributeValue(null,
                                    Config.CONFIG_INFO);
                            ((Usecase) config).mAction = xmlPullParser.getAttributeValue(null,
                                    Config.CONFIG_ACTION);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xmlPullParser.getName();
                        Log.d("executor", "end_tag = "  + tag);
                        if (tag.equalsIgnoreCase(param.mConfigType)
                                && config != null) {
                            configs.add(config);
                            config = null;
                        }
                        break;
                    default:
                        break;
                }
                evtType = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.d("executor", "Exception = "  + e);
        }
        return configs;
    }

}
