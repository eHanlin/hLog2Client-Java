package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.Test;

public class TranslatorFactoryBaseTest {

    @Test
    public void baseInstance() {
        Assert.assertTrue(TranslatorFactory.getTranslator() instanceof TranslatorBase);
    }

}
