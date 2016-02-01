package com.eHanlin.hLog2.client;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggerBaseTest {

    public static LauncherBase launcher = null;
    public static Translator translator = null;
    public static LoggerBase logger = null;

    @BeforeClass
    public static void setupClass() {
        launcher = new LauncherBase();
        translator = new TranslatorBase();
        logger = new LoggerBase(launcher, translator);
    }

    @Test
    public void errorLogInstance() {

        logger.setLevel(Level.ERROR);

        Assert.assertTrue(logger.error() instanceof LogBase);
        Assert.assertTrue(logger.warn() instanceof NullLogBase);
        Assert.assertTrue(logger.info() instanceof NullLogBase);
        Assert.assertTrue(logger.debug() instanceof NullLogBase);
        Assert.assertTrue(logger.trace() instanceof NullLogBase);
    }

    @Test
    public void errorLogEnabled() {

        logger.setLevel(Level.ERROR);

        Assert.assertTrue(logger.isErrorEnabled());
        Assert.assertFalse(logger.isWarnEnabled());
        Assert.assertFalse(logger.isInfoEnabled());
        Assert.assertFalse(logger.isDebugEnabled());
        Assert.assertFalse(logger.isTraceEnabled());
    }


    @Test
    public void warnLogInstance() {

        logger.setLevel(Level.WARN);

        Assert.assertTrue(logger.error() instanceof LogBase);
        Assert.assertTrue(logger.warn() instanceof LogBase);
        Assert.assertTrue(logger.info() instanceof NullLogBase);
        Assert.assertTrue(logger.debug() instanceof NullLogBase);
        Assert.assertTrue(logger.trace() instanceof NullLogBase);
    }

    @Test
    public void warnLogEnabled() {

        logger.setLevel(Level.WARN);

        Assert.assertTrue(logger.isErrorEnabled());
        Assert.assertTrue(logger.isWarnEnabled());
        Assert.assertFalse(logger.isInfoEnabled());
        Assert.assertFalse(logger.isDebugEnabled());
        Assert.assertFalse(logger.isTraceEnabled());
    }


    @Test
    public void infoLogInstance() {

        logger.setLevel(Level.INFO);

        Assert.assertTrue(logger.error() instanceof LogBase);
        Assert.assertTrue(logger.warn() instanceof LogBase);
        Assert.assertTrue(logger.info() instanceof LogBase);
        Assert.assertTrue(logger.debug() instanceof NullLogBase);
        Assert.assertTrue(logger.trace() instanceof NullLogBase);
    }

    @Test
    public void infoLogEnabled() {

        logger.setLevel(Level.INFO);

        Assert.assertTrue(logger.isErrorEnabled());
        Assert.assertTrue(logger.isWarnEnabled());
        Assert.assertTrue(logger.isInfoEnabled());
        Assert.assertFalse(logger.isDebugEnabled());
        Assert.assertFalse(logger.isTraceEnabled());
    }

    @Test
    public void debugLogInstance() {

        logger.setLevel(Level.DEBUG);

        Assert.assertTrue(logger.error() instanceof LogBase);
        Assert.assertTrue(logger.warn() instanceof LogBase);
        Assert.assertTrue(logger.info() instanceof LogBase);
        Assert.assertTrue(logger.debug() instanceof LogBase);
        Assert.assertTrue(logger.trace() instanceof NullLogBase);
    }

    @Test
    public void debugLogEnabled() {

        logger.setLevel(Level.DEBUG);

        Assert.assertTrue(logger.isErrorEnabled());
        Assert.assertTrue(logger.isWarnEnabled());
        Assert.assertTrue(logger.isInfoEnabled());
        Assert.assertTrue(logger.isDebugEnabled());
        Assert.assertFalse(logger.isTraceEnabled());
    }

    @Test
    public void traceLogInstance() {

        logger.setLevel(Level.TRACE);

        Assert.assertTrue(logger.error() instanceof LogBase);
        Assert.assertTrue(logger.warn() instanceof LogBase);
        Assert.assertTrue(logger.info() instanceof LogBase);
        Assert.assertTrue(logger.debug() instanceof LogBase);
        Assert.assertTrue(logger.trace() instanceof LogBase);
    }

    @Test
    public void traceLogEnabled() {

        logger.setLevel(Level.TRACE);

        Assert.assertTrue(logger.isErrorEnabled());
        Assert.assertTrue(logger.isWarnEnabled());
        Assert.assertTrue(logger.isInfoEnabled());
        Assert.assertTrue(logger.isDebugEnabled());
        Assert.assertTrue(logger.isTraceEnabled());
    }

}
