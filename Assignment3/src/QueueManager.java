import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueManager implements Queue {
    public Queue<Order> queue;
    public int limit = 0;
    public int count = 0;
    
    public QueueManager(){
        
    }
    
    public void init(int max){
        limit = max;
        clear();
    }

    public void enqueue(Object o){
        if(count < limit ){
            queue.add(  (Order)o);
            count++;
        }else{
            System.out.println("Cannot add, queue full");
        }
    }

    public Order dequeue(){
        Order order = null;
        if(count > 0) {
            order = (Order) queue.remove();
            count--;
        } else {
            System.out.println("No Orders in Queue - Queue ie Empty");
        }
        return order;
    }

    public String toString() {
        String list="";
        Iterator<Order> it = queue.iterator();

        // hasNext() returns true if the queue has more elements
        while (it.hasNext())
        {
            // next() returns the next element in the iteration
            list +=it.next().toString()+"\n";
        }


        return list;
    }

    @Override
    public void clear() {
        queue = new LinkedList<>();
        count = 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return (count==0);
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }



    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }



    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }







}
