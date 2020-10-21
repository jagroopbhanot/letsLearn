package selfLearning;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class JavaStaticBlock {
    private static final int B_MIN ;
    private static final int B_MAX ;
    private static final int H_MIN ;
    private static final int H_MAX ;
    private static boolean flag = false;
    private static int B, H;

static
    {
         B_MAX = 100;
         B_MIN = 0;
         H_MAX = 100;
         H_MIN = 0;
         
         try
            {
                Scanner sc = new Scanner(System.in);
                 B=sc.nextInt();
                 H=sc.nextInt();
                
                if(B<=B_MIN || H <=H_MIN)
                {
                    throw new Exception("Breadth and height must be positive");
                }
                else
                {
                    flag = true;
                }
            }
            catch(Exception e)
            {
                System.out.println(e.fillInStackTrace());
            }
    }
public static void main(String[] args){
		if(flag){
			int area=B*H;
			System.out.print(area);
		}
		
	}//end of main

}//end of class

