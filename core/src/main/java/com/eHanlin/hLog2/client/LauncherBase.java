package com.eHanlin.hLog2.client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LauncherBase implements Launcher {

    private String lastLog = null;
    private Lock lock = new ReentrantLock();

    @Override
    public void sendLog(String log) {
        setLastLog(log);
        System.out.println(log);
    }

    public String lastLog() {
        lock.lock();
        try {
            return lastLog;
        } finally {
            lock.unlock();
        }
    }

    protected void setLastLog(String value) {
        lock.lock();
        try {
            lastLog = value;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
