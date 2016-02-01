package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.util.SingletonInitiator;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LoggerFactory {

    private static LoggerFactory factory = null;

    private final static SingletonInitiator singletonReadyFactory = new SingletonInitiator(
        () -> {
            try {
                Class clazz = Class.forName("com.eHanlin.hLog2.client.LoggerFactoryImpl");
                Constructor constructor = clazz.getConstructor();
                factory = (LoggerFactory) constructor.newInstance();
            } catch (Exception e) {
                factory = new LoggerFactoryBase();
            }
            return true;
        }
    );

    public static Logger getLogger(Class clazz) {
        if(singletonReadyFactory.isReady()){
            return factory.getLoggerByClass(clazz);
        }
        return null;
    }

    public static Logger getLogger(String name) {
        if(singletonReadyFactory.isReady()){
            return factory.getLoggerByName(name);
        }
        return null;
    }


    protected Launcher launcher = LauncherFactory.getLauncher();
    protected Translator translator = TranslatorFactory.getTranslator();

    protected Map<String, Logger> loggerMap = new ConcurrentHashMap<String, Logger>();

    protected abstract Logger createLogger(String name);

    public Logger getLoggerByClass(Class clazz) {
        return getLoggerByName(clazz.getName());
    }

    public Logger getLoggerByName(String name) {
        if(!loggerMap.containsKey(name)){
            Logger logger = createLogger(name);
            loggerMap.put(name, logger);
        }
        return loggerMap.get(name);
    }


    public void setLauncher(Launcher value) {
        this.launcher = value;
    }
    public Launcher getLauncher() {
        return this.launcher;
    }

    public void setTranslator(Translator value) {
        this.translator = value;
    }
    public Translator getTranslator() {
        return this.translator;
    }

}
