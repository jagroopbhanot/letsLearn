package selfLearning;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaArrayList {

	public static void main(String[] args) {
		/* Create and fill Linked List of Integers */
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<ArrayList<Integer>> all = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int d = sc.nextInt();
			ArrayList<Integer> l = new ArrayList<>();
			for (int j = 0; j < d; j++) {
				l.add(sc.nextInt());
			}
			all.add(l);
		}
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int q1 = sc.nextInt();
			int q2 = sc.nextInt();
			if (q2 > all.get(q1 - 1).size())
				System.out.println("ERROR!");
			else
				System.out.println(all.get(q1 - 1).get(q2 - 1));
		}
	}
}