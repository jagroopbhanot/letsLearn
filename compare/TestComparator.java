package selfLearning.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparator {

	public static void main(String[] args) {
		List<Person> per = new ArrayList<Person>();
		
		per.add(new Person("Jagroop", 35));
		per.add(new Person("Isha",35));
		per.add(new Person("Aarav",8));
		per.add(new Person("Br",1));
		
		Collections.sort(per, new Person());
		
		for (Person person : per) {
			System.out.println(person.toString());

		}
		

	}
}
