package WithoutExecutorService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryWriting extends Thread{
    WordRepository repo;

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
                        } catch (InterruptedException ignored) {
                        }
                    }
                    repo.updateState(WordRepository.WRITING_STATE);
                    String word = repo.getWord();

                    try {
                        bw.write(word);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    System.out.println("Word being written: " + word);


                    repo.updateState(WordRepository.EMPTY_STATE);

                    repo.notify();
                    if(Driver.getFileFinished()){
                        bw.close();
                    }
                    try {
                        repo.wait();
                    } catch (InterruptedException ignored) {
                    }
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
