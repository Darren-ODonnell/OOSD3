package Temporary;
import java.util.concurrent.TimeUnit;

public class Chef extends Thread {
    private Order order = null;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void run() {

        Restaurant.sleep(2);

        while (true) {
            // wait until order is available
            while (Restaurant.order == null || Restaurant.orderComplete) {
                Restaurant.sleep(1);
            }
            synchronized (Restaurant.order) {
                System.out.println("Chef - inside sync " + (Restaurant.order == null ? "True" : "False") + " - " + Restaurant.order.toString());
                try {
                    // Cooking
                    System.out.println("Chef - inside try " + (Restaurant.order == null ? "True" : "False") + Restaurant.order.toString());
                    // cooking time
                    Restaurant.order.wait(3000);
                    // Has Cooked
                    // trigger to tell waiter to collect and deliver order
                    Restaurant.order.setCooked(true);
                    // trigger for next order top be released
                    Restaurant.orderComplete = true;
                    // tell waiter order ios free to use.
                    Restaurant.order.notify();

                } catch (InterruptedException | IllegalMonitorStateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
