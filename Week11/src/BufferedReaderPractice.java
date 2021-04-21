import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderPractice {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("test.txt");
            BufferedReader br = new BufferedReader(fr);
            String content;

            while((content = br.readLine()) != null){
                System.out.println(content);
            }
            br.close();
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
