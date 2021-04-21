import java.io.FileWriter;
import java.io.IOException;

public class FileWriterPractice {

    public static void main(String[] args) {

        try{
            FileWriter fileWriter = new FileWriter("oosd.txt");
            fileWriter.write("It is almost the summer!");
            fileWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
