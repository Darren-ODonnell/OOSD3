package Temporary;

public class Waiter extends Thread{
    private Order order;
    private Chef chef;

    public Waiter() {

    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return this.order;
    }

    public void displayOrder(){

    }

    public void run(){
        /**
         * Need to set a new order whenever the first order is cooked
         * setCooked to false
         */
        synchronized (order) {
            while (order != null && !order.isCooked()) {

                System.out.println("\nWaiter - before cooked: " + order.toString());
                //Passes the order to the chef, boolean in chef requires this to occur first;
                System.out.println("Waiter - Order is null? " + (order == null ? "True" : "False"));

                try {
//                    this.sleep(1000);
//                    order.wait(1000);
                    chef.setOrder(order);

                    order.notify();
                    order.wait();
                } catch (IllegalMonitorStateException | InterruptedException e) {
                    e.printStackTrace();
                }
                //chef.prepareOrder();
                //Wait for chef to finish preparing the meal
                if (order.isCooked()) {
                    System.out.println("Waiter - has returned with order: " + order.toString());
                }
            }
        }

    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}
