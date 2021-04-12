import java.io.*;

public class BufferedInputStreamExample {

    public static void main(String[] args) throws IOException {

        File data = new File("oosd.txt");
        FileInputStream file = new FileInputStream(data);
        FilterInputStream filter = new BufferedInputStream(file);

        int i = 0;
        while((i = filter.read()) != -1){
            System.out.print((char) i);
        }
        file.close();
        filter.close();
    }
}
