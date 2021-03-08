public class Sums {

    private int sum1 = 0;
    private int sum2 = 0;

    private Integer sum1Lock = 1;
    private Integer sum2Lock = 2;


    public void add(int value1, int value2){
        synchronized (this.sum1Lock){
            this.sum1 += value1;
        }
        synchronized (this.sum2Lock){
            this.sum2 += value2;
        }
    }

    public static void main(String[] args) {


    }
}
