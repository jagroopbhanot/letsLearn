package selfLearning;

public class Factorial {
	public static void main(String[] args) 
	{
		
		int i = 5;
		System.out.println(getFactorial(i));
	}

	private static int getFactorial(int i) 
	{
		int k =1;
		for(int j=1;j<=i; j++)
		{
			k=k*j;
		}
		return k;
	}

}
