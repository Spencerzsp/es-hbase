package com.daqsoft;

import com.daqsoft.util.HBaseUtil;
import org.junit.Test;

public class HBaseTest {

    @Test
    public void deleteHbaseTable(){
        HBaseUtil.deleteTable("doc");
    }
}
