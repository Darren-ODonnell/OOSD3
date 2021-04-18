package ExecutorsWithThreads;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class FileHandling {


    /**
     * REad file into string
     * @param file - name of file
     * @return
     * @throws IOException
     */
    public String readFile(String file)  {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * The method uses readfile into string first
     * * readfile into array split by separator
     * @param filename
     * @param lineSeparator
     * @return
     * @throws IOException
     */
    public String[] readFileAsArray(String filename, String lineSeparator)  {

        String fileContents = readFile(filename);

        String[] result = fileContents.split(lineSeparator);

        return result;
    }

    /**
     * Read file contents into a stack
     * @param filename
     * @param lineSeparator
     * @return
     * @throws IOException
     */
    public Stack<String> readFileAsStack(String filename, String lineSeparator)  {

        Stack<String> stack = new Stack<>();

        String fileContents = readFile(filename);

        stack.addAll(Arrays.asList(fileContents.split(lineSeparator)));

        return stack;
    }

    public void writeWord(BufferedWriter bw, String word) {
        try {
            bw.write(word);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Word being written: " + word);
    }


}
