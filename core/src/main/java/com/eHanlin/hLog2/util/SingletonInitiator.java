package com.eHanlin.hLog2.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

public class SingletonInitiator {

    private boolean readied = false;

    private Lock lock = new ReentrantLock();

    private BooleanSupplier initLambda = null;

    public SingletonInitiator(BooleanSupplier initLambda) {
        this.initLambda = initLambda;
    }

    public boolean isReady(){
        lock.lock();
        try{
            if(!readied){
                readied = initLambda.getAsBoolean();
            }
            return readied;
        }finally {
            lock.unlock();
        }
    }
}
