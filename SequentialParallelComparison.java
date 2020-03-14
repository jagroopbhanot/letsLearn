package streamFilterinJava8;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

//https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/sequential-vs-parallel.html

public class SequentialParallelComparison {

    public static void main (String[] args) {
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        System.out.println("-------\nRunning sequential\n-------");
        run(Arrays.stream(strings).sequential());
        System.out.println("-------\nRunning parallel\n-------");
        run(Arrays.stream(strings).parallel());
    }

    public static void run (Stream<String> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                                " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}