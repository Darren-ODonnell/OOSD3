public class TestNamingThreads extends Thread{

    public void run(){
        System.out.println("running...");
    }

    public static void main(String[] args) {
        TestNamingThreads thread1 = new TestNamingThreads();
        TestNamingThreads thread2 = new TestNamingThreads();

        System.out.println("Name of thread1: " + thread1.getName());
        System.out.println("Name of thread2: " + thread2.getName());

        thread1.start();
        thread2.start();
        thread1.setName("OOSD3 thread");
        System.out.println("After Changing thread1: " + thread1.getName());
    }
}
