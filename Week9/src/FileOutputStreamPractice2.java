import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileOutputStreamPractice2 {

    public static void main(String[] args) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("oosd.txt");
            String oosd3 = "Welcome to OOSD3";
            byte[] myByteString = oosd3.getBytes();
            fos.write(myByteString);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
