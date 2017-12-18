package com.bj.myapplication.Bean;

import java.util.List;

/**
 * Created by 郑文杰 on 2017/12/14.
 */

public class SearchBean {


    public String msg;
    public RetBean ret;
    public String code;

    public static class RetBean {


        public String all;
        public String movieNum;
        public int pnum;
        public int totalRecords;
        public String trailerNum;
        public String informationNum;
        public int records;
        public String otherNum;
        public int totalPnum;
        public List<ListBean> list;

        public static class ListBean {


            public int airTime;
            public String angleIcon;
            public String director;
            public String videoType;
            public String description;
            public String pic;
            public String title;
            public String duration;
            public String loadtype;
            public String actors;
            public int score;
            public String dataId;
            public String loadURL;
            public String shareURL;
            public String region;
        }
    }
}
