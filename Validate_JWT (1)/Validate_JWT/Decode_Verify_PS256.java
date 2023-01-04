//package au.com.cuscal.domain.web.rest.validator;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.SignedJWT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public class Decode_Verify_PS256 {


    public static void main(String s[]) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, IOException {


        String publicKeyString = "-----BEGIN CERTIFICATE-----MIIDETCCAfmgAwIBAgIJAJ798i52XUQvMA0GCSqGSIb3DQEBCwUAMEgxEzARBgNVBAMMClVOSUNPUk5fZHIxEzARBgNVBAoMClBpcmVhbiBMdGQxDzANBgNVBAcMBkxvbmRvbjELMAkGA1UEBhMCR0IwHhcNMjEwNDE2MjAyNjIyWhcNMzEwNDE2MjAyNjIyWjBIMRMwEQYDVQQDDApVTklDT1JOX2RyMRMwEQYDVQQKDApQaXJlYW4gTHRkMQ8wDQYDVQQHDAZMb25kb24xCzAJBgNVBAYTAkdCMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkule1EZoBB6l+xyBj0VprCt3E44D1JvbXPSm0GuKSajcJpwtQ3uLCnCLvwv5fe9xWievzXmf3DIi0S+WY7cGfLuSAHJPFua09tqlPQQIiv2tyhasBRc12g1pp0VjLOVjqSW5ZlHiva274NhImDa1eYX0gIjfAsJOmgL6dc8shtoqpQsfNG4SjG29RXtif4c4nSkH5crXwQZxsBD4jPVSrXK4egLxV7Sx9sITd3ORnstxt9JmSHMymW0iHjY8kF9b/nj9+DipjDBwplKz2rfR1Y377IpnxOKFwe/CARH9gplhpgtlosGINLDWIj4wP1SOM4NeJMumy4iHjF1M5lw68wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQB8ch2XrWnBZCROo9eAwiCrLSBR5QSKeBlpQTFADWeMDBYNCkaA8yJojPnoT7S+KUc6soeIBCq0/qrYGPLk7U+YYr0G+iWVZZvkeQ5yWZ0ZYwfWuVyI1Rh5+C4ybubUu9TnP3aoJ7OJpTdPMvjrqUjaS2/1R3vbmQKT6vvtV8BMhyNaQc0XsYXj20QuIwhItrfllZagzbigrosEE5wGqcjgcZzT3VIgeHZ/jBS3lS3/H0MZ4dgFAChLqkoiATzdIkWFhuoL57B63BGjuawqUPEWQvtuvarQxJdEpme/58j7OY22F6FlKdauDAB+4mGmSVvqDclFjbth/IWcOlgOC/v9-----END CERTIFICATE-----";


        //this is for x509 certificate
        X509Certificate cert = X509CertUtils.parse(publicKeyString);

        // Retrieve public key as RSA JWK
        RSAKey rsaJWK = RSAKey.parse(cert);

        //this is only to support the other code - pkcs8 formatted

//        String publicKeyPEM = publicKeyString
//            .replace("-----BEGIN PUBLIC KEY-----", "")
//            .replaceAll(System.lineSeparator(), "")
//            .replace("-----END PUBLIC KEY-----", "");
//
//        byte[] encoded = Base64.decodeBase64(publicKeyPEM);
//
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
//        RSAPublicKey senderPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);


        RSAPublicKey senderPublicKey = rsaJWK.toRSAPublicKey();

        String s3 = "eyJraWQiOiJqd3QtcnNhIiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJjdXNjYWwgdGVzdCIsInNzX3R5cGUiOiJTdHJpbmciLCJpc3MiOiJBOjEiLCJ0eXAiOiJSUzI1NiIsInJfY29kZSI6IlJ2OGIzbGE0OG8xZ21pOGdtaHMxOXloa3Q5OW41Z2Q3Iiwic2Vzc2lvbklkIjoiU3RyaW5nIiwicGFyYW1zIjp7ImR1cmF0aW9uIjoiMTAwIiwiY2RyX2FycmFuZ2VtZW50X2lkIjoiQ0RSIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBiYW5rOmFjY291bnRzLmJhc2ljOnJlYWQgYmFuazphY2NvdW50cy5kZXRhaWw6cmVhZCJ9LCJ1c2VySWQiOiJhY2NjdW5pY29ybmJhbmsiLCJhdWQiOiJjb25zZW50LnVpLmNkcy5jdXNjYWwuY29tLmF1IiwibmJmIjoxNjI2MzQxMzE5LCJzcF9pZCI6IjMwNTFiM2FiLTQwOTYtZWExMS1hODMxLTAwMGQzYTgiLCJkYXRhX2hvbGRlcl9icmFuZF9pZCI6ImFjY2N1bmljb3JuYmFuayIsImV4cCI6MTYyNjM0NDkxOSwiaWF0IjoxNjI2MzQxMzE5LCJqdGkiOiIwOTg2NGMzMS1kN2YyLTQyZWMtOGU2ZC02Yjc5NGY2M2NkZWUiLCJsb2EiOiIxLjAifQ.AtUrz6a9aGa7M6kzVZ_iWCU-c_fOpDYYGzGFZHyimYvD5pshcZLk7iJxJmrhEzuclOmYN9KOFXr_K6U9p8dgzcnXsB8bG1AGP3b4WHj_F1gfnRMExUjU-5jokzfhT18hWVedG4SHsQqNlKwy6JUA5VMXkJL74j1OlwJku8Y0coGUwOYr2W5ptu18sz7e83DpaiNaNLTNp-c4039iR0s47hP14FZv1WBciRCBqm6Zi4LyuxPwSsmFcKuAAsRkcBK8YqM0Oz1Uf8F2HibLVa3VVdDtwamu_4V9OIukrxoYAQJBGQ3Jqj7cjb6CQWVSs3O4Z9JGRSa54PvcvAGJ3D7E3A";
        SignedJWT signedJWT1 = SignedJWT.parse(s3);
        System.out.println("signedJWT.getPayload().toString()" + signedJWT1.getPayload().toString());

        // Create RSA verifier and set BC FIPS provider
        JWSVerifier verifier = new RSASSAVerifier(senderPublicKey);
        verifier.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());

        System.out.println("verification result::" + signedJWT1.verify(verifier));


    }


}
