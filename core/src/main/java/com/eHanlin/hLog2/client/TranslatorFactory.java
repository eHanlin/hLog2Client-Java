package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.util.SingletonInitiator;

import java.lang.reflect.Constructor;

public class TranslatorFactory {

    private static TranslatorFactory factory = null;

    private final static SingletonInitiator singletonReadyFactory = new SingletonInitiator(
        () -> {
            try {
                Class clazz = Class.forName("com.eHanlin.hLog2.client.TranslatorFactoryImpl");
                Constructor constructor = clazz.getConstructor();
                factory = (TranslatorFactory) constructor.newInstance();
            } catch (Exception e) {
                factory = new TranslatorFactoryBase();
            }
            return true;
        }
    );

    public static Translator getTranslator() {
        if(singletonReadyFactory.isReady()){
            return factory.createTranslator();
        }
        return null;
    }


    protected Translator translator = null;

    public Translator createTranslator() {
        return translator;
    }

}
