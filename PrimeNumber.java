package selfLearning;

public class PrimeNumber {
	public static void main(String[] args) {
		
		System.out.println(getNumber(11));
	}
	
	public static String getNumber(int value)
	{
		boolean flag = false;
		for (int i=2; i<=value/2; i++) 
		{
			if(value%i ==0)
			{
				flag = true;
				break;
			}
		}
		if(!flag)
			return "prime";
		else
			return "not prime";
	}

}
