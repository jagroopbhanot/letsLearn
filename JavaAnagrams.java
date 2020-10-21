package selfLearning;

import java.util.Arrays;
import java.util.Scanner;

public class JavaAnagrams {
	 static boolean isAnagram(String a, String b) {
	        // Complete the function

		 char[] word1 = a.toLowerCase().toCharArray();
		 char[] word2 = b.toLowerCase().toCharArray();
		 Arrays.sort(word1);
		 Arrays.parallelSort(word2);
		 return Arrays.equals(word1,word2);
	    }

	public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
	        String a = scan.next();
	        String b = scan.next();
	        scan.close();
	        boolean ret = isAnagram(a, b);
	        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
	    }
	
}
