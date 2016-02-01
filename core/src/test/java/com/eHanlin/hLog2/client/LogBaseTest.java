package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LogBaseTest {

    @Test
    public void sendLog() {

        LauncherBase launcher = new LauncherBase();
        TranslatorBase translator = new TranslatorBase();
        LogBase log = new LogBase(launcher, translator, 40, "5302d164300457e8ef92f300");
        log.intent("intent").action("action").target("target", "target").info("info", "info").meta("meta", "meta").send();

        String logStr = "{level : 40, session : 5302d164300457e8ef92f300, intent : intent, action : action, target : {target=target}, info : {info=info}, meta : {meta=meta}}";

        Assert.assertEquals(launcher.lastLog(), logStr);
    }

}
