import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterPractice {
    public static void main(String[] args) {

        try{
            Writer writer = new FileWriter("test.txt");

            String content = "I love OOSD3";

            writer.write(content);

            writer.close();

            System.out.println("Task Completed");
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
