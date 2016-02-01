package com.eHanlin.hLog2.client.slf4j;

import com.eHanlin.hLog2.client.Launcher;
import com.eHanlin.hLog2.client.Level;
import com.eHanlin.hLog2.client.LogBase;
import com.eHanlin.hLog2.client.Translator;
import org.slf4j.Logger;

public class Slf4jLog extends LogBase {

    protected Logger logger = null;

    public Slf4jLog(Logger logger, Launcher launcher, Translator translator, Integer level, String logSessionId) {
        super(launcher, translator, level, logSessionId);
        this.logger = logger;
    }

    @Override
    public void send() {
        String log = translator.translate(level, session, intent, action, target, info, meta);
        launcher.sendLog(log);

        if(level <= Level.TRACE) {
            logger.trace(log);
        } else if(level <= Level.DEBUG) {
            logger.debug(log);
        } else if(level <= Level.INFO) {
            logger.info(log);
        } else if(level <= Level.WARN) {
            logger.warn(log);
        } else if(level <= Level.ERROR) {
            logger.error(log);
        }
    }

}
