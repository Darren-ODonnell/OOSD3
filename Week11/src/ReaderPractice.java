import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderPractice {

    public static void main(String[] args) {
        try{
            Reader reader = new FileReader("test.txt");
            int myData;
            while((myData = reader.read()) != -1){
                System.out.print((char)myData);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
