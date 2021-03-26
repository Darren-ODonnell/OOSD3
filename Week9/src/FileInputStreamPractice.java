import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamPractice {

    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream("oosd.txt");
            int i = 0;
            while((i = fis.read()) != -1){
                System.out.print((char)i);
            }
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
