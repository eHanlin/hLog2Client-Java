package com.eHanlin.hLog2.client;

public interface Logger {

    public Log log(int level);

    public Log debug();

    public Log error();

    public Log info();

    public Log trace();

    public Log warn();

    public String getName();

    public boolean isLevelEnabled(int level);

    public boolean isDebugEnabled();

    public boolean isErrorEnabled();

    public boolean isInfoEnabled();

    public boolean isTraceEnabled();

    public boolean isWarnEnabled();

}
