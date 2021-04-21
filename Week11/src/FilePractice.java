import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FilePractice {
    public static void main(String[] args) {
        try{
            File file = new File("FilePractice.txt");
            if(file.createNewFile()){
                System.out.println("File has been created");
            }else{
                System.out.println("file already exists");
            }

            File dir = new File("src");
            String filenames[] = dir.list();
            for(String fileName : filenames){
                System.out.println(fileName);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
