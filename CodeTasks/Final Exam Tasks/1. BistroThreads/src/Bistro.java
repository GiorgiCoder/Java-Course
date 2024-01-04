public class Bistro extends Thread{
    private int seats;
    private Thread order, meal, waiter;
    public Bistro(int n){
        seats = n;
        this.waiter = new Thread(() -> this.serve());
        waiter.start();
    }

    public synchronized void dine(int price){
        while (seats <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (this){seats--;}
        notifyAll();
        while (order != null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        order = Thread.currentThread();
        order.setName(Long.toString(order.getId()));
        System.out.println("Guest " + Thread.currentThread().getName() + " orders for " + price + " Lari");
        meal = Thread.currentThread();
//            synchronized (waiter) {
        notifyAll();
        while (meal != null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        meal = Thread.currentThread();
        System.out.println("Guest " + Thread.currentThread().getId() + " eats...");
        notifyAll();
        synchronized (this){seats++;}
        notifyAll();
//            }
    }

    public synchronized void serve(){
        while(!waiter.isInterrupted()) {
            while (order == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String customerName = Long.toString(order.getId());
            order = null;
            notifyAll();
            while (meal == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            meal.setName(customerName);
            notifyAll();
            System.out.println("Enjoy!");
            meal = null;
            notifyAll();
        }
    }

    public void shutdown(){
        waiter.interrupt();

    }

    public static void main(String[] args) {
        Bistro b = new Bistro(2);
        Thread g1 = new Thread(() -> b.dine(10));
        Thread g2 = new Thread(() -> b.dine(20));
        Thread g3 = new Thread(() -> b.dine(30));
        Thread g4 = new Thread(() -> b.dine(40));
        g1.start();
        g2.start();
        g3.start();
        g4.start();
        try {
            g1.join();
            g2.join();
            g3.join();
            g4.join();
        } catch (InterruptedException e) {
            b.shutdown();
        }
    }
}
