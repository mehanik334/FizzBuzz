package ua.epam.javacore.foo;


import java.util.concurrent.Semaphore;

public class Foo {

    private Semaphore semaphore1;
    private Semaphore semaphore2;
    public static String show = "";


    public Foo() {
        try {
            semaphore1 = new Semaphore(1);
            semaphore2 = new Semaphore(1);
            semaphore1.acquire();
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void first() {
        Foo.show += "first";
        System.out.println(Foo.show);
        semaphore1.release();
    }

    public void second() {
        try {
            semaphore1.acquire();
            semaphore1.release();
            Foo.show += "second";
            System.out.println(Foo.show);
            semaphore2.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            semaphore2.acquire();
            semaphore2.release();
            Foo.show += "third";
            System.out.println(show);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
