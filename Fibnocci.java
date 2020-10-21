package selfLearning;

public class Fibnocci {

	public static void main(String[] args) 
	{
	
		int n1=1,n2=1, n3=0;
		System.out.println(n1);
		System.out.println(n2);

		for(int i=2; i<10; i++)
		{
			n3=n1+n2;
			n1=n2;
			n2=n3;
			System.out.println(n3);

		}
		
		System.out.println("The Fibnocci number at the given index is = "+getNumber(7));
	}
	
	public static int getNumber(int index)
	{
		int j1=1,j2=1,j3=0;
		for(int i=2; i<=index; i++)
		{
			j3=j1+j2;
			j1=j2;
			j2=j3;
		}
		return j3;
	}
}
