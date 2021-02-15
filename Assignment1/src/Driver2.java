public class Driver2 {
    Counter2 counter;
    Publisher2 publisher;
    static Repository2 repo;

    public Driver2()  {

        repo = new Repository2();
        counter = new Counter2();
        publisher = new Publisher2();

        publisher.start();
        counter.start();


//        int i = 0;
//        while(i++ < 10){
//
//            counter.increment();
//            publisher.print();
//
//        }
    }

    public static void main(String[] args) {
        new Driver2();
    }
}

class Publisher2 extends Thread{
    //Takes in the value in Repository for use inside this class
    Publisher2(){
    }

    @Override
    public  void  run() {
            print();
    }


    public void print(){

        synchronized (this) {
            while(Driver2.repo.get() < 10) {
                System.out.println("Publisher: " + Driver2.repo.get());
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Counter2  extends Thread{
    Counter2(){
    }
    @Override
    public void run() {
        while(Driver2.repo.get() < 10) {
            increment();
        }
    }

    public synchronized void increment() {
        //Increment method inside Repository is called
        Driver2.repo.increment();
        System.out.println("Counter: " + Driver2.repo.get());
        notifyAll();

    }
}

class Repository2{
    private int repository = 0;
    //This changed statement operates as a switch to change Counter and Publisher running
    boolean changed = false;

    public Repository2() {

    }
    //Allows for remote incrementing from other classes
    public  void increment() {
        repository++;
    }

    //Returns the number inside repository
    public  int get() {
        return this.repository;
    }

}




