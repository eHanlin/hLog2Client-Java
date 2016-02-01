package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.json.JsonTranslator;

public class TranslatorFactoryImpl extends TranslatorFactory {

    public TranslatorFactoryImpl() {
        this.translator = new JsonTranslator();
    }

}
