package selfLearning;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaExceptionHandling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        try
        {
        	int a=sc.nextInt();
            int b=sc.nextInt();
            System.out.println(Math.pow(-3 , -5));
          System.out.println(a/b);
        }
        catch(Exception e)
        {
        	if (e instanceof ArithmeticException)
        	{
        		System.out.println(e.getClass().getCanonicalName() + ": " +e.getLocalizedMessage());
        	}
        	else
        		System.out.println(e.getClass().getCanonicalName());
        }
	}
}
