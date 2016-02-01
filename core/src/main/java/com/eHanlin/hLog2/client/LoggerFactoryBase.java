package com.eHanlin.hLog2.client;

public class LoggerFactoryBase extends LoggerFactory {

    @Override
    protected Logger createLogger(String name) {
        LoggerBase logger = new LoggerBase(launcher, translator);
        logger.setName(name);
        logger.setLevel(Level.TRACE);
        return logger;
    }

}
