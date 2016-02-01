package com.eHanlin.hLog2.client.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextSocketClient {

    protected String host = null;
    protected Integer port = null;

    private Lock lock = new ReentrantLock();
    private Socket socket = null;
    private PrintWriter out = null;


    public TextSocketClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    protected void connect() {
        lock.lock();
        try{
            while (socket == null || !socket.isConnected() || socket.isClosed() || out.checkError()){
                try{
                    socket = new Socket(host, port);
                    socket.setKeepAlive(true);
                    out = new PrintWriter(socket.getOutputStream());
                } catch (Exception e){
                    try{
                        Thread.sleep(100L);
                    } catch (Exception ex) {

                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void send(String text) {

        boolean sendFail = false;

        do {
            lock.lock();
            try{
                if(out.checkError()){
                   throw new IOException("Socket lose connect");
                }

                out.println(text);
                out.flush();

                if(out.checkError()){
                    throw new IOException("Socket lose connect");
                }

                sendFail = false;

            } catch (Exception e){
                sendFail = true;
            } finally {
                lock.unlock();
            }
            if(sendFail) {
                connect();
            }
        } while (sendFail);

    }
}
