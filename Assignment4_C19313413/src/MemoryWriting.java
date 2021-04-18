import java.io.BufferedWriter;
import java.util.concurrent.Callable;

//The class which holds what the threads will use to store the learned words in their memory
public class MemoryWriting implements Callable<String> {
    //This object is used for synchronising memory writing
    final Object lock = new Object();
    FileHandling fh = new FileHandling();
    Utilities util = new Utilities();
    String word;
    BufferedWriter bw;

    //Needs access to the bufferedWriter and word so that it can write the word to the memory file
    public MemoryWriting(BufferedWriter bw, String word) {
        this.word = word;
        this.bw = bw;
    }

    @Override
    public String call() throws Exception {
        //Shows the current word held within the thread
        System.out.println(Thread.currentThread().getName() + " : Current Word : " + word);

        //Synchronised so that threads cannot write to file simultaneously
        synchronized (lock) {
            //Time given for memorising each word
            util.sleepms(Utilities.SLEEP_TIMER);

            //Calls the write word method in FileHandling, which writes the word to the file using BufferedWriter bw
            fh.writeWord(bw, word);

            //Displays the word after it has been memorised
            System.out.println("--> "+ Thread.currentThread().getName() + " --> Memorised word : " + word);

        }

        return null;
    }
}
