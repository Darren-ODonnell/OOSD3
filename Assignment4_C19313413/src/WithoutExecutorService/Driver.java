package WithoutExecutorService;

import java.util.concurrent.TimeUnit;

public class Driver {

    static boolean fileFinished = false;
    public Driver(){
        WordRepository repo = new WordRepository();
        WordsReading wordsReading = new WordsReading(repo);
        MemoryWriting memoryWriting = new MemoryWriting(repo);

        wordsReading.start();
        memoryWriting.start();

        while(true) {
            if (Driver.getFileFinished()) {
                wordsReading.interrupt();
                memoryWriting.interrupt();
            }
        }

    }

    public static void main(String[] args) {
        new Driver();
    }

    // Used for adding delay to GUI so that the changes to order state are more visible
    public static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            System.out.print(""); // expected
        }
    }

    public static void setFileFinished(){
        fileFinished = true;
    }

    public static boolean getFileFinished(){
        return fileFinished;
    }

}
