package Temporary;

public class Waiter extends Thread{

    Display window = new Display();

    public Waiter()  {
    }

    public void run(){
         /**
         * Need to set a new order whenever the first order is cooked
         * setCooked to false
         */

         while (true) {

             synchronized(Restaurant.lock) {

                 // waiter acts on two states
                 // first - New order received - BEFORE
                 // second - when order is FINISHED
                 while(Restaurant.order_state.equals(Restaurant.BEFORE)) {
                     try {
                         Restaurant.lock.notify();
                         Restaurant.lock.wait();
                     } catch (IllegalMonitorStateException e) {
                         e.printStackTrace();
                     } catch (InterruptedException e){
                         System.out.println("Waiter has delivered order to Chef");
                     }
                 }
                 if (Restaurant.order_state.equals(Restaurant.FINISHED)) {
                     Restaurant.order_state.setState(Restaurant.DELIVERED);
                     window.showMessage("Waiter has returned with order: \n" + Restaurant.order.toString());
                     // nullify order just finished
                     Restaurant.order = null;
                 }
             }
         }
    }
}
