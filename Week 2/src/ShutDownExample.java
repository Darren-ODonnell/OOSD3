public class ShutDownExample extends Thread{

    public static void main(String[] args) {

        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new ShutDownExample());

        System.out.println("Main sleeping - press ctrl + c to exit");
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void run(){
        System.out.println("Shutdown hook task Completed");
    }
}

