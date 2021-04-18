import java.util.concurrent.atomic.AtomicInteger;

public class WordRepository {
    private String word;


    static AtomicInteger EMPTY_STATE = new AtomicInteger(0);
    static AtomicInteger READING_STATE = new AtomicInteger(1);
    static AtomicInteger WRITING_STATE = new AtomicInteger(2);
    static AtomicInteger COMPLETED_STATE = new AtomicInteger(3);

    private AtomicInteger stateOfRepo =EMPTY_STATE;


    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void updateState(AtomicInteger state){
        AtomicInteger previousState = this.stateOfRepo;
        this.stateOfRepo = state;
        System.out.println("State Change: " + previousState + " -> " + this.stateOfRepo);

    }

    public AtomicInteger getState(){
        return this.stateOfRepo;
    }
}
