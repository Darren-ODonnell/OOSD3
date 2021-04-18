package ExecutorsWithThreads;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {

    static boolean fileFinished = false;
    Utilities util = new Utilities();
    FileHandling fh = new FileHandling();

    static List<String> memoryList = new ArrayList<>();
    private Stack<String> inputStack;

    public Driver(){
        // memory writing thread
        MemoryWriting memoryWriting = new MemoryWriting();
        // create and start 4 memorising threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // read in words into stack
        inputStack = fh.readFileAsStack("words.txt", "\n");

       // start memory writing thread
        memoryWriting.start();

        // process each word in stack
        while (!inputStack.empty()) {
            String word = inputStack.pop();

//            while(!WordRepository.getState().equals(WordRepository.EMPTY_STATE)){
//                util.sleep(1);
//            }
            util.sleep(3);
            System.out.println("Word: " + word);
            WordRepository.setWord(word);
            WordsReading reader = new WordsReading();
            executorService.submit(reader);

        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Blank");
        memoryList.forEach(System.out::println);

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


