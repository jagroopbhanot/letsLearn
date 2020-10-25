package selfLearning.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable {

	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<Employee>();
		
		emps.add(new Employee("Jagroop", 35));
		emps.add(new Employee("Isha",35));
		emps.add(new Employee("Aarav",8));
		
		Collections.sort(emps);
		
		for (Employee employee : emps) {
			System.out.println(employee.toString());

		}
	}
}
