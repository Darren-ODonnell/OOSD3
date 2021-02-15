public class ProcessDemo {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();

        System.out.println("Number of processors: " + r.availableProcessors());
        System.out.println("Total Memory: " + r.totalMemory());
        System.out.println("Free Memory: " + r.freeMemory());
        System.out.println("Used Memory: " + (r.totalMemory() - r.freeMemory()));

        for(int i = 0; i <= 10000; i++){
            new Object();
        }
        r.gc();
        System.out.println("\nMemory Status");
        System.out.println("Total Memory: " + r.totalMemory());
        System.out.println("Free Memory: " + r.freeMemory());
        System.out.println("Used Memory: " + (r.totalMemory() - r.freeMemory()));
    }
}
