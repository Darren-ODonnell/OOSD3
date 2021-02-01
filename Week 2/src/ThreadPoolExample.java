import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample{

    public static void main(String[] args) {

        //Creates pool of 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++){
            Runnable workerThread = new WorkerThread("Message " + i);
            executor.execute(workerThread);
        }
        executor.shutdown();
        System.out.println("Finished with all threads");
    }
}

class WorkerThread implements Runnable{

    private String message;

    public WorkerThread(String message){
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + ", Start message is: " + this.message);

        processMessage();

        System.out.println(Thread.currentThread().getName()
                + " is ending");
    }

    private void processMessage(){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
