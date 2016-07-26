package com.vivo.testbed.adapter;

import java.util.List;

import com.vivo.testbed.R;
import com.vivo.testbed.bean.Usecase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UsecaseAdapter extends BaseAdapter{

    private List<Usecase> mUsecase;
    private Context mContext;
    private LayoutInflater mInflater;
    private class ViewHolder {
        public TextView ucName;
        public TextView ucInfo;
    }
    
    public UsecaseAdapter(List<Usecase> usecase, Context context) {
        super();
        this.mUsecase = usecase;
        this.mContext = context;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public void setUsecase(List<Usecase> usecase) {
        this.mUsecase = usecase;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        if (mUsecase != null) {
            return mUsecase.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        if (mUsecase == null || mUsecase.size() <= position || position < 0) {
            return null;
        }
        return mUsecase.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO 自动生成的方法存根
        ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.usecase_item, null);
            vh = new ViewHolder();
            vh.ucName = (TextView) convertView.findViewById(R.id.usecase_name);
            vh.ucInfo = (TextView) convertView.findViewById(R.id.usecase_info);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Usecase item = (Usecase) getItem(position);
        if (item != null) {
            vh.ucName.setText(item.mName);
            vh.ucInfo.setText(item.mInfo);
        }
        return convertView;
    }

}
