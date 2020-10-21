package selfLearning;

public class LoopII {

	public static void main(String[] args) 
	{
		//System.out.println(Math.pow(2, 4));
		
		for(int c = 0; c<5; c++)
		{
			//System.out.println(Math.pow(2, c));
		}
		  int a = 5; int b = 3; int n = 5;
		  int j=0, k=1;
		  //System.out.print(b);
		  int tempResult =0;
		  for(int i = 0; i<n; i++) 
		  { 
			  tempResult =0;
			  for(j=0; j<=i; j++)
			  {
				//  System.out.println("a = "+a);
				//  System.out.println("Math.pow(2,j) = "+Math.pow(2,j));
				//  System.out.println("Math.pow(2,j)*b = "+Math.pow(2,j)*b);
				  
				  tempResult += (int) (a+(Math.pow(2,j)*b)); 
				 // System.out.println("final a = "+a);
			  }
			  System.out.print(" " +(int)(a+tempResult));
			 // b=a;
			  j++; 
		  }
		 
	}
}
