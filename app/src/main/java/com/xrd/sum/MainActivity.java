package com.xrd.sum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xrd.sum.bean.DataBean;
import com.xrd.sum.daoUtils.SQLiteUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_year)
    EditText etYear;
    @BindView(R.id.et_month)
    EditText etMonth;
    @BindView(R.id.et_day)
    EditText etDay;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.tv_newdate)
    TextView tvNewdate;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;

    private boolean isCheck = false;
    private String year;
    private String month;
    private String day;
    private String name;
    private String news;
    private String old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        llName.setVisibility(View.INVISIBLE);
        llDate.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //重新初始化
        etYear.setText("");
        etMonth.setText("");
        etDay.setText("");
        etName.setText("");
        tvNewdate.setText("");
        llDate.setVisibility(View.INVISIBLE);
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        llName.setVisibility(View.INVISIBLE);
        commit.setText("查询");
    }

    /**
     * 查询
     */
    private void search() {
        startSearch(year, month, day);
    }

    /**
     * 保存
     */
    private void saveAll() {

        List<DataBean> dataBeanList = SQLiteUtils.getInstance().findAll();
        if(dataBeanList!=null){
            for (int i = 0; i < dataBeanList.size(); i++) {
                if(name.equals(dataBeanList.get(i).getName())){
                    SQLiteUtils.getInstance().deleteData(dataBeanList.get(i));
                }
            }
            SQLiteUtils.getInstance().addData(new DataBean(null,name,old,news));
        }else{
            SQLiteUtils.getInstance().addData(new DataBean(null,name,old,news));
        }

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RecordListActivity.class));
        isCheck=false;

    }

    private boolean check() {
        if (TextUtils.isEmpty(year)) {
            Toast.makeText(this, "请输入年", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (year.length() != 4) {
            Toast.makeText(this, "请输入4位数字的年份", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(month)) {
            Toast.makeText(this, "请输入月", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (month.length() > 2) {
            Toast.makeText(this, "您输入的月份有误！！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(day)) {
            Toast.makeText(this, "请输入日子", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (day.length() > 0 && Integer.parseInt(day) <= 0 || Integer.parseInt(day) > 31) {
            Toast.makeText(this, "您输入的日子有误！！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isCheck && TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入名字", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void startSearch(String year, String month, String day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
        Date date = null; // 指定日期
        Date newDate = null;
        try {
            date = dateFormat.parse(year + "-" + month + "-" + day);
            newDate = addDate(date, 285); // 指定日期加上n天
            old = dateFormat.format(date);
            news = dateFormat.format(newDate);
            llName.setVisibility(View.VISIBLE);
            llDate.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            tvNewdate.setText(news);
            commit.setText("保存");
            isCheck = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(dateFormat.format(date));// 输出格式化后的日期
        System.out.println(dateFormat.format(newDate));
    }

    public Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    @OnClick({R.id.tv_record, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_record:
                startActivity(new Intent(MainActivity.this, RecordListActivity.class));
                break;
            case R.id.commit:
                year = etYear.getText().toString().trim();
                month = etMonth.getText().toString().trim();
                day = etDay.getText().toString().trim();
                name = etName.getText().toString().trim();
                if (isCheck) {
                    if (check())
                        saveAll();
                } else {
                    if (check())
                        search();
                }
                break;
        }

    }
}
