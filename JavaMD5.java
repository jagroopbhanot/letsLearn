package selfLearning;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaMD5 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String yourString = "HelloWorld";
        MessageDigest md = MessageDigest.getInstance("MD5"); 
        byte[] messageDigest = md.digest(yourString.getBytes()); 
        BigInteger no = new BigInteger(1, messageDigest); 
        String hashtext = no.toString(16); 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
        System.out.println(hashtext); 

	}

}
