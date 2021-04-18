import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {

    static boolean fileFinished = false;
    Utilities util = new Utilities();
    FileHandling fh = new FileHandling();
    BufferedWriter bw = fh.openBufferedWriter("memory.txt");
    static final Object lock = new Object();


    static List<String> memoryList = new ArrayList<>();
    private Stack<String> inputStack;

    public Driver(){
        // create and start 4 memorising threads
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        // read in words into stack
        inputStack = fh.readFileAsStack("words.txt", "\n");

        // process each word in stack
        while (!inputStack.empty()) {

            synchronized (lock) {
                String word = inputStack.pop();

//                WordRepository.setWord(word);
                MemoryWriting memoryWriting = new MemoryWriting(bw, word);
                executorService.submit(memoryWriting);
            }

        }

        // make sure threads are closed down before closing file
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("**ERROR: THREAD TIMEOUT HAS OCCURRED\nPossible loss of data**");
            };
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fh.closeFile(bw);

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


