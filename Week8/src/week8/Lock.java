package week8;

import java.util.ArrayList;
import java.util.List;

public class Lock {

    private boolean isLocked = false;
    private Thread lockingThread = null;

    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock(){
        isLocked = false;
        lockingThread = null;
        notify();
    }
}
