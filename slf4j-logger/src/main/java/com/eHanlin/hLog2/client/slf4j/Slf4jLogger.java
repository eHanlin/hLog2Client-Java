package com.eHanlin.hLog2.client.slf4j;

import com.eHanlin.hLog2.client.*;
import org.slf4j.Logger;

public class Slf4jLogger extends LoggerBase {

    protected Logger logger = null;

    public Slf4jLogger(Logger logger, Launcher launcher, Translator translator) {
        super(launcher, translator);
        this.logger = logger;
        this.name = logger.getName();
        if(logger.isTraceEnabled()) {
            this.level = Level.TRACE;
        } else if(logger.isDebugEnabled()) {
            this.level = Level.DEBUG;
        } else if(logger.isInfoEnabled()) {
            this.level = Level.INFO;
        } else if(logger.isWarnEnabled()) {
            this.level = Level.WARN;
        } else if(logger.isErrorEnabled()) {
            this.level = Level.ERROR;
        }
    }

    @Override
    protected Log createLog(int level) {
        if(level >= this.level){
            return new Slf4jLog(logger, launcher, translator, level, HLog2ThreadLocal.getSession());
        }
        return NullLogBase.instance;
    }

}
