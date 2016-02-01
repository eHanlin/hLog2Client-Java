package com.eHanlin.hLog2.client;

public class LoggerBase implements Logger {

    protected Launcher launcher = null;
    protected Translator translator = null;
    protected String name = null;
    protected int level = 0;

    public LoggerBase(Launcher launcher, Translator translator) {
        this.launcher = launcher;
        this.translator = translator;
    }

    protected Log createLog(int level) {
        if(level >= this.level){
            return new LogBase(launcher, translator, level, HLog2ThreadLocal.getSession());
        }
        return NullLogBase.instance;
    }

    @Override
    public Log log(int level) {
        return createLog(level);
    }

    @Override
    public Log trace() {
        return createLog(Level.TRACE);
    }

    @Override
    public Log debug() {
        return createLog(Level.DEBUG);
    }

    @Override
    public Log info() {
        return createLog(Level.INFO);
    }

    @Override
    public Log warn() {
        return createLog(Level.WARN);
    }

    @Override
    public Log error() {
        return createLog(Level.ERROR);
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setLevel(int value) {
        this.level = value;
    }

    @Override
    public boolean isLevelEnabled(int level) {
        return this.level <= level;
    }

    @Override
    public boolean isTraceEnabled() {
        return level <= Level.TRACE;
    }

    @Override
    public boolean isDebugEnabled() {
        return level <= Level.DEBUG;
    }

    @Override
    public boolean isInfoEnabled() {
        return level <= Level.INFO;
    }

    @Override
    public boolean isWarnEnabled() {
        return level <= Level.WARN;
    }

    @Override
    public boolean isErrorEnabled() {
        return level <= Level.ERROR;
    }

}
