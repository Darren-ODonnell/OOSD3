package WithoutExecutorService;

import java.io.*;

public class WordsReading extends Thread {
    WordRepository repo;

    public WordsReading(WordRepository repo) {
        this.repo = repo;
    }

    public void readFromFile() {
        //Read From File
        try{
            BufferedReader br  = new BufferedReader(new FileReader("words.txt"));
            String word;

            while((word = br.readLine()) != null) {

                synchronized (repo) {

                    while(!repo.getState().equals(WordRepository.EMPTY_STATE)){
                        try {
                            repo.wait();
                        }catch(InterruptedException ignored){}
                    }

                    repo.setWord(word);
                    repo.updateState(WordRepository.READING_STATE);
                    System.out.println("Word being read: " + word);
                    //WithoutExecutorService.Driver.sleep(2);
                    repo.notify();
                    try {
                        repo.wait();
                    }catch(InterruptedException ignored){
                    }

                }

            }
            Driver.setFileFinished();
            br.close();

            }catch(IOException e){
            e.printStackTrace();
        }


        //Delete From File

    }

    public void setWordBeingRead(String wordBeingRead) {

    }

    public void run(){
        readFromFile();
    }
}
