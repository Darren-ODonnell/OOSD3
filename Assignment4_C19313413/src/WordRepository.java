//import java.util.concurrent.atomic.AtomicInteger;
//
//public class WordRepository {
//    private static String word;
//    static final Object lock = new Object();
//
//
//    static AtomicInteger EMPTY_STATE = new AtomicInteger(0);
//    static AtomicInteger READING_STATE = new AtomicInteger(1);
//    static AtomicInteger WRITING_STATE = new AtomicInteger(2);
//    static AtomicInteger COMPLETED_STATE = new AtomicInteger(3);
//
//    static AtomicInteger stateOfRepo = EMPTY_STATE;
//
//
//    public static String getWord() {
//            return word;
//    }
//
//    public static void setWord(String str) {
//            word = str;
//    }
//
//    public static void updateState(AtomicInteger state){
//        AtomicInteger previousState = stateOfRepo;
//        stateOfRepo = state;
//        System.out.println("State Change: " + previousState + " -> " + stateOfRepo);
//
//    }
//
//    public static AtomicInteger getState(){
//        return stateOfRepo;
//    }
//
//    //Used to clean up the code inside of the Threads
//    public static void startWait(){
//        try {
//            lock.wait();
//            lock.notify();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
