import org.junit.Assert;
import org.junit.Test;
import ua.epam.javacore.fizzbuzz.FizzBuzz;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz = new FizzBuzz(5);
    private ByteArrayOutputStream byteArr = new ByteArrayOutputStream(256);
    private String expected = "0 1 2 fizz 4 buzz ";

    @Test
    public void fizzBuzzTest() {
        fizzBuzz.setPrint(new PrintStream(byteArr));

        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(fizzBuzz::fizz));
        threads.add(new Thread(fizzBuzz::buzz));
        threads.add(new Thread(fizzBuzz::fizzBuzz));
        threads.add(new Thread(fizzBuzz::numeric));
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(expected, byteArr.toString());
    }
}


