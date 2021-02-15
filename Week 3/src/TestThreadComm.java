public class TestThreadComm {

    public static void main(String[] args) {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        Customer customer4 = new Customer();
        Customer customer5 = new Customer();
        Customer customer6 = new Customer();


        Thread withdraw1 = new Thread( () -> customer1.withdraw(15000));
        withdraw1.start();
        Thread deposit1 = new Thread( () -> customer1.deposit(10000));
        deposit1.start();


        Thread withdraw2 = new Thread( () -> customer2.withdraw(15000));
        withdraw2.start();
        Thread deposit2 = new Thread( () -> customer2.deposit(10000));
        deposit2.start();

        Thread withdraw3 = new Thread( () -> customer3.withdraw(15000));
        withdraw3.start();
        Thread deposit3 = new Thread( () -> customer3.deposit(10000));
        deposit3.start();

        Thread withdraw4 = new Thread( () -> customer4.withdraw(15000));
        withdraw4.start();
        Thread deposit4 = new Thread( () -> customer4.deposit(10000));
        deposit4.start();

        Thread withdraw5 = new Thread( () -> customer5.withdraw(15000));
        withdraw5.start();
        Thread deposit5 = new Thread( () -> customer5.deposit(10000));
        deposit5.start();

        Thread withdraw6 = new Thread( () -> customer6.withdraw(15000));
        withdraw6.start();
        Thread deposit6 = new Thread( () -> customer6.deposit(10000));
        deposit6.start();

    }
}

class Customer {
    int amount = 10000;
    synchronized void withdraw(int amount){
        System.out.println("Going to withdraw...");

        if(this.amount < amount){
            System.out.println("Insufficient Balance");
            try{wait();}catch(Exception e){e.printStackTrace();}
        }
        this.amount = amount;
        System.out.println("Withdraw complete");
    }

    synchronized void deposit(int amount){
        System.out.println("Going to deposit...");
        this.amount += amount;
        System.out.println("Deposit complete");
        notify();
    }
}
