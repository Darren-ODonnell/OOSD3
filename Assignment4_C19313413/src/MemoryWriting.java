import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryWriting extends Thread{
    WordRepository repo;
    FileHandling fh = new FileHandling();
    Utilities util = new Utilities();

    public MemoryWriting(WordRepository repo) {
        this.repo = repo;
    }

    public void writeToMemory(){
        try {
            FileWriter file = new FileWriter("memory.txt");
            BufferedWriter bw = new BufferedWriter(file);

            synchronized (repo) {

                while (true) {
                    while (!repo.getState().equals(WordRepository.READING_STATE)) {
                        try {
                            repo.wait();
                        } catch (InterruptedException ignored) {}

                    }
                    repo.updateState(WordRepository.WRITING_STATE);

                    //Time given for memorising word
                    util.sleep(1);

                    fh.writeWord(bw, repo.getWord());

                    repo.updateState(WordRepository.EMPTY_STATE);

                    repo.notify();

                    if(repo.getState().equals(WordRepository.COMPLETED_STATE)){
                        bw.close();
                    }

                    try {
                        repo.wait();
                    } catch (InterruptedException ignored) {}

                }

            }
            }catch(IOException e){
                e.printStackTrace();
            }


    }

    public void run(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeToMemory();
    }
}
