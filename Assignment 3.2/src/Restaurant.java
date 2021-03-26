import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    public static boolean orderComplete;

    Display window = new Display();
    Input input = new Input();
    static String order = "";
    public static final AtomicInteger NO_ORDER = new AtomicInteger(-2);
    public static final AtomicInteger BEFORE = new AtomicInteger(-1);
    public static final AtomicInteger COOKING = new AtomicInteger(0);
    public static final AtomicInteger DELIVERED = new AtomicInteger(1);
    public static final AtomicInteger FINISHED = new AtomicInteger(2);

    public static AtomicInteger order_State = DELIVERED;



    static final Object lock = new Object();

    Waiter waiter = new Waiter();
    Chef chef = new Chef();

    public Restaurant() throws InterruptedException {
        //Input orders
        //Start threads



//        while(order.equals("")) {
//            order = input.string("Enter your order: ") ;
//            orderComplete = false;
//        }


        //order = input.string("Enter your order: ");

        waiter.setChef(chef);
        if(!waiter.isAlive()) waiter.start();
        if(!chef.isAlive()) chef.start();

        while(!order.equals("exit")) {
            System.out.print("");
            if(order_State.equals(DELIVERED)) {
                order_State = NO_ORDER;

            }
            if(order_State.equals( NO_ORDER)) {
                order = input.string("Enter your order: ");
                order_State = BEFORE;
            }
//            sleep(1);

//            if (DELIVERED.equals(Restaurant.order_State)) {
//                order = input.string("Enter your order: ");
//                order_State = BEFORE;
//            } else if (BEFORE.equals(Restaurant.order_State)) {
//            } else if (COOKING.equals(Restaurant.order_State)) {
//                window.showMessage("Cooking");
//            } else if (FINISHED.equals(Restaurant.order_State)) {
//                window.showMessage("Finished");
//            }


               //Gives time for chef to cook meal
//               sleep(4);

        }
        System.out.println("Order Complete - Treads stopped");



        //Set order inside waiter

        //Display order cooked

        //window.showMessage("Order has returned from chef: " + order);
    }
    public static void main(String[] args) throws InterruptedException {
        new Restaurant();
    }

    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
