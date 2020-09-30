public class AttemptCounter {
    private static AttemptCounter attemptCounter;


    public static synchronized AttemptCounter getAttemptCounter(){
        if (attemptCounter == null) {
            attemptCounter = new AttemptCounter();
        }
        return attemptCounter;
    }

    private AttemptCounter(){

    }
}
