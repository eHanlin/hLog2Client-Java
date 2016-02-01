package com.eHanlin.hLog2.client.aop.ann;

import com.eHanlin.hLog2.client.Level;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 於方法執行發生例外時寫 Log，
 * 會使用 logAdvice 來記錄 exception，
 * include 和 exclude 是互斥的，
 * 若都有值，只使用 include
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogException {
    int value() default Level.ERROR;
    String[] include() default {};
    String[] exclude() default {};
}
