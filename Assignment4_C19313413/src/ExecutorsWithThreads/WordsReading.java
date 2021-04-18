package ExecutorsWithThreads;

import java.util.concurrent.Callable;

public class WordsReading implements Callable<String> {

    @Override
    public String call() throws Exception{

        synchronized (WordRepository.getWord()) {
            //3 seconds allocated for Learning Word
            TerminationTesting.Utilities.sleep(3);
            System.out.println("WordsReading name : " + Thread.currentThread().getName() + " word : " + WordRepository.getWord());
            WordRepository.updateState(WordRepository.READING_STATE);
            System.out.println("State: " + WordRepository.READING_STATE);
            WordRepository.startWait();

        }
        return null;
    }
}
