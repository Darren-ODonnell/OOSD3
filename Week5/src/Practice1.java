import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Practice1 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new Runnable(){
            public void run(){
                System.out.println("Service task running");
            }
        });
        service.shutdown();

    }
}
