import java.util.concurrent.TimeUnit;

public class Utilities {

    public void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.print(""); // expected
        }
    }


}
