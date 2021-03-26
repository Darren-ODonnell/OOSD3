package WithdrawDeposit;
import java.util.Random;

public class Processor {
    private int balance = 0;
    private final int LIMIT = 10;
    private final Object BALANCE = new Object();
    int withdrawalAmount = 8500;

    public void deposit() throws InterruptedException{

        int amount = 2000;

        while(true){

            synchronized (BALANCE) {
                balance += amount;
                System.out.println(balance);
                BALANCE.notify();

            }
            Thread.sleep(500);

        }
    }

    public void withdraw() throws InterruptedException{
        while(true){

            synchronized (BALANCE) {

                while(balance < withdrawalAmount){
                    System.out.println("Request to withdraw " + withdrawalAmount);
                    BALANCE.notify();//notify and wait important for making call back to deposit method
                    BALANCE.wait();
                }
                balance -= withdrawalAmount;
                System.out.print("Balance is: " + balance);
                System.out.println("; value is: " + withdrawalAmount);
                BALANCE.notify();
            }
            Thread.sleep(500);
        }
    }
}
