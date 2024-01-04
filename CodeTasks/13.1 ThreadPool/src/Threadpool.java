import java.util.concurrent.LinkedBlockingQueue;

 // A thread-pool that can receive tasks from its submit and will execute them
 // concurrently with the number of workers provided in the constructor.
public class Threadpool {
    Thread[] workers;
    LinkedBlockingQueue<Task> queue;
    boolean running = true;

     // Creates a new pool that will execute tasks with the given amount of workers.

    public Threadpool(int numWorkers) {
        if (numWorkers <= 0)
            throw new IllegalArgumentException(
                    "Must have more than 0 Workers!");

        queue = new LinkedBlockingQueue<>();

        workers = new Thread[numWorkers];
        Runnable worker = () -> {
            try {
                while (true) {
                    Task t = queue.take();
                    t.getRunnable().run();
                    t.getFuture().finish();
                }
            } catch (InterruptedException e) {
                return;
            }
        };
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(worker);
            workers[i].start();
        }
    }

     // Submits a new task to get executed by the pool. If the queue is full this method will block until space is available.
    public Future submit(Runnable job)
            throws InterruptedException, ShutdownException {
        if (running) {
            Future result = new Future();
            queue.put(new Task(job, result));
            return result;
        } else {
            throw new ShutdownException();
        }
    }

     // Immediately stops executing tasks, interrupting all workers. Waiting tasks
     // will not get executed and no new tasks will be accepted after calling this method
    public void shutdownNow() {
        running = false;
        for (Thread t : workers)
            t.interrupt();
    }
}
