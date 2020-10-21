package selfLearning;

import java.util.Scanner;

public class JavaStringReverse {
	 public static void main(String[] args) {
	        
	        Scanner sc=new Scanner(System.in);
	        String A=sc.next();
	        
	        StringBuilder A1 = new StringBuilder(A);
	        A1.reverse();
	        String s = A1.toString();
	        System.out.println((A.equals(s))?"Yes":"No");
	 }
	
}
