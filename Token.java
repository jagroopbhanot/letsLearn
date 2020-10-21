package selfLearning;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Token {

	public static void main(String[] args) throws SQLException {
		
		/*
		 * System.out.println(Token.validateToken("434feb95-613c-478d-97e3-3d38ee39028e"
		 * )); System.out.println(Token.validateToken("jagroop"));
		 * 
		 * System.out.println(Token.validateToken("jagroop"));
		 * System.out.println(Token.validateToken(""));
		 * System.out.println(Token.validateToken("JAGROOP"));
		 * 
		 System.out.println(Token.validateToken("jagroop"));*/
		getDataFromDB();
	}

	private static void getDataFromDB() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String connectionString = "jdbc:oracle:thin:@idmt01.czvfccuvoe6r.ap-southeast-2.rds.amazonaws.com:1521:IDMT01";
		
		String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcps)(HOST=idmt01.czvfccuvoe6r.ap-southeast-2.rds.amazonaws.com)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=IDMT01))))"; 
		String userName = "idm_tst";
		String pswd = "c";
		
		Properties props = new Properties( );

		props.setProperty( "user", "idm_tst" );
		props.setProperty( "password", "Welcome01" );
		//props.setProperty("javax.net.ssl.trustStore", "/truststore/ewallet.p12"); 
		//props.setProperty("javax.net.ssl.trustStoreType","PKCS12"); 
		//props.setProperty("javax.net.ssl.trustStorePassword","welcome123"); 
		//props.setProperty("oracle.net.ssl_cipher_suites", "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
		//props.setProperty("oracle.net.ssl_cipher_suites",
		//		"(SSL_DH_anon_WITH_3DES_EDE_CBC_SHA, SSL_DH_anon_WITH_RC4_128_MD5,SSL_DH_anon_WITH_DES_CBC_SHA)");
		 
		  props.setProperty("oracle.net.ssl_server_dn_match", "true");
		  props.setProperty("javax.net.ssl.keyStore",  "C:\\Program Files\\Java\\jdk1.8.0_241\\jre\\lib\\security\\keystore.jks");
		  props.setProperty("javax.net.ssl.keyStoreType","jks");
		  props.setProperty("javax.net.ssl.keyStorePassword","Asdf@123456");
		 
				//Connection conn = DriverManager.getConnection(url, props); 
		
		try  
		 {
			// conn = DriverManager.getConnection(connectionString, userName, pswd); 
			 //conn = DriverManager.getConnection(url,userName, pswd); 
			conn = DriverManager.getConnection(url, props); 
	

	            if (conn != null) 
	            {
	                System.out.println("Connected to the database!"+conn);
	                
	                 stmt = conn.createStatement();
	                 rs = stmt.executeQuery("SELECT auth_token, username FROM account WHERE auth_token is not null");
	                
	               
	      			/*When you get token from log to compare then just put it in the field name "strhashcodeToCompare". As it will already be hashed value
	      			using hashcode() method of string class.
	      			So no need to call hashcode() method again.
	      			*/
	      			
				  Map<String, String> bulkDataSet = resultSettoMap(rs);
				  
				  String tokenValueToCompare = "0ddd7839-eb6c-4906-9856-056889609396"; //"434feb95-613c-478d-97e3-3d38ee39028e";
				  
				  //For testing purpose.
				  //Comment following line
				  //int strhashcodeToCompare = tokenValueToCompare.hashCode();
				  int strhashcodeToCompare = -1242779638;
				  
				  validateToken(strhashcodeToCompare, bulkDataSet);
				    
	            } 
	            else 
	            {
	                System.out.println("Failed to make connection!");
	            }

	        } 
		 catch (SQLException e) 
		 {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	     } 
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	     }
		 finally
		 {
			rs.close();
			stmt.close();
			 conn.close();
		 }
	}
	
	private static Map<String ,String> resultSettoMap(ResultSet rs) throws SQLException {
		
		Map<String ,String> rsToMap = new HashMap<String, String>();
		while(rs.next())
		{
			rsToMap.put(rs.getString(1), rs.getString(2));
		}
		return rsToMap;
	}
	
	private static int validateToken(int strhashcode, Map<String, String> inputData)
	{
		String result = "";

		result = inputData.entrySet().stream()
                .filter(x -> {
                    if ((x.getKey().hashCode()) == strhashcode ) {
                        return true;
                    }
                    return false;
                })
                .map(map -> "The matched data is ----> Auth Token = " + map.getKey() + " UserName = " + map.getValue())
                .collect(Collectors.joining(","));

		if (!result.isEmpty())
			System.out.println(result);
		else
			System.out.println("No Matched Data found");
        
        
		return strhashcode;
	}
}
