package Temporary;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    List<Order> orders = new ArrayList<>();
    private Order order = new Order();
    private Waiter waiter = new Waiter();
    private Chef chef = new Chef();
   // private MainWindow gui = new MainWindow();


    public Restaurant(){
        //User inputs the order they want
        //Order created
        //Waiter Picks up the order
        waiter.start();
        chef.start();

        //Waiter passes the order to the chef and the chef starts a thread
        //Presume the meal prep takes a set amount of time
        //After this amount of time the chef thread should notify the waiter
        //waiter waits until chef notifies
        createSampleOrders();

        processOrders();
    }
    private void createSampleOrders() {
//        gui.buildOrders(orderCount);
        Order o3 = new Order(0,0,0);
        Order o2 = new Order(1,1,1);
        Order o1 = new Order(2,2,2);
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);

        // When displaying in gui, html wrapping is necessary
        for(Order o : orders) {
            System.out.println(o.toHtml());
        }

    }

    private void processOrders() {
        for (Order o : orders) {
            waiter.setOrder(o);
            waiter.setChef(chef);
            //while(!o.isCooked()) ;
        }

    }

    public void AddOrder(Order order){
        orders.add(order);
    }

    synchronized public void sendToWaiter(Order order){

    }

    synchronized public void sendToChef(Order order){

        chef.setOrder(order);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}
