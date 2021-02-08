public class InterruptSleep {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try{
            myThread.interrupt();
        }catch (Exception e){
            System.out.println("Exception Handled " + e);
        }
    }
}

class MyThread extends Thread{
    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println("task");
        }catch(InterruptedException e){
            throw new RuntimeException("Sleeping Thread Interrupted");
        }
    }
}
