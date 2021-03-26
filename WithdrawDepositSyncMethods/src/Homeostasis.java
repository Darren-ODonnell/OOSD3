public class Homeostasis {

    private int balance = 0;

    public static void main(String[] args) {
        Homeostasis h = new Homeostasis();
        h.goingThroughLife();
    }

    public void goingThroughLife(){
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i< 10000; i++){
                    add();
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i< 10000; i++){
                    subtract();
                }
            }
        });
        t1.start();
        t2.start();

        System.out.println("Balance: " + balance);
    }

    public synchronized void add(){
        balance++;
    }

    public synchronized void subtract(){
        balance--;
    }
}
