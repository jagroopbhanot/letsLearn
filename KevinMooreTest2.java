package selfLearning;

public class KevinMooreTest2 {

	public static int setInt(int i)
	{
		i = 2;
		return i;
	}

	public static void setInteger(int j)
	{
		j = 3;
	}

	public static void setInteger(Integer k)
	{
		k = 9;
	}
	 int i = 1;
	
	
	static Integer j = 1;
	
	static Integer k = new Integer(3);

	
	
	public static void main(String[] args) {
		
		KevinMooreTest2 km = new KevinMooreTest2();
		km.i = km.i +10;
		KevinMooreTest2 km1 = new KevinMooreTest2();

		//setInt(KevinMooreTest2.i);
		setInteger(KevinMooreTest2.j);
		setInteger(KevinMooreTest2.k);
		System.out.println("int1 = " + km.i + " int2 = "+j + " int3 ="+k);
		System.out.println(km1.i);
	}
}
