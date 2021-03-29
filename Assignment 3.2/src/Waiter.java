public class Waiter extends Thread{
    private String order;
    private Chef chef;

    public void setOrder(String order){
        this.order = order;
    }

    public void run(){
         /**
         * Need to set a new order whenever the first order is cooked
         * setCooked to false
         */

         while (true) {
             // stay here until new order ready to be processed
//             System.out.println("Inside Waiter");

//             while (Restaurant.order.equals("")) {
//                 try {
//                     Restaurant.lock.wait();
//                 } catch (IllegalMonitorStateException | InterruptedException e) {
//                     e.printStackTrace();
//                 }
//                 System.out.println("Waiter - Order is null? " + (Restaurant.order.equals("") ? "True " : "False "+ Restaurant.order) );
//             }

             synchronized(Restaurant.lock) {

                 if(Restaurant.order_State.equals(Restaurant.NEW_ORDER)) {

                     System.out.println("Waiter - Order is null? " + (Restaurant.order.equals("") ? "True " : "False " + Restaurant.order));

                     try {
                         Restaurant.lock.notify();
                         Restaurant.lock.wait();
                     } catch (IllegalMonitorStateException e) {
                         e.printStackTrace();
                     }catch(InterruptedException e){
                         System.out.println("Waiter has delivered order");
                     }
                 }
                 if (Restaurant.order_State.equals(Restaurant.FINISHED)) {

                     System.out.println("Waiter - has returned with order: " + Restaurant.order);
                     // nullify order just finished
                     Restaurant.order = "";
                     System.out.println("Waiter - order now NULL - Getting next order");
                     Restaurant.order_State = Restaurant.DELIVERED;
                     //chef.interrupt();
                     //this.interrupt();

                 }

             }

         }

    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public String getOrder() {
        return this.order;
    }

    public void killThread() {
        this.interrupt();

    }
}
