package com.daqsoft;

import com.daqsoft.util.ElasticsearchUtil;
import com.daqsoft.util.HBaseUtil;

import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        HBaseUtil hbase = new HBaseUtil();

//        hbase.createTable("doc", "cf");
//        hbase.deleteTable("doc");

        ElasticsearchUtil elasticsearchUtil = new ElasticsearchUtil();
        elasticsearchUtil.createIndex();


    }


}
