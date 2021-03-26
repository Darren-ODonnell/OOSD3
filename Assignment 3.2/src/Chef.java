public class Chef extends Thread {
    private String order = null;

    public void setOrder(String order) {
        this.order = this.order;
    }

    public void run() {

        Restaurant.sleep(2);

        while (true) {
            // wait until order is available
            //System.out.println("Inside Chef");

            while (Restaurant.order.equals("") ) {
                try {
                     Restaurant.lock.wait();
                } catch (IllegalMonitorStateException | InterruptedException e) {
//                    e.printStackTrace();
                }
                //System.out.println("Chef - inside while  " + (Restaurant.order.equals("") ? "True" : "False"+ " - " + Restaurant.order) );
            }
            synchronized (Restaurant.lock) {
                Restaurant.order_State = Restaurant.COOKING;

                System.out.println("Chef - inside sync " + (Restaurant.order.equals("") ? "True" : "False"+ " - " + Restaurant.order) );
                try {
                    // Cooking
                    System.out.println("Chef - inside try " + (Restaurant.order.equals("") ? "True" : "False"+ " - " + Restaurant.order) );
                    // cooking time
                    System.out.println("Chef - Preparing Order");
                    Restaurant.lock.wait(3000);
                    // Has Cooked
                    // trigger to tell waiter to collect and deliver order
                    // trigger for next order top be released
                    Restaurant.orderComplete = true;
                    // tell waiter order ios free to use.
                    Restaurant.order_State = Restaurant.FINISHED;
                    System.out.println("Chef - Order Complete");
                    Restaurant.lock.notify();
                    Restaurant.lock.wait();

                } catch (IllegalMonitorStateException e) {
                    e.printStackTrace();
                }catch(InterruptedException e){
                    System.out.println("Chef has cooked order");
                    System.exit(0);
                }
            }
        }
    }
    public void killThread() {
        this.interrupt();
    }

}
