import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuildCalendar {
    public static void main(String[] args) throws IOException {
        System.out.println("Calendar for the year");
        ProcessBuilder pb = new ProcessBuilder("cal", "2022");
        Process process = pb.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while((line =br.readLine()) != null){
            System.out.println(line);
        }

    }
}
