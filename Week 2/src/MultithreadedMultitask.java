public class MultithreadedMultitask {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();

        task1.start();
        task2.start();
        task3.start();
    }

}

class Task1 extends Thread{
    public void run(){
        System.out.println("Task One");
    }
}

class Task2 extends Thread{
    public void run(){
        System.out.println("Task Two");
    }
}

class Task3 extends Thread{
    public void run(){
        System.out.println("Task Three");
    }
}
