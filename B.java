package selfLearning;

/*class A
{
	int add(int i, int j)
	{
		return i+j;
	}
}*/
public class B  {
	public static void main(String[] args) {
		
		/* heello word **** */
		///* This is also a comment // more comment */
		try {
		Float f = new Float("3.0");
		int x= f.intValue();
		byte b = f.byteValue();
		double d = f.doubleValue();
		
		System.out.println(x+b+d);
		}
		catch(NumberFormatException e )
		{
			System.out.println("bad number");
		}
	}
}
