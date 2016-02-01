package com.eHanlin.hLog2.client.json;

import com.eHanlin.hLog2.client.Translator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonTranslator implements Translator {

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String translate(Integer level, String session, String intent, String action, Object target, Object info, Object meta) {

        Map<String, Object> map = new HashMap();
        map.put("createDate", new Date());
        if(level != null){
            map.put("level", level);
        }
        if(session != null){
            map.put("session", session);
        }
        if(intent != null){
            map.put("intent", intent);
        }
        if(action != null){
            map.put("action", action);
        }
        if(target != null){
            map.put("target", target);
        }
        if(info != null){
            map.put("info", info);
        }
        if(meta != null){
            map.put("meta", meta);
        }

        StackTraceElement stacks = Thread.currentThread().getStackTrace()[3];
        map.put("class", stacks.getClassName());
        map.put("method", stacks.getMethodName());
        map.put("line", stacks.getLineNumber());

        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

}
