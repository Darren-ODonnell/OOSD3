public class Driver {

    public static void main(String[] args) {
        double[] array = new double[]{10,20,30,40,100};
        displayBasicStatistics(array);
    }

    public static void displayBasicStatistics(double[] array){
        System.out.println("Standard Deviation = " + Statistics.getStandardDeviation(array));
        System.out.println("Variance = " + Statistics.getVariance(array));
        System.out.println("Mean = " + Statistics.getMean(array));
        System.out.println("Total = " + Statistics.getTotal(array));


    }
}
