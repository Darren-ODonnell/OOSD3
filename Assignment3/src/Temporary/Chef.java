package Temporary;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Chef extends Thread {
    private Order order = null;
    MainWindow mainWindow;

    Chef(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    public void setOrder(Order order) {
        this.order = this.order;
    }

    public void run() {

        Restaurant.sleep(2);

        while (true) {
            // wait until order is available
            while (mainWindow.order == null || Restaurant.orderComplete) {
                Restaurant.sleep(1);
                System.out.println("Chef - inside while  " + (mainWindow.order == null ? "True" : "False"+ " - " + mainWindow.order.toString()) );
            }
            synchronized (mainWindow.order) {

                mainWindow.changeLabelState(mainWindow.cooking);

                System.out.println("Chef - inside sync " + (mainWindow.order == null ? "True" : "False"+ " - " + mainWindow.order.toString()) );
                try {
                    // Cooking
                    System.out.println("Chef - inside try " + (mainWindow.order == null ? "True" : "False"+ mainWindow.order.toString()) );
                    // cooking time
                    mainWindow.order.wait(3000);
                    // Has Cooked
                    // trigger to tell waiter to collect and deliver order
                    mainWindow.order.setCooked(true);
                    // trigger for next order top be released
                    Restaurant.orderComplete = true;
                    mainWindow.changeLabelState(mainWindow.cooking);
                    // tell waiter order ios free to use.
                    mainWindow.order.notify();

                } catch (InterruptedException | IllegalMonitorStateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
