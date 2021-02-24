import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
public class Practice3 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Future & callable used");
                return "Callable result";
            }
        });

        try{
            System.out.println(future.get());
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }finally{
            service.shutdown();
        }
    }
}
