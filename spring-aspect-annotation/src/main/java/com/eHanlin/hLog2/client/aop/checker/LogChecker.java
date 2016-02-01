package com.eHanlin.hLog2.client.aop.checker;

import com.eHanlin.hLog2.client.*;
import com.eHanlin.hLog2.client.aop.ann.LogAfter;
import com.eHanlin.hLog2.client.aop.ann.LogAround;
import com.eHanlin.hLog2.client.aop.ann.LogBefore;
import com.eHanlin.hLog2.client.aop.ann.LogException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@Aspect
@Order(2)
@Service
public class LogChecker {

    private Object[] getParameters(
            String[] parameterNames, Object[] parameterValues,
            String[] include, String[] exclude)
    {
        Map result = new HashMap();
        List list = new ArrayList<Object>();

        if(include.length == 0 && exclude.length == 0) {
            for(int i=0 ; i<parameterNames.length ; i++) {
                list.add(parameterNames[i]);
                list.add(parameterValues[i]);
            }
        } else if (include.length > 0) {
            List includeList = Arrays.asList(include);
            for(int i=0 ; i<parameterNames.length ; i++) {
                if(includeList.contains(parameterNames[i])) {
                    list.add(parameterNames[i]);
                    list.add(parameterValues[i]);
                }
            }
        } else if (exclude.length > 0) {
            List excludeList = Arrays.asList(exclude);
            for(int i=0 ; i<parameterNames.length ; i++) {
                if(!excludeList.contains(parameterNames[i])) {
                    list.add(parameterNames[i]);
                    list.add(parameterValues[i]);
                }
            }
        }

        return list.toArray();
    }

    private MethodSignature getMethodSignature(ProceedingJoinPoint pjp) {
        if(pjp.getSignature() instanceof MethodSignature){
            return (MethodSignature) pjp.getSignature();
        }
        return null;
    }

    private void sendLog(Logger logger, int level, MethodSignature signature, Object[] info, String advice) {
        logger
            .log(level)
            .intent(HLog2ThreadLocal.getIntent())
            .action(signature.getName())
            .info(info)
            .meta("advice", advice, "class", signature.getDeclaringTypeName(), "method", signature.getName())
            .send();
    }

    private void sendExceptionLog(Logger logger, int level, MethodSignature signature, Object[] info, String advice, Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        String stack = stringWriter.toString();
        printWriter.close();
        logger
            .log(level)
            .intent(HLog2ThreadLocal.getIntent())
            .action(signature.getName())
            .info(info)
            .meta("advice", advice, "class", signature.getDeclaringTypeName(), "method", signature.getName(), "exception", exception.toString(), "stack", stack)
            .send();
    }

    @Around(value="@annotation(logAround)")
    public Object checkAround(ProceedingJoinPoint pjp, LogAround logAround) throws Throwable {

        MethodSignature signature = getMethodSignature(pjp);

        if(signature != null){
            Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
            Object[] parameters = getParameters(
                    signature.getParameterNames(), pjp.getArgs(), logAround.include(), logAround.exclude());

            sendLog(logger, logAround.value(), signature, parameters, "before");

            Object result = null;
            try {
                result = pjp.proceed();
            } catch (Exception e){
                sendExceptionLog(logger, logAround.exceptionLevel(), signature, parameters, "exception", e);
                throw e;
            }

            sendLog(logger, logAround.value(), signature, parameters, "after");

            return result;

        } else {
            return pjp.proceed();
        }

    }

    @Around(value="@annotation(logBefore)")
    public Object checkBefore(ProceedingJoinPoint pjp, LogBefore logBefore) throws Throwable {

        MethodSignature signature = getMethodSignature(pjp);

        if(signature != null){
            Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
            Object[] parameters = getParameters(
                    signature.getParameterNames(), pjp.getArgs(), logBefore.include(), logBefore.exclude());

            sendLog(logger, logBefore.value(), signature, parameters, "before");

            Object result = null;
            try {
                result = pjp.proceed();
            } catch (Exception e){
                sendExceptionLog(logger, logBefore.exceptionLevel(), signature, parameters, "exception", e);
                throw e;
            }

            return result;

        } else {
            return pjp.proceed();
        }

    }

    @Around(value="@annotation(logAfter)")
    public Object checkAfter(ProceedingJoinPoint pjp, LogAfter logAfter) throws Throwable {

        MethodSignature signature = getMethodSignature(pjp);

        if(signature != null){
            Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
            Object[] parameters = getParameters(
                    signature.getParameterNames(), pjp.getArgs(), logAfter.include(), logAfter.exclude());

            Object result = null;
            try {
                result = pjp.proceed();
            } catch (Exception e){
                sendExceptionLog(logger, logAfter.exceptionLevel(), signature, parameters, "exception", e);
                throw e;
            }

            sendLog(logger, logAfter.value(), signature, parameters, "after");

            return result;

        } else {
            return pjp.proceed();
        }

    }

    @Around(value="@annotation(logException)")
    public Object checkException(ProceedingJoinPoint pjp, LogException logException) throws Throwable {

        MethodSignature signature = getMethodSignature(pjp);

        if(signature != null){
            Object result = null;
            try {
                result = pjp.proceed();
            } catch (Exception e){
                Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
                Object[] parameters = getParameters(
                        signature.getParameterNames(), pjp.getArgs(), logException.include(), logException.exclude());
                sendExceptionLog(logger, logException.value(), signature, parameters, "exception", e);
                throw e;
            }

            return result;

        } else {
            return pjp.proceed();
        }

    }

}
