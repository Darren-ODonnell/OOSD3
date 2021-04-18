import java.util.concurrent.TimeUnit;

//This class allows for other classes to use sleep in a cleaner way
public class Utilities {

    //Sleep timer is referred to in 3 places, 2x inside MemoryWriter and 1x inside Driver for awaitTermination
    static final int SLEEP_TIMER = 10;

    /**
     * Sleep for i Seconds
     * @param i
     */
    public void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.print(""); // expected
        }
    }

    /**
     * Sleep for i milliseconds
     * @param i
     */
    public void sleepms(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.print(""); // expected
        }
    }

}
