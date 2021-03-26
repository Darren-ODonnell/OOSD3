import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamPractice {

    public static void main(String[] args) {

        try{
            FileInputStream fis = new FileInputStream("test.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            int i = 0;
            while((i = bis.read()) != -1){
                System.out.print((char)i);
            }
            bis.close();
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
