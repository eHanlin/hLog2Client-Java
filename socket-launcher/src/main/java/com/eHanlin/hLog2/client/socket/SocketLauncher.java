package com.eHanlin.hLog2.client.socket;

import com.eHanlin.hLog2.client.LauncherBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketLauncher extends LauncherBase implements Runnable {

    protected BlockingQueue<String> queue = new LinkedBlockingQueue();
    protected TextSocketClient socket = null;

    public SocketLauncher() {
        this("socketLauncher.properties");
    }

    public SocketLauncher(String configPath) {
        Properties props = new Properties();
        URL configFilePath = Thread.currentThread().getContextClassLoader().getResource(configPath);
        if (configFilePath != null) {
            try {
                props.load(new FileInputStream(configFilePath.getPath()));
            } catch (IOException e) {

            }
        }

        String host = props.getProperty("socketLauncher.host", "127.0.0.1");
        Integer port = Integer.parseInt(props.getProperty("socketLauncher.port", "10992"));

        init(host, port);
    }

    public SocketLauncher(String host, Integer port) {
        init(host, port);
    }

    protected void init(String host, Integer port) {
        socket = new TextSocketClient(host, port);
        new Thread(this).start();
    }

    @Override
    public void sendLog(String log) {
        try {
            queue.put(log);
            this.setLastLog(log);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String log = queue.take();
                socket.send(log);
            } catch (InterruptedException e) {

            }
        }
    }
}
