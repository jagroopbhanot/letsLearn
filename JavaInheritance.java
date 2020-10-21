package selfLearning;

class Animal
{
	Animal()
	{
		System.out.println(" Animal Class --> Constructor");
	}
	
	public void run()
	{
		System.out.println("Animal Class --> run");
	}
}
public class JavaInheritance extends Animal{

	public static void main(String[] args) {
		JavaInheritance j = new JavaInheritance();
		j.run();
		
	}
	
	public JavaInheritance() {
		System.out.println("JavaInheritance --> Constructor");
	}
	
	public void run()
	{
		super.run();
		System.out.println("JavaInheritance --> run");
	}
}
