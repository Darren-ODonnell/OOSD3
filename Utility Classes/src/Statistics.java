public class Statistics {

    public static double getStandardDeviation(double[] array) {
        return Math.sqrt(getVariance(array));
    }

    public static double getVariance(double[] array) {
        double mean = getMean(array);
        double variance = 0;

        for (int i = 0; i < array.length; i++) {
            variance += getIndividualVariance(mean, array[i]);
        }
        return Math.sqrt(variance / array.length);
    }

    private static double getIndividualVariance(double mean, double value) {
        return Math.pow(value - mean, 2);

    }

    private static double getIndividualDeviation(double mean, double value){
        return value -mean;
    }


    public static double getMean(double[] array) {
        return getTotal(array) / array.length;
    }

    public static double getTotal(double[] array){
        double total = 0;
        for(int i = 0; i < array.length; i++) {
            total += array[i];
        }
        return total;
    }

    public static double getANOVATable(double array1[], double array2[], double array3[]){
        double[] devSqrTotalArray = new double[3];
        double[] tableMeans = new double[]{getMean(array1), getMean(array2), getMean(array3)};
        double grandTotalMean = getMean(tableMeans);
        devSqrTotalArray[0] = displayANOVATable_1(array1);
        devSqrTotalArray[1] = displayANOVATable_1(array2);
        devSqrTotalArray[2] = displayANOVATable_1(array3);

        displayANOVATable_2(tableMeans, 5);


    }

    private static double displayANOVATable_1(double array[]){
        double mean = getMean(array);
        double totalDevSqr = 0;
        System.out.println(" ____________ ____________ ____________ ____________");
        System.out.println("|   Value    |    Mean    |  Deviation |Dev Squared |");
        System.out.println(" ____________ ____________ ____________ ____________");

        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i] + "  |  " + mean + "  |  " + getIndividualDeviation(mean, array[i])  + "  |  " + getIndividualVariance(mean, array[i]));
            totalDevSqr += getIndividualVariance(mean, array[i]);
        }

        return totalDevSqr;
    }

    private static double displayANOVATable_2(double array[], int size){
        double mean = getMean(array);
        double MS_Error = 0;
        double DoF_Error = size * 3 - 3;
        double SS_Error = 0;


        System.out.println(" __________________ __________________ ");
        System.out.println("|Sum Of Squared Dev|     Variance     |");
        System.out.println(" __________________ __________________");
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]+ "  |  " + (array[i] / 4) + "  |  ");
            MS_Error += array[i]/4;
        }

        SS_Error = MS_Error * DoF_Error;

        System.out.println("| SS Error | MS Error | DoF_Error |");
        System.out.println(SS_Error + "  |  " + MS_Error + "  |  " + DoF_Error);


        return totalDevSqr;
    }

}
