package week8;

import java.util.ArrayList;
import java.util.List;

public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException{
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this){
            waitingThreads.add(queueObject);
        }
        while(isLockedForThisThread){
            synchronized (this){
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if(!isLockedForThisThread){
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            queueObject.wait();
        }
    }

    public synchronized void unlock(){
        isLocked = false;
        lockingThread = null;
        waitingThreads.get(0).notify();
    }
}


class QueueObject {
}

