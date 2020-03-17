public class Main {

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Main m = new Main();
        Thread t1 = new Thread(() -> {
            try {
                m.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                m.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                m.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() throws InterruptedException {
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'A') {
                    mon.wait();
                }
                System.out.print("A");
                currentLetter = 'B';
                mon.notifyAll();
            }
        }
    }
    public void printB() throws InterruptedException {
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'B') {
                    mon.wait();
                }
                System.out.print("B");
                currentLetter = 'C';
                mon.notifyAll();
            }
        }
    }
    public void printC() throws InterruptedException {
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (currentLetter != 'C') {
                    mon.wait();
                }
                System.out.print("C");
                currentLetter = 'A';
                mon.notifyAll();
            }
        }
    }
}
