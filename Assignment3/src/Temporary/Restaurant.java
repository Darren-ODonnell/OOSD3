package Temporary;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Restaurant {


    private List<Order> orders = new ArrayList<>();
    private Waiter waiter = new Waiter();
    private Chef chef = new Chef();
    private MainWindow gui = new MainWindow();
    public static  boolean orderComplete = false;

    // only one static copy of order is used throughout the program
    public static Order order = null;

    public int orderCount = 3;

    public  Restaurant(){
        //User inputs the order they want
        //Order created
        //Waiter Picks up the order
        //Waiter passes the order to the chef and the chef starts a thread
        //Presume the meal prep takes a set amount of time
        //After this amount of time the chef thread should notify the waiter
        //waiter waits until chef notifies
//
        // create
        createSampleOrders();

        processOrders();

    }
    public static void sleep(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void createSampleOrders() {
//        gui.buildOrders(orderCount);
        Order o3 = new Order(0,0,0);
        Order o2 = new Order(1,1,1);
        Order o1 = new Order(2,2,2);
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);

    }

    private void processOrders() {

        for (Order o : orders) {
            orderComplete = false;
            order = o;
            System.out.println("Restaurant - New Order - "+order.toString());
            // start services if not already started
            if(!waiter.isAlive()) waiter.start();
            if(!chef.isAlive()) chef.start();

            // wait until order has benn completed
            while(!orderComplete) sleep(1);

        }

        System.out.println("Restaurant - All Orders Processed");
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
