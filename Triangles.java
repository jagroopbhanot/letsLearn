package selfLearning;

public class Triangles 
{

	public static void main(String[] args) 
	{
		//triAngle1();
		//triAngle2();
		triangle3();
	}
	
	public static void triAngle1()
	{
		/*
		 1
		 1 2
		 1 2 3
		 1 2 3 4
		 1 2 3 4 5      
		 * 
		 * */
		
		for(int i=1; i<=5; i++)
		{
			for(int j=1; j<=i; j++)
			{
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}
	
	public static void triAngle2()
	{
		/*
		 1
		 2 3
		 3 4 5
		 4 5 6 7
		 * 
		 * */

		int k=1;
		for(int i=1; i<=5; i++)
		{
			k = i;
			for(int j=1; j<=i; j++)
			{
				//k++;
				System.out.print(k + " ");
				k++;
			}
			System.out.println();
		}
	}
	
	public static void triangle3()
	{
		
		/*
		* *
	   * * *
	  * * * *
	 * * * * *
		 * 
		 * */
		
		for (int i = 0; i <=5; i++) 
		{
			for(int k=5;k>i; k--)
			{
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++)
			{
				
				System.out.print("* ");
			}
				System.out.println();
		}
	}
}
