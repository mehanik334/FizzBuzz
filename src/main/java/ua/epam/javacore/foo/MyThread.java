package ua.epam.javacore.foo;

public class MyThread extends Thread {

    private Foo foo;
    private int number;

    public MyThread(Foo foo, int number) {
        this.foo = foo;
        this.number = number;
    }

    public void run() {

        switch (number) {
            case 1:
                foo.first();
                break;
            case 2:
                foo.second();
                break;
            case 3:
                foo.third();
                break;
        }

    }
}
