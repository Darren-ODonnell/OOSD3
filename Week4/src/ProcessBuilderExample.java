import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessBuilderExample {

    public static void main(String[] args)  {

        ProcessBuilder pb = new ProcessBuilder();
        pb.command("cmd.exe", "/c", "ping -n 10 github.com");
        System.out.println("Calling the start method now");

        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to start");
        }

        int errorCode = 0;
        try {
            errorCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("failed whiled waiting");
        }

        System.out.println("print process executed. any errors? " + (errorCode == 0 ? "No" : "Yes"));

        try {
            System.out.println("Print process output: " + output(process.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed when displaying");
        }

    }

    private static String output(InputStream inputStream) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;

        while(true){
            try {
                if (((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                br.close();
            }
            sb.append(line + "-");
        }
        return sb.toString();
    }
}
