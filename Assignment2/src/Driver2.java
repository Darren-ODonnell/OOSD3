import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Driver2{
    public static void main(String[] args) {
        // pool of 4 threads
        ExecutorService executorService =
                Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        // creates 4 callables to be executed
        for (int i = 0; i < 4; i++) {
            TaskCall callable = new TaskCall();

            //Gets the future String from the call() method
            Future<String> future = executorService.submit(callable);
            //Adds the future String for the current process to the list
            futures.add(future);
        }
        // getting tasks results
        for(int i = 0; i < futures.size(); i++){
            try {
                //Prints the output held in the futures list
                System.out.println(futures.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // shuts down the executor service
        executorService.shutdown();
    }
}
    class TaskCall implements Callable<String> {
        @Override
        public String call(){
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

            /**
             * next task - to grab the output from the ping command#
             * and return this as our output to the future object.
             *
             */


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
