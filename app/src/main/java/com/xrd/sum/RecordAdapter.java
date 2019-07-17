package com.xrd.sum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xrd.sum.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WJ on 2019/7/15.
 */

public class RecordAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private final LayoutInflater inflater;
    List<DataBean> mList=new ArrayList<>();

    public RecordAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        DataBean bean = mList.get(position);
        if(bean!=null){
           holder1.tvName.setText(bean.getName());
           holder1.tvStarttime.setText(bean.getStartTime());
           holder1.tvEndtime.setText(bean.getEndTime());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<DataBean> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_starttime)
        TextView tvStarttime;
        @BindView(R.id.tv_endtime)
        TextView tvEndtime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
