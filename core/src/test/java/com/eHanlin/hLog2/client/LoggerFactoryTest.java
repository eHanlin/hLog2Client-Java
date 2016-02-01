package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.Test;

public class LoggerFactoryTest {

    @Test
    public void baseInstance() {
        Logger logger = LoggerFactory.getLogger("test");
        Assert.assertTrue(logger instanceof LoggerBase);
        Assert.assertTrue(((LoggerBase) logger).launcher instanceof LauncherBase);
        LauncherBase launcher = (LauncherBase)((LoggerBase) logger).launcher;
        Log log = logger.error();
        Assert.assertTrue(log instanceof LogBase);
        String logStr = "{level : 40, session : null, intent : intent, action : action, target : {target=target}, info : {info=info}, meta : {meta=meta}}";
        log.intent("intent").action("action").target("target", "target").info("info", "info").meta("meta", "meta").send();
        Assert.assertEquals(launcher.lastLog(), logStr);
    }

}
