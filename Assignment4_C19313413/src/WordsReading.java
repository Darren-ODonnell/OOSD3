
import java.io.*;
import java.util.Stack;

public class WordsReading {
    static Stack<String> stack = new Stack<>();
    FileHandling fh = new FileHandling();


    public WordsReading() {
        readIntoStack();
    }

    public void readIntoStack(){
        try {
            stack = fh.readFileAsStack("words.txt", "\n");

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
