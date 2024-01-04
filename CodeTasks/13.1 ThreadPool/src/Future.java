public class Future {
    private boolean finished = false;

   // Waits till the future is marked as finished
   // Throws: InterruptedException – if interrupted while waiting
    public synchronized void get() throws InterruptedException {
        while (!finished) {
            wait();
        }
    }

     // Marks the future as finished, waking all waiting threads
    public synchronized void finish() {
        finished = true;
        notifyAll();
    }
}
