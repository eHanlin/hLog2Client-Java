package com.eHanlin.hLog2.client;

public interface Log {

    public Log session(String session);

    public Log intent(String intent);

    public Log action(String action);

    public Log target(Object... targets);

    public Log info(Object... infos);

    public Log meta(Object... infos);

    public void send();

}
