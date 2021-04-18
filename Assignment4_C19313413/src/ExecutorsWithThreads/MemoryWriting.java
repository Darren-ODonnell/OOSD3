package ExecutorsWithThreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryWriting extends Thread{
    FileHandling fh = new FileHandling();
    Utilities util = new Utilities();



    public MemoryWriting() {
    }

    public void writeToMemory(){
        try {
            FileWriter file = new FileWriter("memory.txt");
            BufferedWriter bw = new BufferedWriter(file);

            while (WordRepository.getWord() == null) {
                System.out.println("Word is null");
                util.sleep(1);
            }

            synchronized (WordRepository.lock) {
                while (true) {
                    System.out.println("Memory Writing inside while true");
                    while (!WordRepository.getState().equals(WordRepository.READING_STATE)) {
                            System.out.println("Memory Writing inside while true - try");
                            WordRepository.startWait();
                    }
                    
                    System.out.println("Memory Writing Word: " + WordRepository.getWord());
                    WordRepository.updateState(WordRepository.WRITING_STATE);

                    //Time given for memorising word
                    util.sleep(1);

                    fh.writeWord(bw, WordRepository.getWord());
                    WordRepository.updateState(WordRepository.EMPTY_STATE);

                    if (WordRepository.getState().equals(WordRepository.COMPLETED_STATE)) {
                        bw.close();
                    }

                    WordRepository.startWait();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }


    }

    public void run(){
        writeToMemory();
    }
}
