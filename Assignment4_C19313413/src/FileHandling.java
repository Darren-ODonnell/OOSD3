import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Stack;

public class FileHandling {

    /**
     * Read file contents into a stack
     * @param file
     * @return
     */
    public Stack<String> readFileAsStack(String file)  {
        Stack<String> stack = new Stack<>();
        BufferedReader reader = null;
        String word = null;

        //Opening File For Reading
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Processing File
        try {
            while((word = reader.readLine()) != null) {
                //Add word to stack
                stack.push(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //When Finished, Close File
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stack;
    }

    /**
     * Write single word to BufferedWriter file
     * @param bw
     * @param word
     */
    public void writeWord(BufferedWriter bw, String word) {
        String ls = "\r\n";
        try {
            bw.write(word + ls);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * open BufferedWriter with filename
     * @param filename
     * @return
     */
    public BufferedWriter openBufferedWriter(String filename) {

        try {
            return new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * close buffered writer
     * @param bw
     */
    public void closeFile(BufferedWriter bw) {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
