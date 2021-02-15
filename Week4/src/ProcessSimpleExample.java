import java.util.concurrent.TimeUnit;

public class ProcessSimpleExample {

    public static void main(String[] args) throws Exception{
        Runtime r = Runtime.getRuntime();
        //Opens application at file location, path incorrect
        //Open applications and closes after 10 seconds
        Process p = r.exec("C:/Program Files/Internet Explorer");
        p.waitFor(10, TimeUnit.SECONDS);


    }
}
