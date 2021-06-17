package com.example.joystickremotecontrol.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Model {
    BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<Runnable>();
    Socket fg;
    PrintWriter out;
    Boolean stop = false;
    public Model(String ip, int port) throws IOException {
        fg = new Socket(ip, port);
        out=new PrintWriter(fg.getOutputStream(),true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!stop){
                    try{
                        // take() blocks, so no busy waitingv
                        dispatchQueue.take().run();
                    }catch (InterruptedException e){}
                }
            }
        }).start();
    }
    //value between -1, 1
    public void setAileron(float value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
                              @Override
                              public void run() {
                                  out.print("set /controls/flight/aileron "+value+"\r\n");
                                  out.flush();
                              }
                          });
    }
    //value between -1, 1
    public void setElevator(float value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/elevator "+value+"\r\n");
                out.flush();
            }
        });
    }
    //value between -1, 1
    public void setRudder(float value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/rudder "+value+"\r\n");
                out.flush();
            }
        });
    }
    //value between 0, 1
    public void setThrottle(float value) throws InterruptedException {
        dispatchQueue.put(new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/engines/current-engine/throttle "+value+"\r\n");
                out.flush();
            }
        });
    }
}
