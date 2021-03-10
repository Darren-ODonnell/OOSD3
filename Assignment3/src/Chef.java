import java.util.concurrent.TimeUnit;

public class Chef extends Thread {
    private Order order = null;

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processOrder() {
        while (order != null && !order.isCooked()) {
            try {
                //Cooking
                System.out.println("inside try " + (order == null ? "True" : "False"));
                order.wait(3000);
                //Has Cooked
                order.setCooked(true);
                order.notify();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void run() {
        System.out.println("before while");
        System.out.println(order == null ? "True" : "False");

        sleep(1);

       // while (true) {

            if (order != null) {
                //synchronized (order) {
                    sleep(1);

                    System.out.println("inside sync " + (order == null ? "True" : "False"));
                    //            while(order != null && !order.isCooked()){
                    processOrder();
               // }
            }
        //}
    }

}
