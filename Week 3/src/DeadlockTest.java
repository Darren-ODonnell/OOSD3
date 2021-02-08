public class DeadlockTest {
    public static void main(String[] args) {
        final String resource1 = "Hello World";
        final String resource2 = "Goodbye World";

        Thread t1 = new Thread(){
            public void run(){
                synchronized (resource1){
                    System.out.println("Thread 1: locked resource 1");
                    try{Thread.sleep(100);}catch(Exception e){}

                    synchronized (resource2){
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                synchronized (resource2){
                    System.out.println("Thread 2: locked resource 2");
                    try{Thread.sleep(100);}catch(Exception e){}

                    synchronized (resource1){
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        t1.start();
        t2.start();

    }
}
