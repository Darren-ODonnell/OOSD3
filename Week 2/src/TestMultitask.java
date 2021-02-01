public class TestMultitask extends Thread {

    public static void main(String[] args) {
        TestMultitask t1 = new TestMultitask();
        TestMultitask t2 = new TestMultitask();
        TestMultitask t3 = new TestMultitask();

        t1.start();
        t2.start();
        t3.start();
    }

    public void run(){
        System.out.println("Task one");
    }

}
