package selfLearning.compare;

public class Employee implements Comparable<Employee>{

	private String name;
	private int age;
	
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public int compareTo(Employee o) {
		
//compare based on numbers
		/*
		 * if(this.getAge() > o.getAge()) return 1; else if(this.getAge()== o.getAge())
		 * return 0; else return -1;
		 */
		
//compare based on string
		return this.name.compareTo(o.name);
	}
	
	@Override
	public String toString()
	{
		return "Employee ["+ name + " " + age + " ]";
	}
}
