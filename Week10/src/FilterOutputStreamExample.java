import java.io.*;

public class FilterOutputStreamExample {

    public static void main(String[] args) throws IOException {

        File data = new File("oosd.txt");
        FileOutputStream file = new FileOutputStream(data);
        FilterOutputStream filter = new FilterOutputStream(file);

        String s = "Welcome to OOSD3!";
        byte[] b = s.getBytes();
        filter.write(b);

        filter.flush();
        filter.close();
        file.close();
    }
}
