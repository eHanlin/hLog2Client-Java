package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.Test;

public class LauncherFactoryBaseTest {

    @Test
    public void baseInstance() {
        Assert.assertTrue(LauncherFactory.getLauncher() instanceof LauncherBase);
    }

}
