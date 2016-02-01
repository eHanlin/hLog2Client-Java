package com.eHanlin.hLog2.client;

public class TranslatorBase implements Translator {

    @Override
    public String translate(Integer level, String session, String intent, String action, Object target, Object info, Object meta) {
        return "{level : "+level+", session : "+session+", intent : "+intent+", action : "+action+", target : "+target.toString()+", info : "+info.toString()+", meta : "+meta.toString()+"}";
    }

}
