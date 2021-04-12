import java.io.*;

public class ByteArrayOutputStreamExample {

    public static void main(String[] args) {
        try {
            FileOutputStream fout1 = new FileOutputStream("oosd.txt");
            FileOutputStream fout2 = new FileOutputStream("Test.txt");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bos.write(65); //65 is the letter a
            bos.writeTo(fout1);
            bos.writeTo(fout2);

            bos.flush();
            bos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
