package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.slf4j.Slf4jLog;
import com.eHanlin.hLog2.client.slf4j.Slf4jLogger;
import org.junit.Assert;
import org.junit.Test;

public class LoggerFactoryImplTest {

    @Test
    public void slf4jInstance() {
        Logger logger = LoggerFactory.getLogger("test");
        Assert.assertTrue(logger instanceof Slf4jLogger);
        Log log = logger.error();
        Assert.assertTrue(log instanceof Slf4jLog);
    }

}
