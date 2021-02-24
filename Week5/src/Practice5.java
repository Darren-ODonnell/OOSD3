import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Practice5 implements Callable<String> {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<>();
        Callable<String> callable = new Practice5();
        for(int i =0; i < 100; i++){

            Future<String> future = service.submit(callable);
            list.add(future);
        }

        for(Future<String> ft: list){
            try {
                System.out.println(new Date() + ": " + ft.get());
            } catch (InterruptedException | ExecutionException e ) {
                e.printStackTrace();
            } finally {
                service.shutdown();
            }
        }
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }
}
