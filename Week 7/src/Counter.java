public class Counter {
    private int count = 0;

    public void add(int value){
        this.count = this.count + value;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        //ThreadA(counter);
        //ThreadBCounter();
    }
}
//this.count == 0
//A: Reads this.count --> register (0)
//B: Reads this.count --> register (0)
//B: Add value 2 to the register
//B: Writes register value (2) back to memory: this.count = 2
//A: Add 3 to the register
//A: register --> 0 + 3 --> this.count = 3
