package Temporary;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Restaurant {

    private static MainWindow mainWindow = new MainWindow();

    private static Waiter waiter = new Waiter(mainWindow);
    private static Chef chef = new Chef(mainWindow);
    public static  boolean orderComplete = false;

    // only one static copy of order is used throughout the program
    public static Order order = null;

    public int orderCount = 0;

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
//        createSampleOrders();
//        mainWindow.buildOrders(orderCount);
//        if (!waiter.isAlive()) waiter.start();
//        if (!chef.isAlive()) chef.start();

        // start gui

        // start processing orders
        processOrders();

    }
    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Waiter getWaiter() {
        return waiter;
    }
    public static Chef getChef() {
        return chef;
    }


    public void processOrders() {

        // run gui - to create order in mainWindow.order

        mainWindow.buildOrders(1);
        System.out.println("Order : " + mainWindow.order.toString());

        // start threads to process order

        // on order completion
        // interrupt both threads to stop them
        // set order to null
        // loop to next order.



        }


//    public void AddOrder(Order order){
//        orders.add(order);
//    }

    synchronized public void sendToWaiter(Order order){

    }

    synchronized public void sendToChef(Order order){

        chef.setOrder(mainWindow.order);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}
