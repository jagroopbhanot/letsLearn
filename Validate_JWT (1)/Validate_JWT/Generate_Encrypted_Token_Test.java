import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import javax.crypto.Cipher;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
import java.util.Date;
import java.util.UUID;

public class Generate_Encrypted_Token_Test {

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

        String publicKeyString = "-----BEGIN CERTIFICATE-----MIIDETCCAfmgAwIBAgIJAJ798i52XUQvMA0GCSqGSIb3DQEBCwUAMEgxEzARBgNVBAMMClVOSUNPUk5fZHIxEzARBgNVBAoMClBpcmVhbiBMdGQxDzANBgNVBAcMBkxvbmRvbjELMAkGA1UEBhMCR0IwHhcNMjEwNDE2MjAyNjIyWhcNMzEwNDE2MjAyNjIyWjBIMRMwEQYDVQQDDApVTklDT1JOX2RyMRMwEQYDVQQKDApQaXJlYW4gTHRkMQ8wDQYDVQQHDAZMb25kb24xCzAJBgNVBAYTAkdCMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkule1EZoBB6l+xyBj0VprCt3E44D1JvbXPSm0GuKSajcJpwtQ3uLCnCLvwv5fe9xWievzXmf3DIi0S+WY7cGfLuSAHJPFua09tqlPQQIiv2tyhasBRc12g1pp0VjLOVjqSW5ZlHiva274NhImDa1eYX0gIjfAsJOmgL6dc8shtoqpQsfNG4SjG29RXtif4c4nSkH5crXwQZxsBD4jPVSrXK4egLxV7Sx9sITd3ORnstxt9JmSHMymW0iHjY8kF9b/nj9+DipjDBwplKz2rfR1Y377IpnxOKFwe/CARH9gplhpgtlosGINLDWIj4wP1SOM4NeJMumy4iHjF1M5lw68wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQB8ch2XrWnBZCROo9eAwiCrLSBR5QSKeBlpQTFADWeMDBYNCkaA8yJojPnoT7S+KUc6soeIBCq0/qrYGPLk7U+YYr0G+iWVZZvkeQ5yWZ0ZYwfWuVyI1Rh5+C4ybubUu9TnP3aoJ7OJpTdPMvjrqUjaS2/1R3vbmQKT6vvtV8BMhyNaQc0XsYXj20QuIwhItrfllZagzbigrosEE5wGqcjgcZzT3VIgeHZ/jBS3lS3/H0MZ4dgFAChLqkoiATzdIkWFhuoL57B63BGjuawqUPEWQvtuvarQxJdEpme/58j7OY22F6FlKdauDAB+4mGmSVvqDclFjbth/IWcOlgOC/v9-----END CERTIFICATE-----";

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

        String recpublicKeyString = "-----BEGIN CERTIFICATE-----MIIDIjCCAgqgAwIBAgIIZLnBuCi1jgkwDQYJKoZIhvcNAQELBQAwUTEcMBoGA1UEAwwTVU5JQ09STl9kdW1teV9hY2NjXzETMBEGA1UECgwKUGlyZWFuIEx0ZDEPMA0GA1UEBwwGTG9uZG9uMQswCQYDVQQGEwJHQjAeFw0yMTA0MTYxMzM4MDhaFw0zMTA0MTYxMzM4MDhaMFExHDAaBgNVBAMME1VOSUNPUk5fZHVtbXlfYWNjY18xEzARBgNVBAoMClBpcmVhbiBMdGQxDzANBgNVBAcMBkxvbmRvbjELMAkGA1UEBhMCR0IwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC8rzUMzARt3KrFDuSBaGP2w3sD3kNOSFyaQOcC/lb7VIpA4F2gbnWax0kWPM35OjrqLcfzzlbD4N5AVeva24BYyI01YUXdSxGYRO5ZLPquFLWP1ej5vAlwmHsVPL44r8SLA4j3F+BdyQeVaOKfy8zBYV5VC38mzA3qy+ZMmHjJc7dO0LjqjBtaVVnOPohB1pzKEp7B3oDZX90erG64Sq8/6bumpoQ6lDFdDDKtOeRwuYCXdMKg+dw3M5yBomud0LJPmgrygg/X1V7NO/AYlj6d9n6uT1cQu0fzXM7gR7ezrWYZlio/mRUh1k/6FsoFEFvu0LTFZi9eoCOVabTI4aS/AgMBAAEwDQYJKoZIhvcNAQELBQADggEBAG+Uia0hr+EPBIrlbxg7W+ZfADmd7XGFQBHrqCMm/yqsJVsHXb6OWLL7hUJBl0UabTila2qQd4GYPIhUT7kUiVKcAp18euQf6xkCdYnEqmGVd+zfEaZmejnpSZXV0Yq/OmA8BNbVq2Ny+13m1S7vP+HkUxlWdueWK1rh5H9K8iI5q5JfdwNvmUnriB66GQ48VxVPIIgbGDG3lRJ66FzR1n7/ihX0PbgHDEM/fTIqh3jAT1taHqoq0w5NEwkmwUtN65jmCV5nljxdd0jDuBAl3Fa1wSkRzCRQL5Po2MIRHB/PNV4JooRYc3lFBHOrVHrg0GSspYAinGYFYq2tzx2juks=-----END CERTIFICATE-----";
        
        X509Certificate reccert = X509CertUtils.parse(recpublicKeyString);

        // Retrieve public key as RSA JWK
        RSAKey recrsaJWK = RSAKey.parse(reccert);

        RSAPublicKey recPublicKey = recrsaJWK.toRSAPublicKey();

