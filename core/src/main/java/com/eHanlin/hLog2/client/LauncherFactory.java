package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.util.SingletonInitiator;

import java.lang.reflect.Constructor;

public abstract class LauncherFactory {

    private static LauncherFactory factory = null;

    private final static SingletonInitiator singletonReadyFactory = new SingletonInitiator(
        () -> {
            try {
                Class clazz = Class.forName("com.eHanlin.hLog2.client.LauncherFactoryImpl");
                Constructor constructor = clazz.getConstructor();
                factory = (LauncherFactory) constructor.newInstance();
            } catch (Exception e) {
                factory = new LauncherFactoryBase();
            }
            return true;
        }
    );

    public static Launcher getLauncher() {
        if(singletonReadyFactory.isReady()){
            return factory.createLauncher();
        }
        return null;
    }


    protected Launcher launcher = null;

    public Launcher createLauncher() {
        return launcher;
    }

}
