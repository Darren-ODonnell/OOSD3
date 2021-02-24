import java.util.Arrays;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Colony
{


    //METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static int[] cellCompete(int[] cells, int days) {
        int[] cells2 = new int[8];
        // INSERT YOUR CODE HERE

        //IF neighbours are BOTH active or BOTH inactive, current becomes inactive for next day, else becomes active

        //Cells on ends have 1 adjacent cell, the missing cell can be classed as inactive at all times

        //1 = active, 0 = inactive
        for (int x = 0; x < days; x++) {
            for (int i = 0; i < cells.length; i++) {

                if (i == 0) {
                    //If both the same cell, current becomes inactive
                    cells2[i] = (cells[i + 1] == 0) ? toggle(cells[i]) : cells[i];

                } else if (i == cells.length - 1) {
                    cells2[i] = (cells[i - 1] == 0) ? toggle(cells[i]) : cells[i];

                } else {

                    cells2[i] = (cells[i - 1] == cells[i + 1]) ? toggle(cells[i]) : cells[i];

                }

            }
            cells = cells2;

        }
        return cells2;
        // METHOD SIGNATURE ENDS
    }
    public static int toggle(int current) {
        return (current == 0) ? 1 : 0;


    }

    public static void main(String[] args) {
        int[] in = new int[]{1, 0, 0, 0, 0, 0, 0, 1};
        int[] out = Colony.cellCompete(in, 1);
        System.out.println(""+in[0] + in[1] + in[2] + in[3] + in[4] + in[5]  + in[6]  + in[7]);
        System.out.println(""+out[0] + out[1] + out[2] + out[3] + out[4] + out[5]  + out[6]  + out[7]);
    //    System.out.println("out "+output);


    }

}