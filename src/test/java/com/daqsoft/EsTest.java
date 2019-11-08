package com.daqsoft;

import org.junit.Test;

import java.net.UnknownHostException;

public class EsTest {

    @Test
    public void testEsSearch() throws UnknownHostException {
        AppMain2.getByKeyWord(AppMain2.getEsClient(), "对象");
    }
}
