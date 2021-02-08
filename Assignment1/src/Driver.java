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

    //Takes in the value in Repository for use inside this class
    Publisher(Repository repository){
        repo = repository;
    }

    public void run() {
        while(true) {
            if(repo.isChanged()) {
                    System.out.println("Publisher: " + repo.get());
                repo.setChanged(false);
            }
        }
    }


}

class Counter  extends Thread{
    Repository repo;

    //Takes in the value in Repository for use inside this class
    Counter(Repository repository){
        repo = repository;
    }

    public void run(){

        int i = 1;

        while(i<10000) {

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
}

class Repository{
    private int repository;
    //This changed statement operates as a switch to change Counter and Publisher running
    boolean changed = false;

    public Repository() {

    }
    //Allows for remote incrementing from other classes
    public synchronized void increment() {
        repository++;
    }
    //Returns the number inside repository
    public synchronized int get() {
        return this.repository;
    }
    //Returns whether the value has been changed
    public synchronized boolean isChanged() {
        return changed;
    }
    //Sets the value of changed, this is done at the end of each iteration inside Counter and Publisher's run methods
    public synchronized void setChanged(boolean value) {
        changed = value;
    }
}



