public class Driver {
    Counter counter;
    Publisher publisher;
    Repository repo;
    public Driver()  {

        repo = new Repository();
        counter = new Counter(repo);

        publisher = new Publisher(repo);

        publisher.start();

        counter.start();


    }

    public static void main(String[] args) {
        new Driver();

    }
}


class Publisher extends Thread{
    Repository repo;

    Publisher(Repository repository){
        repo = repository;
    }

    public void run() {
        int next= 0 , prev = -1 ;
        while(true) {
           // System.out.println("Publisher: " + next);

            if(repo.isChanged()) {
//                next = repo.get();
//
//                if (next != prev)
                    System.out.println("Num: " + repo.get());
                repo.setChanged(false);
//                prev = next;

            }

        }
    }


}

class Counter  extends Thread{
    Repository repo;
    int i = -1;


    Counter(Repository repository){
        repo = repository;
    }

    public void run(){

        int i = 1;

        while(i<1000000) {

            //If a new number isn't stored in repo, increment the repo, display  and exit if
            if (!repo.isChanged()) {

                //Incrementing repo and Displaying
                repo.increment();
                i = repo.get();

                System.out.println("Counter: " + i);

                repo.setChanged(true);
            }

        }


    }
    public Repository getResult(){
        return repo;
    }
}

class Repository{
    private int repository;
    //This changed statement operates as a switch to change Counter and Publisher running
    boolean changed = false;

    public Repository() {

    }

    public synchronized void increment() {
        repository++;
    }

    public synchronized int get() {
        return this.repository;
    }

    public synchronized boolean isChanged() {
        return changed;
    }
    public synchronized void setChanged(boolean value) {
        changed = value;
    }
}



