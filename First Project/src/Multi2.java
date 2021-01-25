public class Multi2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) {
        Multi2 multi = new Multi2();
        Thread thread = new Thread(multi);
        thread.start();
    }
}
