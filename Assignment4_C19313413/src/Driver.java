import java.util.concurrent.TimeUnit;

public class Driver {

    static boolean fileFinished = false;
    Utilities util = new Utilities();
    FileHandling fh = new FileHandling();

    public Driver(){
        WordRepository repo = new WordRepository();
        WordsReading wordsReading = new WordsReading();
        MemoryWriting memoryWriting = new MemoryWriting(repo);

        memoryWriting.start();


        while(true) {
            if (Driver.getFileFinished()) {
                memoryWriting.interrupt();
            }
        }

    }

    public static void main(String[] args) {
        new Driver();
    }

    // Used for adding delay to GUI so that the changes to order state are more visible


    public static void setFileFinished(){
        fileFinished = true;
    }

    public static boolean getFileFinished(){
        return fileFinished;
    }

    public void tidyup() {

    }

}
