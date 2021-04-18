package TerminationTesting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Driver{

    static List<String> memoryList = new ArrayList<>();
    static String[] words = {"a", "aa", "aaa", "aaron", "ab", "abandoned", "azerbaijan"};

    public static void main(String[] args) {
        // pool of 4 threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MemorisingWord memory = new MemorisingWord();


        // creates 4 callables to be executed
        for (String word : words) {
//            TaskCall callable = new TaskCall();
            ReadingWord reader = new ReadingWord();

            reader.setWord(word);
//            reader.setMemoriser();
            executorService.submit(reader);


//            synchronized (executorServiceWriting) {
//                memory.setWord(word);
//                executorServiceWriting.submit(memory);
//            }

        }
        
        executorService.shutdown();
        try {
            executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Blank");
        memoryList.forEach(System.out::println);

    }
}
class ReadingWord implements Callable<String> {
    String word="";

    @Override
    public String call() throws Exception {
        Utilities.sleep(3);
        synchronized (word) {
            System.out.println("ReadingThread name : " + Thread.currentThread().getName() + " word : " + word);
        }
        return null;
    }

    public void setWord(String word) {
        this.word = word;
    }

}

class MemorisingWord extends Thread{

    MemorisingWord(){

    }

    public void run(){
        Utilities.sleep(1);
//        while(!stack.isEmpty()){
//
//        }
    }

    private void saveToFile(){

    }
}
//
//class MemorisingWord implements Callable<String> {
//    String word = "";
//    @Override
//    public String call() throws Exception {
//
//        Utilities.sleep(1);
//            Driver.memoryList.add(word);
//            System.out.println("Memorising Thread name : " + Thread.currentThread().getName() + " finished");
//        return null;
//    }
//    public void setWord(String word) {
//        this.word = word;
//    }
//}

class TaskCall implements Callable<String> {
    @Override
    public String call() throws InterruptedException {
        //str contains the output from each process
        StringBuilder str = new StringBuilder();
        ProcessBuilder processBuilder = new ProcessBuilder();
        //Sets the command to ping github.com 10 times per process
        processBuilder.command("cmd.exe", "/c", "ping -n 10 github.com");

        //Trying to run, catching IO and Interrupted Exceptions
        //Starting Process
        Process process = null;
        try {
            process = processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(process != null) {
            //Initialise BufferedReader and InputStreamReader to process the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            try {
                //This appends the lines to hold the entire process in one StringBuilder
                while ((line = reader.readLine()) != null) {
                    str.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Returns the StringBuilder as a string so that the future object can use it in main
        return str.toString();
    }
}


