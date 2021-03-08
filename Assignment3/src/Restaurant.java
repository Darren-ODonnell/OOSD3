import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    int MAX_QUEUE_SIZE = 3;

    private List<Order> orders = new ArrayList<>();
    private Waiter waiter = new Waiter();
    private Chef chef = new Chef();
    private MainWindow gui = new MainWindow();

    public QueueManager qm = new QueueManager();


    public int orderCount = 3;

    public  Restaurant(){
        //User inputs the order they want
        //Order created
        //Waiter Picks up the order
        //Waiter passes the order to the chef and the chef starts a thread
        //Presume the meal prep takes a set amount of time
        //After this amount of time the chef thread should notify the waiter
        //waiter waits until chef notifies
        qm.init(MAX_QUEUE_SIZE);

        createSampleOrders();

        processOrders();

    }

    private void createSampleOrders() {
//        gui.buildOrders(orderCount);
        Order o3 = new Order(0,0,0);
        Order o2 = new Order(1,1,1);
        Order o1 = new Order(2,2,2);
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);

        // adding a couple of orders to queue
        qm.enqueue(o1);
        qm.enqueue(o2);
        qm.enqueue(o3);


        // When displaying in gui, html wrapping is necessary
        for(Order o : orders) {
            System.out.println(o.toHtml());
        }

        // simple output to screen
        // grab a list of orders as astring then print
        System.out.println(qm.toString());
        System.out.println("Order Count = " + qm.count);

    }

    private void processOrders() {

        for (Order o : orders) {
            waiter.setOrder(o);
            waiter.deliverOrder(chef);
            System.out.println("Chef has completed order: " + o.toString());
        }

    }

    public void AddOrder(Order order){
        orders.add(order);
    }

    synchronized public void sendToWaiter(Order order){

    }

    synchronized public void sendToChef(Order order){

        chef.setOrder(order);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}
