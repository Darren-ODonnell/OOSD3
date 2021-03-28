package Temporary;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.awt.*;



public class Restaurant {
    public static boolean orderComplete;

    Display window = new Display();
    Input input = new Input();

    static Order order = null;

    public static Order_State  order_state = new Order_State();

    public static AtomicInteger NO_ORDER = new AtomicInteger(-2);
    public static AtomicInteger BEFORE = new AtomicInteger(-1);
    public static AtomicInteger COOKING = new AtomicInteger(0);
    public static AtomicInteger FINISHED = new AtomicInteger(1);
    public static AtomicInteger DELIVERED = new AtomicInteger(2);
    public static AtomicInteger EXIT = new AtomicInteger(10);

    MainWindow mainWindow = new MainWindow();
    GuiObserver observer = new GuiObserver(mainWindow);



    static final Object lock = new Object();

    Waiter waiter = new Waiter();
    Chef chef = new Chef();


    public Restaurant() throws InterruptedException {
        //Input orders
        //Start threads

        sleep(4);
        order_state.addObserver(observer);
        order_state.setState(NO_ORDER); // start with NO Order
        mainWindow.buildOrders(1);
//        while(order.equals("")) {
//            order = input.string("Enter your order: ") ;
//            orderComplete = false;
//        }

        //order = input.string("Enter your order: ");

//        waiter.setChef(chef);
        if(!waiter.isAlive()) waiter.start();
        if(!chef.isAlive()) chef.start();

        while(!order_state.equals(EXIT)) { // this state comes from the gui.

            while (order != null) {
                if(order_state.equals(NO_ORDER)) {
                    order_state.setState(BEFORE);
                }
            }
            if (order_state.equals(DELIVERED)) {
                System.out.print("");
                order = null;
                order_state.setState(NO_ORDER);
            }
        }
        // stop interrupts and exit program
        chef.interrupt();
        waiter.interrupt();

        mainWindow.setVisible(false);
        JLabel label = new JLabel("Thank you for eating with us");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        JOptionPane.showMessageDialog(null, label);
        System.exit(0);
    }


    public static void main(String[] args) throws InterruptedException {
        new Restaurant();
    }

    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.println("");
        }
    }

}
