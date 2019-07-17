package com.xrd.sum.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by WJ on 2019/7/15.
 */
@Entity
public class DataBean {
    @Id(autoincrement = true)
    private Long id;//保证自增长必须是Long类型的
    public String name="";
    public String startTime;
    public String endTime;

    //   @Generated(hash = 1370434209)
    @Keep  //使用keep可以修改和删除字段
    public DataBean(Long id, String name, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

//    @Generated(hash = 1024547259)
    public DataBean() {
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
}
