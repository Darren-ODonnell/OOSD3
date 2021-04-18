import java.io.BufferedWriter;
import java.util.concurrent.Callable;

public class MemoryWriting implements Callable<String> {
    FileHandling fh = new FileHandling();
    Utilities util = new Utilities();
    String word;
    BufferedWriter bw;


    public MemoryWriting(BufferedWriter bw, String word) {
        this.word = word;
        this.bw = bw;
    }

    @Override
    public String call() throws Exception {

//        while (WordRepository.getWord() == null) {
//            System.out.println("Word is null");
//            util.sleep(1);
//        }
        synchronized (Driver.lock) {

            System.out.println("Memory Thread Name : " + Thread.currentThread().getName() + " : word : " + word);

            //Time given for memorising word
//                util.sleep(1);

            fh.writeWord(bw, word);

        }

        return null;
    }
}
