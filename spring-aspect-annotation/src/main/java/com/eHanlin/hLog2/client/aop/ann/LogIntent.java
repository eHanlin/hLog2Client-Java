package com.eHanlin.hLog2.client.aop.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配合其他 Log Annotation 使用，會自動寫入 intent。
 * 一次 request 中，若有呼叫到二個以上的 LogIntent，
 * 後呼叫到的會蓋掉前面的。
 * 建議只在 @RequestMapping 時使用。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogIntent {
    String value();
}
