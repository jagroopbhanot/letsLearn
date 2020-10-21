package selfLearning;

import java.util.*;

public class Java1DArray {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		int a[] = new int[number];
		scan.close();

		// Prints each sequential element in array a
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}