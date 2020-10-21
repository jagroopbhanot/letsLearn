package selfLearning;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CheckDBConnectionOverSSL {
   
	//private static final String RDS_INSTANCE_HOSTNAME = "idmt01.czvfccuvoe6r.ap-southeast-2.rds.amazonaws.com";
    
   // private static final int RDS_INSTANCE_PORT = 1521; 
   // private static final String REGION_NAME = "ap-southeast-2";
    private static final String DB_USER = "idm_tst";
    private static final String DB_PSWD = "Welcome01";

	//private static final String RDS_SID = "IDMT01";
    //private static final String JDBC_URL = "jdbc:oracle:thin:@" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + ":"+RDS_SID;

	private static final String JDBC_URL = "jdbc:oracle:thin:@(DESCRIPTION= (ADDRESS=(PROTOCOL=TCPS)(PORT=2484)(HOST=idmt01.czvfccuvoe6r.ap-southeast-2.rds.amazonaws.com))(CONNECT_DATA=(SERVICE_NAME=IDMT01))(SECURITY=(ssl_server_cert_dn=\"CN=Amazonsfvsfss RDS Root 2019 CA, OU=Amazon RDS, O=Amazon Web Services, Inc., ST=Washington, L=Seattle, C=US\")))";
    private static final String SSL_CERTIFICATE = "rds-root";

    private static final String KEY_STORE_TYPE = "jks";
  //  private static final String KEY_STORE_PROVIDER = "SUN";
   // private static final String KEY_STORE_FILE_PREFIX = "cacerts";
    //private static final String KEY_STORE_FILE_SUFFIX = ".der";
    private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";

    public static void main(String[] args) throws Exception {
        //get the connection
        Connection connection = getDBConnection();

        //verify the connection is successful
        Statement stmt= connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT username from Account where 	user_id=2789");
        while (rs.next()) {
        	    String name = rs.getString(1);
            System.out.println(name); 
        }

        //close the connection
        stmt.close();
        connection.close();
        
        clearSslProperties();
        
    }

    private static Connection getDBConnection() throws Exception {
        setSslProperties();
        return DriverManager.getConnection(JDBC_URL, setDBConnectionProperties());
    }

    private static Properties setDBConnectionProperties() {
        Properties oracleConnectionProperties = new Properties();
        oracleConnectionProperties.setProperty("verifyServerCertificate","true");
        oracleConnectionProperties.setProperty("useSSL", "true");
        oracleConnectionProperties.setProperty("user",DB_USER);
        oracleConnectionProperties.setProperty("password",DB_PSWD);
        return oracleConnectionProperties;
    }

    private static void setSslProperties() throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "C://Program Files/Java/jdk1.8.0_241/jre/lib/security/cacerts");
        System.setProperty("javax.net.ssl.keyStoreType", KEY_STORE_TYPE);
        System.setProperty("javax.net.ssl.keyStorePassword", DEFAULT_KEY_STORE_PASSWORD);
    }

    /**
     * This method returns the path of the Key Store File needed for the SSL verification during the IAM Database Authentication to
     * the db instance.
     * @return
     * @throws Exception
     */
	/*
	 * private static String createKeyStoreFile() throws Exception { return
	 * createKeyStoreFile(createCertificate()).getPath(); }
	 */
    /**
     *  This method generates the SSL certificate
     * @return
     * @throws Exception
     */
	/*
	 * private static X509Certificate createCertificate() throws Exception {
	 * CertificateFactory certFactory = CertificateFactory.getInstance("X.509"); URL
	 * url = new File(SSL_CERTIFICATE).toURI().toURL(); if (url == null) { throw new
	 * Exception(); } try (InputStream certInputStream = url.openStream()) { return
	 * (X509Certificate) certFactory.generateCertificate(certInputStream); } }
	 */

    /**
     * This method creates the Key Store File
     * @param rootX509Certificate - the SSL certificate to be stored in the KeyStore
     * @return
     * @throws Exception
     */
	/*
	 * private static File createKeyStoreFile(X509Certificate rootX509Certificate)
	 * throws Exception { File keyStoreFile =
	 * File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX); try
	 * (FileOutputStream fos = new FileOutputStream(keyStoreFile.getPath())) {
	 * KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
	 * ks.load(null); ks.setCertificateEntry("rootCaCertificate",
	 * rootX509Certificate); ks.store(fos,
	 * DEFAULT_KEY_STORE_PASSWORD.toCharArray()); } return keyStoreFile; }
	 */
    
    /**
     * This method clears the SSL properties.
     * @throws Exception
     */
    private static void clearSslProperties() throws Exception {
           System.clearProperty("javax.net.ssl.trustStore");
           System.clearProperty("javax.net.ssl.trustStoreType");
           System.clearProperty("javax.net.ssl.trustStorePassword"); 
    }
    
}
