package TerminationTesting;

import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.print(""); // expected
        }
    }


}
