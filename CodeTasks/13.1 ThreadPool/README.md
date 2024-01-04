Task:

Thread Pools


A common way of running concurrent operations efficiently without having to synchronize too much is with thread pools. Essentially, a thread pool consists of a buffer in which the tasks to be carried out are stored. These tasks are then processed by several worker threads. The number of workers is specified when the thread pool is created.

Write the class Threadpool, which receives an int in the constructor, which specifies how many threads this thread pool should take. Internally, the thread pool should use a LinkedBlockingQueue to manage the tasks to be carried out. The workers are constantly waiting for a new task to be added to the queue and then process it. For this purpose, the thread pool has a method Future submit(Runnable), which inserts a task into the queue and returns a future that represents this task. A future has a method void get() in this task, which waits until the task has been completed. Normally, a future would return a result, but since we are only executing Runnables that do not return anything, the future does not return anything either. The template also has a Task class that contains a runnable and a future.

The exact process is then as follows:

A new future is generated in submit, and the Runnable is stored as a task in the queue and the Future is returned.

A worker will then take the Task, execute the Runnable and, as soon as he is finished, set the Future to completed with the finish() method (a boolean in the Future is useful for this) ). In this way, the Future wakes up all the threads that are waiting for it.

In get the Future is waited until the Future is finished.

Finally, the thread pool has the method shutdownNow(), which ensures that every further call to submit throws a ShutdownException (you have to define this first) and also terminates all workers (see the method interrupt()). Tasks that are still waiting in the queue will no longer be executed.

Then write a framework program Main that uses this thread pool to output the numbers from 1 - 1000 together with the name of the thread. Don't forget to end your thread pool at the end!
