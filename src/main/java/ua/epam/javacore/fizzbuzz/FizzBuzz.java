package ua.epam.javacore.fizzbuzz;

import java.io.PrintStream;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {

    private final int n;
    private PrintStream print = System.out;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final AtomicBoolean condition = new AtomicBoolean(false);

    private Semaphore fizzSem;
    private Semaphore buzzSem;
    private Semaphore fizzBuzzSem;
    private Semaphore numSem;

    public FizzBuzz(int n) {
        this.n = n;
        this.fizzSem = new Semaphore(0);
        this.buzzSem = new Semaphore(0);
        this.fizzBuzzSem = new Semaphore(0);
        this.numSem = new Semaphore(1);
    }

    public void setPrint(PrintStream print) {
        this.print = print;
    }

    private void goApp() {
        if (counter.getAndIncrement() == n) {
            condition.set(true);
            fizzSem.release();
            buzzSem.release();
            fizzBuzzSem.release();
            numSem.release();
        } else {
            if (counter.get() % 5 == 0 && counter.get() % 3 == 0) {
                fizzBuzzSem.release();
            } else if (counter.get() % 5 == 0) {
                buzzSem.release();
            } else if (counter.get() % 3 == 0) {
                fizzSem.release();
            } else {
                numSem.release();
            }
        }
    }

    public void fizz() {
        while (true) {
            try {
                fizzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.get()) {
                break;
            }
            print.print("fizz ");
            goApp();
        }
    }

    public void buzz() {
        while (true) {
            try {
                buzzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.get()) {
                break;
            }
            print.print("buzz ");
            goApp();
        }
    }

    public void fizzBuzz() {
        while (true) {
            try {
                fizzBuzzSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.get()) {
                break;
            }
            print.print("FizzBuzz ");
            goApp();
        }
    }

    public void numeric() {
        while (true) {
            try {
                numSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (condition.get()) {
                break;
            }
            print.print(counter.get() + " ");
            goApp();
        }
    }
}

