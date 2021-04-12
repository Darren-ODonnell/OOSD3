import java.io.*;

public class DataOutputStreamExample {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("Test.txt");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(65);
        dos.writeInt(100);
        dos.writeBytes(" Hello");
        dos.flush();
        dos.close();

    }
}
