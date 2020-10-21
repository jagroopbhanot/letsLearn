package selfLearning;

public class JavaSingleton 
{
	public static void main(String[] args) 
	{
		Employee e = Employee.getInstance();
		Employee e1 = Employee.getInstance();
		Employee e2 = Employee.getInstance();
		Employee e3 = Employee.getInstance();
		Employee e4 = Employee.getInstance();
		Employee e5 = Employee.getInstance();

		System.out.println(e.getName() + " " + e.getAge() ); 
		System.out.println(e1.getName() + " " + e1.getAge() ); 
		System.out.println(e2.getName() + " " + e2.getAge() ); 
		System.out.println(e3.getName() + " " + e3.getAge() ); 
		System.out.println(e4.getName() + " " + e4.getAge() ); 
		System.out.println(e5.getName() + " " + e5.getAge() ); 

	}
	
}
class Employee
{
	private String name;
	private int age;
	private static Employee e = null;
	
	private Employee()
	{
		
	}
	public static Employee getInstance()
	{
		if(e ==null)
		{
			e = new Employee("jagroop",40);
		}
		return e;
	}
	private Employee(String name, int age)
	{
		this.name= name;
		this.age= age;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getAge() 
	{
		return age;
	}
	public void setAge(int age) 
	{
		this.age = age;
	}
	
}
