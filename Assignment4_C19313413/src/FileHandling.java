import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

public class FileHandling {

    // Read file contents into a stack
    public Stack<String> readFileAsStack(String file) {
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
            while ((word = reader.readLine()) != null) {
                //Add word to stack
                stack.push(word);
            }
            //Closes file once all words have been taken in
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stack;
    }

    // Empties file at start of running, so that you can see the words being input in real time
    public void emptyFile(String filename) {
        try {
            //Opens file for writing (deletes current data in file)
            FileWriter fw = new FileWriter(filename);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // temp is used to store the words currently in words.txt and is
    // renamed to words.txt once the original words.txt is replaced
    public void copyToTempFile(Stack<String> stack) {

        try {
            FileWriter fw = new FileWriter("Temp.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (String word : stack)
                bw.write(word + "\r\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Renames tempfile to words.txt and deletes words.txt
    public void copyTempToWords() {

        String temp = "temp.txt";
        String words = "words.txt";
        // File with old name
        File wordsFile = new File("words.txt");

        // Temporary File
        File tempFile = new File("temp.txt");

        //Deletes words.txt
        if (wordsFile.delete()) {
            if (!wordsFile.exists()) {
                //Renames the tempfile to words.txt
                boolean success = tempFile.renameTo(wordsFile);
            } else { // file already exists
                System.out.println("file still exists");
            }
        } else {
            System.out.println("Failed to delete the file.");
        }

    }

    //Appends word  to memory
    public void appendWord(String word) {
        FileHandling fh = new FileHandling();
        BufferedWriter bw = fh.createNewFileForAppend("memory.txt");
        PrintWriter out = new PrintWriter(bw);
        out.println(word);
        out.close();

    }

    //Creates new file for appending
    public BufferedWriter createNewFileForAppend(String filename) {
        BufferedWriter bw = null;
        try {
            //Opens file for appending
            FileWriter fw = new FileWriter(filename, true);
            bw = new BufferedWriter(fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw;
    }

}
