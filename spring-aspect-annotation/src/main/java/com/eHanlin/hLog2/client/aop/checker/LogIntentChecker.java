package com.eHanlin.hLog2.client.aop.checker;

import com.eHanlin.hLog2.client.HLog2ThreadLocal;
import com.eHanlin.hLog2.client.aop.ann.LogIntent;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Aspect
@Order(1)
@Service
public class LogIntentChecker {

    @Before("@annotation(logIntent)")
    public void insertIntent(LogIntent logIntent) {
        HLog2ThreadLocal.setIntent(logIntent.value());
    }

}
