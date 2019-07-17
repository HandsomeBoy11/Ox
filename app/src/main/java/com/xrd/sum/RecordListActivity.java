package com.xrd.sum;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xrd.sum.bean.DataBean;
import com.xrd.sum.daoUtils.SQLiteUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordListActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private List<DataBean> mList = new ArrayList<>();
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.line));
        rvList.addItemDecoration(itemDecoration);
        recordAdapter = new RecordAdapter(this);
        rvList.setAdapter(recordAdapter);
    }


    private void initData() {
        mList.clear();
        mList.add(new DataBean(null,"名字", "开始日子", "出生日子"));

        List<DataBean> dataBeanList = SQLiteUtils.getInstance().findAll();
        if(dataBeanList!=null&&dataBeanList.size()>0){
            tvNodata.setVisibility(View.GONE);
            mList.addAll(dataBeanList);
            recordAdapter.setData(mList);//设置数据
        }else{
            mList.clear();
            tvNodata.setVisibility(View.VISIBLE);
        }
    }
}
