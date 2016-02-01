package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class LauncherBaseTest {

    @Test
    public void sendLog() {
        HashMap target = new HashMap();
        target.put("target", "target");

        HashMap info = new HashMap();
        info.put("info", "info");

        HashMap meta = new HashMap();
        meta.put("meta", "meta");

        String logStr = "{level : 40, session : 5302d164300457e8ef92f300, intent : intent, action : action, target : {target=target}, info : {info=info}, meta : {meta=meta}}";

        LauncherBase launcherBase = new LauncherBase();
        launcherBase.sendLog(logStr);
        Assert.assertEquals(launcherBase.lastLog(), logStr);
    }

}