        String recprivatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8rzUMzARt3KrFDuSBaGP2w3sD3kNOSFyaQOcC/lb7VIpA4F2gbnWax0kWPM35OjrqLcfzzlbD4N5AVeva24BYyI01YUXdSxGYRO5ZLPquFLWP1ej5vAlwmHsVPL44r8SLA4j3F+BdyQeVaOKfy8zBYV5VC38mzA3qy+ZMmHjJc7dO0LjqjBtaVVnOPohB1pzKEp7B3oDZX90erG64Sq8/6bumpoQ6lDFdDDKtOeRwuYCXdMKg+dw3M5yBomud0LJPmgrygg/X1V7NO/AYlj6d9n6uT1cQu0fzXM7gR7ezrWYZlio/mRUh1k/6FsoFEFvu0LTFZi9eoCOVabTI4aS/AgMBAAECggEBALmI43Zo6vYIpZdN9J3XQ2yzfbQpCy/f8cbwiPglluxejfALb6VA2L3Qtmp6ceNfQe1iBRsVS7Yi2QiUNG6ppoQm+MuDkgpvNabwKRthIkKpNHtyUTu4jeO83FLevwHIvFAhsr2eKc6uzZk6YJXW9ck18GdZ/bbtscWuqA+rYiJbxUrgA1NZxaaufafK1UzTfKhJKh0y0OWmO8lfoKbaSp+8lNYmrRaPpPEw2zVl/UXmk0WIjnlsYg3C7kGeYFkgYsMc8ABDNNXdvlAZzTp2u7nG4DsdKLmjCxs07Rp7GH9g2V9fR+xhlZzU2g3pn731z2UNtdTnZLpHUmRBwxCInBECgYEA/VpdUIfiVv7oHK54fuDW7EbxCD046PWm/QNtN9QrJbq2g8xYgTFffi7Q6WBJ6DX/c2p8k02Pz+dlgr9Q4aU1lOftBmPE3cwYed/LBm9/tl3Xcd1aSgyGFMmyNZSDX7mMMpoQS3OHCprwIEDYq6vQCfFEms1Ejc0r7v8G0vQtEIkCgYEAvqfgKYPfdZiztq572o9yNfs1cOGXfplG5gr8X3v02crlldLIYkSQZjCu9MG4ZTcTnmn1wfR41PRPeh4tGGg8/69JDqiqr+0dkoS/0DMd43gSVIAGHQbEW6M5kGFLDSMf4h7bgBoas4HzmRlh6YQYsnaTvK88LEHrlZwL5R1uaQcCgYB4S8XvDPZNtG9l3vnb1Mp4Gh8seFVjV/DYRh4/2gA6w/MRlZ+YiGb9NSGBDnVJSwpmNPO9xaA5aXfrowe2fIl6kL0cwZaicdxVUgvGnH6ZiulXaXELo/KuQC7b/g57D1EgXcLK8Kn4QSUvS1sV+Fipv+OBVWHtqrPnoTQOSl5zcQKBgEq8T9i2QvA8DaZ8uzwvotuaP5aiN9p+hxZCFC/xzUdai6ixWa97pQcJ6mszTO8QKfadHqMLDBqeMlFyZsb8hXzM31yJrW8NCdyXgQFCKGM0fCr3jG3wayRo1XXNTbg2npbq9aw/j1vyguTu2DzLdUrvxHXgUzweExUweFE1ukQdAoGAJEtNQs3j+Hgm2RCDIEnmPksduFrlh5GGvC2lS+ddHbinCFzwOiAuPI8piJlZc5JS23wquhH7R+uf56dJdUG7dMsMfmjjylvDjf5Xatik2SsCFq10YAizGExv23Js7zFroEv06D2QF0gPbQMx0Jh2bPEjSFFJ4prJrY0brUmKeSs=-----END PRIVATE KEY-----";

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

        JWSSigner signer = new RSASSASigner(senderPrivateKey);

        signer.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());

        AdditionalClaims additionalClaims = new AdditionalClaims();

        additionalClaims.setDuration("100");
        additionalClaims.setScope("openid profile bank:accounts.basic:read bank:accounts.detail:read");
        additionalClaims.setCdr_arrangement_id("CDR");
        SignedJWT signedJWT = new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("jwt-rsa").build(),
            new JWTClaimsSet.Builder()
                .audience("consent.ui.test.cds.whitford.com.au")
               // .subject("cuscal test")
                .notBeforeTime(new Date(new Date().getTime() / 1000 * 1000))
                .issueTime(new Date(new Date().getTime() / 1000 * 1000))
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(new Date().getTime() + 3600 * 1000))
                .issuer("whitford")
                //.subject("Tlewis93^")
                .claim("data_holder_brand_id", "acccunicornbank")
                .claim("r_code", "R4oj0na8g0hecgmn7xzbmkdfgvivmw4n")
                .claim("userId", "acccunicornbank")
                .claim("typ", "RS256")
                .claim("loa", "1.0")
                .claim("sessionId", "String")
                .claim("sp_id", "af9f578f-3d96-ea11-a831-000d3a8842e1")
                .claim("ss_type", "String")

                .claim("params", additionalClaims)
                .build());

        signedJWT.sign(signer);
        // Create JWE object with signed JWT as payload
        JWEObject jweObject = new JWEObject(
            new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256CBC_HS512)
                .contentType("JWT") // required to indicate nested JWT
                .build(),
            new Payload(signedJWT));

        // Encrypt with the recipient's public key
        jweObject.encrypt(new RSAEncrypter(recPublicKey));

        // Serialise to JWE compact form
        String jweString = jweObject.serialize();
        System.out.println("encoded token::" + jweString);
    }
}
