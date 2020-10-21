package selfLearning;

public class JavaObjectCompare 
{
	public static void main(String[] args) 
	{
		Person p1 = new Person("jagroop",35);
		Person p2 = new Person("jagroop",36);
		System.out.println("Hashcode of first object:" +p1.hashCode());
		System.out.println("Hashcode of second object:" +p2.hashCode());

		System.out.println("Checking if objects are equal or not");
		System.out.println(p1.equals(p2));
	}
}
class Person
{
	private String name;
	private int age;
	
	public Person(String name, int age)
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
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj ==null)
			return false;
		if(obj==this)
			return true;
		
		return this.getName() == ((Person)obj).getName() || this.getAge() == ((Person)obj).getAge();
	}
	
}
