package com.xrd.sum;

import java.util.List;

/**
 * Created by WJ on 2019/7/15.
 */

public class ListBean {

    private List<Bean> list;

    public ListBean(List<Bean> list) {
        this.list = list;
    }

    public List<Bean> getList() {
        return list;
    }

    public void setList(List<Bean> list) {
        this.list = list;
    }

    public static class Bean{
        private String name;
        private String startTime;
        private String endTime;

        public Bean(String name, String startTime, String endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

}
