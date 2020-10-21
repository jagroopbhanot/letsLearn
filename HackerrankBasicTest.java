package selfLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HackerrankBasicTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        fizzBuzz(n);

        bufferedReader.close();
    }
    public static void fizzBuzz(int n) 
    {
        for(int i=1; i<=n; i++)
        {
            if(i%3 == 0 && i%5 ==0)
            System.out.println("FizzBuzz");
            else if (i%3 ==0 && i%5 !=0)
            System.out.println("Fizz");
            else if(i%5 ==0 && i%3 !=0)
            System.out.println("Buzz");
            else
            System.out.println(i);
        }
    }
}

