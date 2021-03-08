public class Chef extends Thread {
    private Order order = null;

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public void prepareOrder() {
        synchronized (order) {
            while(order != null && !order.isCooked()){
                try {
                    //Cooking
                    order.wait(5000);
                    //Has Cooked
                    order.setCooked(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                order.notifyAll();
            }
        }
    }
}
