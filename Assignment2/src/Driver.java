import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Driver {

    public static void main(String[] args){
        //processBuilder.command("cmd.exe", "/c", "<ping command> -n <number of times to ping><website>");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", "ping -n 10 github.com");

            //Trying to run, catching IO and Interrupted Exceptions
            try {
                //Starting Process
                Process process = processBuilder.start();

                //Uses the InputStreamReader to interpret the input stream in process
                /**
                 * InputStream is being blocked
                 */
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;

                //Prints the next line where there is still a next line to be printed
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                //Prints the exit code if no errors occur
                int exitCode = process.waitFor();
                System.out.println("\nExited with error code : " + exitCode);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

}
//2 classes are needed, in main line create an executor service, create a process builder and pass in the command, call the start on that. "Building on Q1"
//Use executor service to start off a separate thread which will complete the task that is blocking
//The second class will implement the callable interface, this will have a method "call()" which does what you did in question 1 where you did what you did to read the files
//Read line and put into a string, add this string to the List, return list (call method returns list of Strings

//The implements Callable<> inside the "<>" you put the return type you wish to return from the call method
class Caller implements Callable<List<String>>{

    //HOW to call this new Caller(process.getInputStream())
    public Caller(){

    }
    @Override
    /**
     * Cannot return a List<String>
     */


    //method -> call -> Read inputStream like Q1 -> return list of string(Strings from inputStream)
    public List<String> call() throws Exception {
        List<String> list = new ArrayList<>();


        return null;
    }

    //in main
    //REturn list of strings into future object
    //CAll get on the future objects -> List of strings, loop through this and print it out
}
