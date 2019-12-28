import org.junit.Assert;
import org.junit.Test;
import ua.epam.javacore.foo.Foo;
import ua.epam.javacore.foo.MyThread;

public class MyThreadTest {

    private Foo foo = new Foo();
    private int[] arr = {2,3,1};
    private String expectedShowTrue = "firstsecondthird";
    private String expectedShowFalse = "secondthird";
    private MyThread thread1 = new MyThread(foo,arr[0]);
    private MyThread thread2 = new MyThread(foo,arr[1]);
    private MyThread thread3 = new MyThread(foo,arr[2]);

    @Test
    public void checkShowTrue() {
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread3.join();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedShowTrue,Foo.show);
    }

    @Test
    public void checkShowFalse() {
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread3.join();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedShowFalse,Foo.show);
    }
}
