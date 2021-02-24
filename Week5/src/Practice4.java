import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Practice4 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);
        Runnable r1 = new Task("task1");
        Runnable r2 = new Task("task2");
        Runnable r3 = new Task("task3");
        Runnable r4 = new Task("task4");
        Runnable r5 = new Task("task5");

        service.execute(r1);
        service.execute(r2);
        service.execute(r3);
        service.execute(r4);
        service.execute(r5);

        service.shutdown();
    }

}
class Task implements Runnable{
    private String name;
    public Task(String s){
        name = s;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= 5; i++) {
                if (i == 0) {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Start Time for task name: " + name + "is: " + ft.format(d));
                } else {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("Executing Time for task name: " + name + "is: " + ft.format(d));
                }
                Thread.sleep(1000);
            }
            System.out.println(name + " complete");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
