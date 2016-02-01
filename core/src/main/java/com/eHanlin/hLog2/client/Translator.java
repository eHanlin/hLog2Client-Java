package com.eHanlin.hLog2.client;

public interface Translator {

    public String translate(Integer level, String session, String intent, String action, Object target, Object info, Object meta);

}
