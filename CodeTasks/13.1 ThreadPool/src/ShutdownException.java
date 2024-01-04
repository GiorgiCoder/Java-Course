

 // Exception thrown by a Threadpool when a task is submitted after shutting down

@SuppressWarnings("serial")
public class ShutdownException extends Exception {
    public ShutdownException() {
        super("Threadpool has already been shut down!");
    }
}
