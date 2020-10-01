public class AttemptCounter {
    private static AttemptCounter attemptCounter;
    private static int count = 0;

    public static synchronized AttemptCounter getInstance(){
        if (attemptCounter == null) {
            attemptCounter = new AttemptCounter();
        }
        return attemptCounter;
    }

    private AttemptCounter(){

    }

    public void addLog() throws TooManyLoginAttemptsException {
        count ++;
        if (count > 5) {
            throw new TooManyLoginAttemptsException("\nYou are a crook, GET OUT!");
        }
    }

    public int getLogs(){
        return count;
    }
}
