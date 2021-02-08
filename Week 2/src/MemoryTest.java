public class MemoryTest {

    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();

        System.out.println("Total Memory: " + run.totalMemory());
        System.out.println("Free Memory: " + run.freeMemory());

        for(int i = 0; i < 1000; i++){
            new MemoryTest();

        }
        System.out.println("Free Memory after 100 instances: " + run.freeMemory());
        System.gc();
        System.out.println("Free Memory after gc call: " + run.freeMemory());
    }
}
