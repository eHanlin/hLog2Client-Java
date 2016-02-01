package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.socket.SocketLauncher;

public class LauncherFactoryImpl extends LauncherFactory {

    public LauncherFactoryImpl() {
        this.launcher = new SocketLauncher();
    }

}
