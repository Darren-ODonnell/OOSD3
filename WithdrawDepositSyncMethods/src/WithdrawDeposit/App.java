package WithdrawDeposit;

public class App {

    public static void main(String[] args) {
        final Processor processor = new Processor();

        Thread t1 = new Thread (new Runnable(){

            @Override
            public void run() {
                try {
                    processor.withdraw();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread (new Runnable(){

            @Override
            public void run() {
                try {
                    processor.deposit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
