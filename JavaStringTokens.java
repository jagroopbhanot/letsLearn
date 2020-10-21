package selfLearning;

import java.util.Scanner;
import java.util.StringTokenizer;

public class JavaStringTokens {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        scan.close();
        String delims = "[_\\\\@!?.', ]";

        StringTokenizer st = new StringTokenizer(s,(delims));
        int x = st.countTokens();

        System.out.println(x);
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
	}
}
