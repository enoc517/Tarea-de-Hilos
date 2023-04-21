package bingoporhilos;

import static java.lang.Thread.sleep;

public class HilosdelBingo extends Thread {

    int k;

    HilosdelBingo(int i) {
        this.k = i;

    }

    @Override
    public void run() {
        System.out.println("Proceso: " + k);
        if (k > 1) {
            try {
                System.out.println("....");
                sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println(ex.getLocalizedMessage());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Primer mensaje");
        HilosdelBingo p1 = new HilosdelBingo(1);
        HilosdelBingo p2 = new HilosdelBingo(2);
        HilosdelBingo p3 = new HilosdelBingo(3);
        p1.start();
        p2.start();
        p3.start();
        System.out.println("Ultimo mensaje");
    }

}
