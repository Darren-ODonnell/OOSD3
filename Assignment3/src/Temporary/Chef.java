package Temporary;

public class Chef extends Thread {
    private Order order = null;

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public void run() {


            System.out.println("Chef - inside sync " + (order == null ? "True" : "False"));
            while (order != null && !order.isCooked()) {
                synchronized (order) {
                try {
                    this.sleep(1000);

                    //Cooking
                    System.out.println("Chef - inside try " + (order == null ? "True" : "False"));
                    order.wait(5000);
                    //Has Cooked
                    order.setCooked(true);
                    order.notify();
                } catch (InterruptedException | IllegalMonitorStateException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
