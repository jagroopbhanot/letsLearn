package selfLearning;

import java.util.Scanner;

public class JavaEOF {
	public static void main(String[] args) {
		callEOF();
	}

	private static void callEOF() {
		Scanner my_scan = new Scanner(System.in);
		int i=0;
		while(my_scan.hasNext())
		{
			if(my_scan.equals("end-of-file"))
			{
				break;
			}
			i++;
		      String my_str = my_scan.nextLine();
		      System.out.println(i + " "+my_str);

		}
		my_scan.close();
	   //   String my_str = my_scan.nextLine();
	    //  System.out.println("The string is "+my_str);

		
	}

}
