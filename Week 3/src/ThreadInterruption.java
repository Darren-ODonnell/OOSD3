public class ThreadInterruption extends Thread {

    public void run(){
        for(int i = 1;i <= 10; i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        ThreadInterruption t1 = new ThreadInterruption();
        t1.start();
        t1.interrupt();
    }

}
