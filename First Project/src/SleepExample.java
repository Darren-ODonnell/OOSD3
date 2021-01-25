public class SleepExample extends Thread{

    public void run(){
        for(int i = 1; i < 5; i++){
            try{
                Thread.sleep(500);

            }catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.print(i);
        }
    }
    public static void main(String[] args) {
        SleepExample example1 = new SleepExample();
        SleepExample example2 = new SleepExample();
        example1.start();
        example2.start();

    }
}
