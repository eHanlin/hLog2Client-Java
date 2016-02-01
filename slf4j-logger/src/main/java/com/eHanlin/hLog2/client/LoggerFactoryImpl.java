package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.slf4j.Slf4jLogger;

public class LoggerFactoryImpl extends LoggerFactory {

    @Override
    protected Logger createLogger(String name) {
        org.slf4j.Logger logger =  org.slf4j.LoggerFactory.getLogger(name);
        return new Slf4jLogger(logger, launcher, translator);
    }

}
