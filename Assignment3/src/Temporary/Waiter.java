package Temporary;

import javafx.scene.control.CustomMenuItem;

import javax.swing.*;
import java.awt.*;

public class Waiter extends Thread{
    private Order order;
    private Chef chef;
    MainWindow mainWindow;

    public Waiter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
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
             while (mainWindow.order == null || Restaurant.orderComplete) {
                 mainWindow.changeLabelState(mainWindow.deliverToChef);

                 // 1ms wait each loop
                 Restaurant.sleep(1);
                 System.out.println("Waiter - Order is null? " + (mainWindow.order == null ? "True " : "False "+ mainWindow.order.toString()) );
             }

             synchronized(mainWindow.order) {

                 System.out.println("Waiter - Order is null? " + (mainWindow.order == null ? "True " : "False "+ mainWindow.order.toString()) );

                 try {

                     mainWindow.changeLabelState(mainWindow.deliverToChef);
                     mainWindow.order.notify();
                     mainWindow.order.wait();
                 } catch (IllegalMonitorStateException | InterruptedException e) {
                     e.printStackTrace();
                 }

             }
             if (mainWindow.order.isCooked()) {
                 mainWindow.changeLabelState(mainWindow.deliveredToCustomer);
                 JLabel label = new JLabel("Waiter has returned with order: " + mainWindow.order.toString());
                 label.setFont(new Font("Serif", Font.BOLD, 18));
                 JOptionPane.showMessageDialog(null, label);

                 mainWindow.changeLabelState(mainWindow.deliveredToCustomer);
                 System.out.println("Waiter - has returned with order: " + mainWindow.order.toString());
                 // nullify order just finished
                 mainWindow.order = null;
                 Restaurant.orderComplete = true;
                 System.out.println("Waiter - order now NULL - Getting next order");
             }
         }
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Order getOrder() {
        return this.order;
    }
}
