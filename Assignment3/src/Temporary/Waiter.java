package Temporary;
public class Waiter extends Thread{
    private Order order;
    private Chef chef;

    public Waiter() {

    }

    public void setOrder(Order order){
        this.order = order;
    }

    public void run(){
         /**
         * Need to set a new order whenever the first order is cooked
         * setCooked to false
         */

         while (true) {
             // stay here until new order ready to be processed
             while (Restaurant.order == null || Restaurant.orderComplete) {
                 // 1ms wait each loop
                 Restaurant.sleep(1);
             }
             synchronized(Restaurant.order) {
                 System.out.println("Waiter - Order is null? " + (Restaurant.order == null ? "True " : "False ") + Restaurant.order.toString());

                 try {
                     Restaurant.order.notify();
                     Restaurant.order.wait();
                 } catch (IllegalMonitorStateException | InterruptedException e) {
                     e.printStackTrace();
                 }

             }
             if (Restaurant.order.isCooked()) {
                 System.out.println("Waiter - has returned with order: " + Restaurant.order.toString());
                 // nullify order just finished
                 Restaurant.order = null;
                 Restaurant.orderComplete = true;
                 System.out.println("Waiter - order now NULL - Getting next order");
             }
         }
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}
