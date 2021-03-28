package Temporary;

public class Chef extends Thread {

    public Chef() {
    }

    public void run() {

        Restaurant.sleep(2);

        synchronized (Restaurant.lock) {

            while (true) {
                while (Restaurant.order == null) {

                    try {
                        Restaurant.lock.wait();
                    } catch (IllegalMonitorStateException e) {
                        e.printStackTrace();
                    } catch(InterruptedException ex){
                        System.out.print("");
                    }

                }
                Restaurant.sleep(1);

                if(Restaurant.order_state.equals(Restaurant.BEFORE)) {

                    try {
                        // Cooking
                        Restaurant.order_state.setState(Restaurant.COOKING);

                        Restaurant.sleep(3);

                        Restaurant.order_state.setState(Restaurant.FINISHED);
                        Restaurant.sleep(1);
                        Restaurant.lock.notify();

                        Restaurant.lock.wait();

                    } catch (IllegalMonitorStateException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        System.out.print("");
                        System.exit(0);
                    }
                }
            }
        }
    }

}
