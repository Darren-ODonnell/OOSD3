import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class Practice2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Future results awaits");
            }
        });

        try {
            System.out.println(future.get());
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }finally{
            service.shutdown();
        }
    }
}
