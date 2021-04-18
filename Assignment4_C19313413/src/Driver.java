import java.io.BufferedWriter;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {

    FileHandling fh = new FileHandling();
    BufferedWriter bw = fh.openBufferedWriter("memory.txt");
    final int MAX_THREADS = 10;
    long stackSize;

    private Stack<String> stack;

    public Driver(){
        // create and start 4 memorising threads
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);

        // read in words into stack
        stack = fh.readFileAsStack("words.txt");

        //To be used for awaitTermination
        stackSize = stack.size();

        // process each word in stack
        while (!stack.empty()) {

            //Constructs a MemoryWriting with buffered writer and the word popped from stack
            MemoryWriting memoryWriting = new MemoryWriting(bw, stack.pop());
            //Submits the Callable object memoryWriting to the ExecutorService
            // to be acted on by the idle Threads in the Thread Pool
            executorService.submit(memoryWriting);
        }

        tidyUp(executorService, fh);

    }
    // stops any new threads from being made from while allowing all current threads to finish
    public void tidyUp(ExecutorService executorService, FileHandling fh){

        executorService.shutdown();
        try {
            //This allows for easily changing the size of input as well as the number of threads
            //Without having to change the timer amounts
            long timeout = Utilities.SLEEP_TIMER * stackSize * MAX_THREADS;

            //awaitTermination waits until all threads are finished or the timer ending whichever happens first
            if(!executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS)){
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

}


