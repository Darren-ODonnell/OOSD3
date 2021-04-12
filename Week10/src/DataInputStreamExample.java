import java.io.*;

public class DataInputStreamExample {
    public static void main(String[] args) throws IOException{
        FileInputStream fis = new FileInputStream("Test.txt");
        DataInputStream dis = new DataInputStream(fis);

        int count = fis.available();
        byte[] array = new byte[count];

        dis.read(array);

        for(byte b : array){
            char ch = (char)b;
            System.out.print(" ch: " + ch );
        }

    }
}
