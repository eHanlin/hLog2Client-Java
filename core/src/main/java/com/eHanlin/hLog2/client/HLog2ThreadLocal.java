package com.eHanlin.hLog2.client;

public class HLog2ThreadLocal {

    private static InheritableThreadLocal<String> session = new InheritableThreadLocal<String>();
    public static String getSession(){
        return session.get();
    }
    public static void setSession(String id){
        session.set(id);
    }

    private static InheritableThreadLocal<String> intent = new InheritableThreadLocal<String>();
    public static String getIntent(){
        return intent.get();
    }
    public static void setIntent(String value){
        intent.set(value);
    }

}
