package com.eHanlin.hLog2.client;

public class NullLogBase implements Log {

    public static NullLogBase instance = new NullLogBase(null, null, null);

    public NullLogBase(Launcher launcher, Integer level, String sessionId) {

    }

    @Override
    public Log session(String session) {
        return this;
    }

    @Override
    public Log intent(String intent) {
        return this;
    }

    @Override
    public Log action(String action) {
        return this;
    }

    @Override
    public Log target(Object... targets) {
        return this;
    }

    @Override
    public Log info(Object... infos) {
        return this;
    }

    @Override
    public Log meta(Object... metas) {
        return this;
    }

    @Override
    public void send() {
    }
}
