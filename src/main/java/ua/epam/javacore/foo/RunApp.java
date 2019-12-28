package ua.epam.javacore.foo;

public class RunApp {

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        int[] arr = {1, 3, 2};
        MyThread firstThread = new MyThread(foo, arr[0]);
        MyThread secondThread = new MyThread(foo, arr[1]);
        MyThread thirdThread = new MyThread(foo, arr[2]);
        thirdThread.start();
        secondThread.start();
        firstThread.start();
    }

}
