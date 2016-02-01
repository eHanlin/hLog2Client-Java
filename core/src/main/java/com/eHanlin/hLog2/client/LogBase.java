package com.eHanlin.hLog2.client;

import java.util.HashMap;
import java.util.Map;

public class LogBase implements Log {

    protected Launcher launcher = null;
    protected Translator translator = null;
    protected Integer level = null;
    protected String session = null;
    protected String intent = null;
    protected String action = null;
    @SuppressWarnings("rawtypes")
    protected Map target = null;
    @SuppressWarnings("rawtypes")
    protected Map info = null;
    @SuppressWarnings("rawtypes")
    protected Map meta = null;


    public LogBase(Launcher launcher, Translator translator, Integer level, String session) {
        this.launcher = launcher;
        this.translator = translator;
        this.level = level;
        this.session = session;
    }

    @Override
    public Log session(String session) {
        this.session = session;
        return this;
    }

    @Override
    public Log intent(String intent) {
        this.intent = intent;
        return this;
    }

    @Override
    public Log action(String action) {
        this.action = action;
        return this;
    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public Log target(Object... targets) {
        if(targets.length <= 0){
            return this;
        }
        if(target == null){
            target = new HashMap();
        }
        insertToMap(target, targets);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Log info(Object... infos) {
        if(infos.length <= 0){
            return this;
        }
        if(info == null){
            info = new HashMap();
        }
        insertToMap(info, infos);
        return this;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Log meta(Object... metas) {
        if(metas.length <= 0){
            return this;
        }
        if(meta == null){
            meta = new HashMap();
        }
        insertToMap(meta, metas);
        return this;
    }


    @Override
    public void send() {
        String log = translator.translate(level, session, intent, action, target, info, meta);
        launcher.sendLog(log);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void insertToMap(Map map, Object[] infos){
        int i = 0;
        while(i < infos.length-1){
            map.put(infos[i], infos[i+1]);
            i += 2;
        }
        if(i < infos.length){
            map.put(infos[i], null);
        }
    }

}
