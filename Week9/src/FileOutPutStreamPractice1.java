import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutPutStreamPractice1 {

    public static void main(String[] args) {
        try{
            FileOutputStream fos = new FileOutputStream("test.txt");
            fos.write(65); // 65 represents A
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
