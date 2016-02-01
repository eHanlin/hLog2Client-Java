package com.eHanlin.hLog2.client;

import com.eHanlin.hLog2.client.json.JsonTranslator;
import org.junit.Assert;
import org.junit.Test;

public class TranslatorFactoryImplTest {

    @Test
    public void socketInstance() {
        Assert.assertTrue(TranslatorFactory.getTranslator() instanceof JsonTranslator);
    }

    @Test
    public void translate() {

        LauncherBase launcher = new LauncherBase();
        Translator translator = new JsonTranslator();
        Logger logger = new LoggerBase(launcher, translator);

        logger.log(40).session("5302d164300457e8ef92f300").intent("intent").action("action").target("target", "target").info("info", "info").meta("meta", "meta").send();

        String log = launcher.lastLog();
        Assert.assertTrue(log.indexOf("\"level\":40") >= 0);
        Assert.assertTrue(log.indexOf("\"session\":\"5302d164300457e8ef92f300\"") >= 0);
        Assert.assertTrue(log.indexOf("\"intent\":\"intent\"") >= 0);
        Assert.assertTrue(log.indexOf("\"action\":\"action\"") >= 0);
        Assert.assertTrue(log.indexOf("\"target\":{\"target\":\"target\"}") >= 0);
        Assert.assertTrue(log.indexOf("\"info\":{\"info\":\"info\"}") >= 0);
        Assert.assertTrue(log.indexOf("\"meta\":{\"meta\":\"meta\"}") >= 0);
        Assert.assertTrue(log.indexOf("\"class\":\"com.eHanlin.hLog2.client.TranslatorFactoryImplTest\"") >= 0);
        Assert.assertTrue(log.indexOf("\"method\":\"translate\"") >= 0);
        Assert.assertTrue(log.indexOf("\"line\":") >= 0);
        Assert.assertTrue(log.indexOf("\"createDate\":") >= 0);

        System.out.println(log);
    }

}
