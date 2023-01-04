import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONObject;

import javax.crypto.Cipher;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Optional;

public class Generate_Encrypted_Token_Pre {
//JWE - ENCRYPTED PLAIN JWT DEMO

    public static void main(String s[]) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, IOException {

        try {
            if (Cipher.getMaxAllowedKeyLength("AES") < 256) {

                Class jceSecurity = Class.forName("javax.crypto.JceSecurity");
                Field isRestricted = jceSecurity.getDeclaredField("isRestricted");
                Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(isRestricted, isRestricted.getModifiers() & ~Modifier.FINAL);
                isRestricted.setAccessible(true);
                isRestricted.setBoolean(null, false); // isRestricted = false;
                isRestricted.setAccessible(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        String publicKeyString = "-----BEGIN CERTIFICATE-----MIIDlDCCAnwCCQCDH6hG01kTuDANBgkqhkiG9w0BAQsFADCBizELMAkGA1UEBhMCQVUxDzANBgNVBAgMBlN5ZG5leTEPMA0GA1UEBwwGU3lkbmV5MQ0wCwYDVQQKDARSQUNRMQswCQYDVQQLDAJPQjEcMBoGA1UEAwwTcHJlcHJvZC1yYWNxLWN1c2NhbDEgMB4GCSqGSIb3DQEJARYRaG9tQGN1c2NhbC5jb20uYXUwHhcNMjIwMTIxMDczNTQ2WhcNMjUwMTIwMDczNTQ2WjCBizELMAkGA1UEBhMCQVUxDzANBgNVBAgMBlN5ZG5leTEPMA0GA1UEBwwGU3lkbmV5MQ0wCwYDVQQKDARSQUNRMQswCQYDVQQLDAJPQjEcMBoGA1UEAwwTcHJlcHJvZC1yYWNxLWN1c2NhbDEgMB4GCSqGSIb3DQEJARYRaG9tQGN1c2NhbC5jb20uYXUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC2S5ICGKh99jkY35gVJx2jd9KzXmxoXnlfF+hsH+7oe+HnEY0WuaIVlhZUsVGT6o1lxUesdQ7eJ2K1ySP/9hjmSg8vvzZ8IK+aYIbEb7tZwChsHqdsRmMfe06Iz8o0tRDYbj0Zx9qzuiV4xtoIxuPTD9xs1Jm0Rj1U6zyn28F8n9Sp3ili2SQf9dM4+XbWaiMaN0lWv36NH3sjluNkDovKweT7Oc5U8pooqW8iaIxEMym+pQvmap0WQe6AetbOm/BBxeoBVtmjBYIyPpTcrLA4DYHABU2Wvf0UhJsqLfbIO9mBbZlAAhksPki/Q3jMWIYcnXkahNty5EnsaBF6ZLy3AgMBAAEwDQYJKoZIhvcNAQELBQADggEBAFLX1B07Ao6g2TMLN3Frv/NZsBCcl5mVEXJOaen7Kh7KJCmsXBRW/USowvPexeIbOjFXXyJdORUcNB1XKJ8jr6WXUbyk8TDSUF9a70JvkO0d8oy9K8y/eGTXrIajMJG19Ahq+lwYzP53+YqTaREarBed1RzjHkaGJCG4wPDcOpSUXnV2sl6AAvBOy6corVJoGCU3qK1QaiHm1b3+s9jiMjvvSpJ5fudaHBD3fyPgGoI8J6+oZuzuR+0u0o+y8bi+faK5K1GmS371NarmhakxUprbHw8zkbRJ31JUDyQK11tEBPIP392UPRutPtCtfcz6QOtj+JmRGV+BihRCtRAkcuo=-----END CERTIFICATE-----";

        X509Certificate cert = X509CertUtils.parse(publicKeyString);

        // Retrieve public key as RSA JWK
        RSAKey rsaJWK = RSAKey.parse(cert);

        String privatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS6V7URmgEHqX7HIGPRWmsK3cTjgPUm9tc9KbQa4pJqNwmnC1De4sKcIu/C/l973FaJ6/NeZ/cMiLRL5ZjtwZ8u5IAck8W5rT22qU9BAiK/a3KFqwFFzXaDWmnRWMs5WOpJblmUeK9rbvg2EiYNrV5hfSAiN8Cwk6aAvp1zyyG2iqlCx80bhKMbb1Fe2J/hzidKQflytfBBnGwEPiM9VKtcrh6AvFXtLH2whN3c5Gey3G30mZIczKZbSIeNjyQX1v+eP34OKmMMHCmUrPat9HVjfvsimfE4oXB78IBEf2CmWGmC2WiwYg0sNYiPjA/VI4zg14ky6bLiIeMXUzmXDrzAgMBAAECggEAU3oltyrWedjMaZZ2C+neNMVx4PumM43oJLajfvRMXLSFe67sjVbK18DBt2nfd26yEw9P9spwSpd2bzTCGKDsH74ZJQXO6O1o0IfGspfzHr8snBMr5aydnexXJ4Pm6aDicz+LTJek5jCs/AMJpOwZTBcQqmSbi57LOPoORRjWbTz1j7My7m0nHK5NbX8befJ8yAw8vjgPRFsyQ4wYaEykfTwOWFwoQ68ow68f7uo8lxRZ/tPefTrpy1OjRx5GHJ2AXjbM3PMBjjPzWpGJWTKXU3IlN6NSNWmo1kmJK9GzfbyJ8c6mHijWQ/WM/HZSN8NF8DHerMUMpq0ujUrGkxefYQKBgQDgk0synIFKo6oW5ASexpQgsIkZzsuewI4s48LXiXEarL4NCAHTwhK7vpUGGaynP3qQm2k22oZsXsOesm/Iw7TGmNq2JeEWBPujqY//8a7OQUuOVkGRK9ZYU2BWWItjx4HHri2lCJabDUXdemcpgS+xDmoHISXeCj7dcnZZMFT90QKBgQCneARApNIouWHb9kldjZXc+MBROx30jKiXoX7AsTUkhzHpmE5BzrHxI+df8VSU/t3uGkKVK+f7jZfjy+XRtMJq6hbIirCgENnlLNk5aIYCeNq8OB7glp1p4T0BdxsUu8XGu5DXP4jPBj4i2CwrXNo+OTxaSfuN+tXgHitCEVIJgwKBgQDYxdTkOaWjgm+AQQEksdHx6/Wq764kwwF52zAMFSMT+IJK2dvJp5+lkcUPamD535BTXXAl/rGaka6J1PuJ8z7gJFOZVt75/j1YMShKieOOPADOl/waQRJZl8F6F2YVHlLrh/mPP8cAS8OQkcJakdSTN7KoxxpiRcmAwCf0125ywQKBgEKtIRxPwzDdpl26cDlkPs+s5n0xCVstiY5diSbVJzB092Vm83l1/xjgT6W+Ywuzcc7z+6CCy6k3Fctnigf1bRa+PvX3ah1AuFBri800lW50ibo4qeqHbQMT34Mu1cRqgnL+iMt6i1DJzoF3Chb1sBroFORp4lMEFJVXzadPWBdbAoGBAKw2V3jlp/2qxCWigWVN8+1izEnJJ61NFKaIBFxGEudcy5TSUP1rb0fVqQxEFu+5ry3om7zsYoKCe0dcesXUJdwsAHdaVjVPKQaOv351pA+IbqTMrkMVCUlNkKHQlqVa90LaCZ7CjLHKC62nTFVPYBUNNwjKYLza9MMPGLmpTbak-----END PRIVATE KEY-----";

        String privateKeyPEM = privatekeystr
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll(System.lineSeparator(), "")
            .replace("-----END PRIVATE KEY-----", "");

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encoded = decoder.decode(privateKeyPEM);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        RSAPrivateKey senderPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

        String recpublicKeyString = "-----BEGIN CERTIFICATE-----MIIDlDCCAnwCCQCDH6hG01kTuDANBgkqhkiG9w0BAQsFADCBizELMAkGA1UEBhMCQVUxDzANBgNVBAgMBlN5ZG5leTEPMA0GA1UEBwwGU3lkbmV5MQ0wCwYDVQQKDARSQUNRMQswCQYDVQQLDAJPQjEcMBoGA1UEAwwTcHJlcHJvZC1yYWNxLWN1c2NhbDEgMB4GCSqGSIb3DQEJARYRaG9tQGN1c2NhbC5jb20uYXUwHhcNMjIwMTIxMDczNTQ2WhcNMjUwMTIwMDczNTQ2WjCBizELMAkGA1UEBhMCQVUxDzANBgNVBAgMBlN5ZG5leTEPMA0GA1UEBwwGU3lkbmV5MQ0wCwYDVQQKDARSQUNRMQswCQYDVQQLDAJPQjEcMBoGA1UEAwwTcHJlcHJvZC1yYWNxLWN1c2NhbDEgMB4GCSqGSIb3DQEJARYRaG9tQGN1c2NhbC5jb20uYXUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC2S5ICGKh99jkY35gVJx2jd9KzXmxoXnlfF+hsH+7oe+HnEY0WuaIVlhZUsVGT6o1lxUesdQ7eJ2K1ySP/9hjmSg8vvzZ8IK+aYIbEb7tZwChsHqdsRmMfe06Iz8o0tRDYbj0Zx9qzuiV4xtoIxuPTD9xs1Jm0Rj1U6zyn28F8n9Sp3ili2SQf9dM4+XbWaiMaN0lWv36NH3sjluNkDovKweT7Oc5U8pooqW8iaIxEMym+pQvmap0WQe6AetbOm/BBxeoBVtmjBYIyPpTcrLA4DYHABU2Wvf0UhJsqLfbIO9mBbZlAAhksPki/Q3jMWIYcnXkahNty5EnsaBF6ZLy3AgMBAAEwDQYJKoZIhvcNAQELBQADggEBAFLX1B07Ao6g2TMLN3Frv/NZsBCcl5mVEXJOaen7Kh7KJCmsXBRW/USowvPexeIbOjFXXyJdORUcNB1XKJ8jr6WXUbyk8TDSUF9a70JvkO0d8oy9K8y/eGTXrIajMJG19Ahq+lwYzP53+YqTaREarBed1RzjHkaGJCG4wPDcOpSUXnV2sl6AAvBOy6corVJoGCU3qK1QaiHm1b3+s9jiMjvvSpJ5fudaHBD3fyPgGoI8J6+oZuzuR+0u0o+y8bi+faK5K1GmS371NarmhakxUprbHw8zkbRJ31JUDyQK11tEBPIP392UPRutPtCtfcz6QOtj+JmRGV+BihRCtRAkcuo=-----END CERTIFICATE-----";

        X509Certificate reccert = X509CertUtils.parse(recpublicKeyString);

        // Retrieve public key as RSA JWK
        RSAKey recrsaJWK = RSAKey.parse(reccert);

        RSAPublicKey recPublicKey = recrsaJWK.toRSAPublicKey();

        String recprivatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS6V7URmgEHqX7HIGPRWmsK3cTjgPUm9tc9KbQa4pJqNwmnC1De4sKcIu/C/l973FaJ6/NeZ/cMiLRL5ZjtwZ8u5IAck8W5rT22qU9BAiK/a3KFqwFFzXaDWmnRWMs5WOpJblmUeK9rbvg2EiYNrV5hfSAiN8Cwk6aAvp1zyyG2iqlCx80bhKMbb1Fe2J/hzidKQflytfBBnGwEPiM9VKtcrh6AvFXtLH2whN3c5Gey3G30mZIczKZbSIeNjyQX1v+eP34OKmMMHCmUrPat9HVjfvsimfE4oXB78IBEf2CmWGmC2WiwYg0sNYiPjA/VI4zg14ky6bLiIeMXUzmXDrzAgMBAAECggEAU3oltyrWedjMaZZ2C+neNMVx4PumM43oJLajfvRMXLSFe67sjVbK18DBt2nfd26yEw9P9spwSpd2bzTCGKDsH74ZJQXO6O1o0IfGspfzHr8snBMr5aydnexXJ4Pm6aDicz+LTJek5jCs/AMJpOwZTBcQqmSbi57LOPoORRjWbTz1j7My7m0nHK5NbX8befJ8yAw8vjgPRFsyQ4wYaEykfTwOWFwoQ68ow68f7uo8lxRZ/tPefTrpy1OjRx5GHJ2AXjbM3PMBjjPzWpGJWTKXU3IlN6NSNWmo1kmJK9GzfbyJ8c6mHijWQ/WM/HZSN8NF8DHerMUMpq0ujUrGkxefYQKBgQDgk0synIFKo6oW5ASexpQgsIkZzsuewI4s48LXiXEarL4NCAHTwhK7vpUGGaynP3qQm2k22oZsXsOesm/Iw7TGmNq2JeEWBPujqY//8a7OQUuOVkGRK9ZYU2BWWItjx4HHri2lCJabDUXdemcpgS+xDmoHISXeCj7dcnZZMFT90QKBgQCneARApNIouWHb9kldjZXc+MBROx30jKiXoX7AsTUkhzHpmE5BzrHxI+df8VSU/t3uGkKVK+f7jZfjy+XRtMJq6hbIirCgENnlLNk5aIYCeNq8OB7glp1p4T0BdxsUu8XGu5DXP4jPBj4i2CwrXNo+OTxaSfuN+tXgHitCEVIJgwKBgQDYxdTkOaWjgm+AQQEksdHx6/Wq764kwwF52zAMFSMT+IJK2dvJp5+lkcUPamD535BTXXAl/rGaka6J1PuJ8z7gJFOZVt75/j1YMShKieOOPADOl/waQRJZl8F6F2YVHlLrh/mPP8cAS8OQkcJakdSTN7KoxxpiRcmAwCf0125ywQKBgEKtIRxPwzDdpl26cDlkPs+s5n0xCVstiY5diSbVJzB092Vm83l1/xjgT6W+Ywuzcc7z+6CCy6k3Fctnigf1bRa+PvX3ah1AuFBri800lW50ibo4qeqHbQMT34Mu1cRqgnL+iMt6i1DJzoF3Chb1sBroFORp4lMEFJVXzadPWBdbAoGBAKw2V3jlp/2qxCWigWVN8+1izEnJJ61NFKaIBFxGEudcy5TSUP1rb0fVqQxEFu+5ry3om7zsYoKCe0dcesXUJdwsAHdaVjVPKQaOv351pA+IbqTMrkMVCUlNkKHQlqVa90LaCZ7CjLHKC62nTFVPYBUNNwjKYLza9MMPGLmpTbak-----END PRIVATE KEY-----";

        String recprivateKeyPEM = recprivatekeystr
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll(System.lineSeparator(), "")
            .replace("-----END PRIVATE KEY-----", "");

        Base64.Decoder decoder1 = Base64.getDecoder();
        byte[] encoded1 = decoder1.decode(recprivateKeyPEM);

        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(encoded1);
        RSAPrivateKey recPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec1);

        Provider bc = BouncyCastleProviderSingleton.getInstance();
        Security.addProvider(bc);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
            .claim("data_holder_brand_id", "acccunicornbank")
                .claim("r_code", "R4oj0na8g0hecgmn7xzbmkdfgvivmw4n")
                .claim("userId", "acccunicornbank")
            .build();

            String header1 = "{\n" +
                "  \"alg\": \"none\"\n" +
                "}";

        JSONObject header = new JSONObject();
        header.put("alg", "none");

        JSONObject payload1 = new JSONObject();
//        payload1.put("iss", "rac575");
//        payload1.put("iss", "rac575.uat");
        payload1.put("iss", "rac575.preprd");
//        payload1.put("aud", "consent-mgt-ui.cdrbank.racq.com.au");
//        payload1.put("aud", "consent-mgt-ui.nonprod-cdrbank.racq.com.au");
        //payload1.put("sub", "10119283");
        payload1.put("aud", "consent-mgt-ui.preprd-cdrbank.racq.com.au");
//        payload1.put("sub", "327");
        long unixTime = System.currentTimeMillis() / 1000L;
        unixTime=unixTime+86400;
        System.out.println("Unix time calculated "+unixTime);
        payload1.put("exp", unixTime);

        String jwtToken = Base64.getUrlEncoder().withoutPadding().encodeToString(header.toString().getBytes(StandardCharsets.UTF_8))+ "." + Base64.getUrlEncoder().withoutPadding().encodeToString(payload1.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println("jwtToken" + jwtToken);
        Payload payload = new Payload(jwtToken);

        JWEObject jweObject = new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256CBC_HS512)
                .contentType("JWT") // required to indicate nested JWT
                .build(),
            payload);

        // Encrypt with the recipient's public key
        jweObject.encrypt(new RSAEncrypter(recPublicKey));

        // Serialise to JWE compact form
        String jweString = jweObject.serialize();

        System.out.println("encoded token::" + jweString);

    }

    private static String decodeToken(String token, SignedJWT signedJWT) {
        String tokenStr = null;
        if (signedJWT == null) {
            String[] chunks = token.split("\\.");

            Base64.Decoder decoder = Base64.getDecoder();

            tokenStr = Optional.ofNullable(chunks)
                .filter(strings -> strings.length >= 2)
                .map(strings -> new String(decoder.decode(strings[1])))
                .orElse(null);
        } else {
            tokenStr = signedJWT.getPayload().toString();
        }
        return tokenStr;
    }
}
