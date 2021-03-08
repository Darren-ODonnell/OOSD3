public class Waiter {
    private Order order;

    public Waiter() {

    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return this.order;
    }

    public void displayOrder(){

    }

    public void deliverOrder(Chef chef){
            while(!order.isCooked()){

                    System.out.println("\n\nWaiter before cooked: " + order.toString());
                    //Passes the order to the chef, boolean in chef requires this to occur first;
                    chef.setOrder(order);
                    chef.prepareOrder();
                    //Wait for chef to finish preparing the meal
            }
            System.out.println("Waiter has returned with order: " + order.toString());
            order.setCooked(false);

    }
}
