package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.socket.SocketLauncher;
import org.junit.Assert;
import org.junit.Test;

public class LauncherFactoryImplTest {

    @Test
    public void socketInstance() {
        Assert.assertTrue(LauncherFactory.getLauncher() instanceof SocketLauncher);
    }

}
