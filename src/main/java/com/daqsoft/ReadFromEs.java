package com.daqsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: daqsoft
 * @date: 2019/11/12
 */

public class ReadFromEs {

    private static final Logger LOGGER = LoggerFactory.getLogger("es");
    private static final int SCROLL_SIZE = 10000;
    private static final int HBASE_PUT_SIZE = 1000;

    public void putAllDataToHbase(String tableName){

    }
}
