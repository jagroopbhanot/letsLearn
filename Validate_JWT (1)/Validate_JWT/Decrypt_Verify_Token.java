//package au.com.cuscal.domain.web.rest.validator;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class Decrypt_Verify_Token {


    public static void main(String s[]) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, IOException, BadJWTException {

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

        Provider bc = BouncyCastleProviderSingleton.getInstance();
        Security.addProvider(bc);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        String recprivatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8rzUMzARt3KrFDuSBaGP2w3sD3kNOSFyaQOcC/lb7VIpA4F2gbnWax0kWPM35OjrqLcfzzlbD4N5AVeva24BYyI01YUXdSxGYRO5ZLPquFLWP1ej5vAlwmHsVPL44r8SLA4j3F+BdyQeVaOKfy8zBYV5VC38mzA3qy+ZMmHjJc7dO0LjqjBtaVVnOPohB1pzKEp7B3oDZX90erG64Sq8/6bumpoQ6lDFdDDKtOeRwuYCXdMKg+dw3M5yBomud0LJPmgrygg/X1V7NO/AYlj6d9n6uT1cQu0fzXM7gR7ezrWYZlio/mRUh1k/6FsoFEFvu0LTFZi9eoCOVabTI4aS/AgMBAAECggEBALmI43Zo6vYIpZdN9J3XQ2yzfbQpCy/f8cbwiPglluxejfALb6VA2L3Qtmp6ceNfQe1iBRsVS7Yi2QiUNG6ppoQm+MuDkgpvNabwKRthIkKpNHtyUTu4jeO83FLevwHIvFAhsr2eKc6uzZk6YJXW9ck18GdZ/bbtscWuqA+rYiJbxUrgA1NZxaaufafK1UzTfKhJKh0y0OWmO8lfoKbaSp+8lNYmrRaPpPEw2zVl/UXmk0WIjnlsYg3C7kGeYFkgYsMc8ABDNNXdvlAZzTp2u7nG4DsdKLmjCxs07Rp7GH9g2V9fR+xhlZzU2g3pn731z2UNtdTnZLpHUmRBwxCInBECgYEA/VpdUIfiVv7oHK54fuDW7EbxCD046PWm/QNtN9QrJbq2g8xYgTFffi7Q6WBJ6DX/c2p8k02Pz+dlgr9Q4aU1lOftBmPE3cwYed/LBm9/tl3Xcd1aSgyGFMmyNZSDX7mMMpoQS3OHCprwIEDYq6vQCfFEms1Ejc0r7v8G0vQtEIkCgYEAvqfgKYPfdZiztq572o9yNfs1cOGXfplG5gr8X3v02crlldLIYkSQZjCu9MG4ZTcTnmn1wfR41PRPeh4tGGg8/69JDqiqr+0dkoS/0DMd43gSVIAGHQbEW6M5kGFLDSMf4h7bgBoas4HzmRlh6YQYsnaTvK88LEHrlZwL5R1uaQcCgYB4S8XvDPZNtG9l3vnb1Mp4Gh8seFVjV/DYRh4/2gA6w/MRlZ+YiGb9NSGBDnVJSwpmNPO9xaA5aXfrowe2fIl6kL0cwZaicdxVUgvGnH6ZiulXaXELo/KuQC7b/g57D1EgXcLK8Kn4QSUvS1sV+Fipv+OBVWHtqrPnoTQOSl5zcQKBgEq8T9i2QvA8DaZ8uzwvotuaP5aiN9p+hxZCFC/xzUdai6ixWa97pQcJ6mszTO8QKfadHqMLDBqeMlFyZsb8hXzM31yJrW8NCdyXgQFCKGM0fCr3jG3wayRo1XXNTbg2npbq9aw/j1vyguTu2DzLdUrvxHXgUzweExUweFE1ukQdAoGAJEtNQs3j+Hgm2RCDIEnmPksduFrlh5GGvC2lS+ddHbinCFzwOiAuPI8piJlZc5JS23wquhH7R+uf56dJdUG7dMsMfmjjylvDjf5Xatik2SsCFq10YAizGExv23Js7zFroEv06D2QF0gPbQMx0Jh2bPEjSFFJ4prJrY0brUmKeSs=-----END PRIVATE KEY-----";
        byte[] recprivKeyBytes =recprivatekeystr.getBytes(StandardCharsets.UTF_8);

        String recprivateKeyPEM = recprivatekeystr
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll(System.lineSeparator(), "")
            .replace("-----END PRIVATE KEY-----", "");

        Base64.Decoder decoder1 = Base64.getDecoder();
        byte[] encoded1 = decoder1.decode(recprivateKeyPEM);

        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(encoded1);
        RSAPrivateKey recPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec1);


        String publicKeyString = "-----BEGIN CERTIFICATE-----MIIDETCCAfmgAwIBAgIJAJ798i52XUQvMA0GCSqGSIb3DQEBCwUAMEgxEzARBgNVBAMMClVOSUNPUk5fZHIxEzARBgNVBAoMClBpcmVhbiBMdGQxDzANBgNVBAcMBkxvbmRvbjELMAkGA1UEBhMCR0IwHhcNMjEwNDE2MjAyNjIyWhcNMzEwNDE2MjAyNjIyWjBIMRMwEQYDVQQDDApVTklDT1JOX2RyMRMwEQYDVQQKDApQaXJlYW4gTHRkMQ8wDQYDVQQHDAZMb25kb24xCzAJBgNVBAYTAkdCMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkule1EZoBB6l+xyBj0VprCt3E44D1JvbXPSm0GuKSajcJpwtQ3uLCnCLvwv5fe9xWievzXmf3DIi0S+WY7cGfLuSAHJPFua09tqlPQQIiv2tyhasBRc12g1pp0VjLOVjqSW5ZlHiva274NhImDa1eYX0gIjfAsJOmgL6dc8shtoqpQsfNG4SjG29RXtif4c4nSkH5crXwQZxsBD4jPVSrXK4egLxV7Sx9sITd3ORnstxt9JmSHMymW0iHjY8kF9b/nj9+DipjDBwplKz2rfR1Y377IpnxOKFwe/CARH9gplhpgtlosGINLDWIj4wP1SOM4NeJMumy4iHjF1M5lw68wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQB8ch2XrWnBZCROo9eAwiCrLSBR5QSKeBlpQTFADWeMDBYNCkaA8yJojPnoT7S+KUc6soeIBCq0/qrYGPLk7U+YYr0G+iWVZZvkeQ5yWZ0ZYwfWuVyI1Rh5+C4ybubUu9TnP3aoJ7OJpTdPMvjrqUjaS2/1R3vbmQKT6vvtV8BMhyNaQc0XsYXj20QuIwhItrfllZagzbigrosEE5wGqcjgcZzT3VIgeHZ/jBS3lS3/H0MZ4dgFAChLqkoiATzdIkWFhuoL57B63BGjuawqUPEWQvtuvarQxJdEpme/58j7OY22F6FlKdauDAB+4mGmSVvqDclFjbth/IWcOlgOC/v9-----END CERTIFICATE-----";

        X509Certificate cert = X509CertUtils.parse(publicKeyString);

        // Retrieve public key as RSA JWK
        RSAKey rsaJWK = RSAKey.parse(cert);

        RSAPublicKey senderPublicKey = rsaJWK.toRSAPublicKey();

        String jweString = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.dewyzNOntELTUrUEa122a-9v-CGEXCtTuE5hfIkGvxNGI7OCOUd-6-Sb5e5dcsiTvmSAqKpX-cAlEhxQizzKVt1kdJ9S5ZpuKGWowfKjYnp4eCe-Ysg6dvMUgnNYqH76IzND95pBmgnltBbfenZ684SbyqmtLHm6o023GSgGJE9iOQWUgfkJEIAYfNJzeD9RpiojQl8730USnqb0I2_xuJsH8hhX6dl1AtAGVi8y_zRlinS3z3-pVJkPv6WLTbltp93oQtDYcWxzkAB6B5qJp6FgMDvxUg6cKuJlW_geYKeJsuFj92HpMfNihVyTY7ZTUBi9aXsj8xRcpdhmh0UIcA.fndQSPK0Moll1gI2SHvj3g.opC5jgZZIU371xyXkEaTX2T3-S8bQ_BdFO6hVynPGSpBFmxzsTnAD3iwwKNM9S7MJqybaGIdCSWI00GCclXrDG5jq17G4jfcKE-VjKRGF44DnMH1-A4jaoIRws9fOg9V5MzSwHPd1bSZM0_sUaZTMxgskTtYjkDIcFA6ZGa9rABvT6hUOLft0lEzTk07PYKIyYmMfEVdx0eb1bZ1HNUr52QiTCq2D58bwCvUAOVbanPoPIhXpbpyKuBN6m3mGvWZBvxm4wF2M7OK-1tzZR9eS2ZGT60gvFrTTTZFEjyRn_lfIUI57jURrZe2vAuX8xwe0AmuRr_fSlKM3izpbqk9OcwCxQayFwPa47I5IaJSsSe6yL4Mn5Sda1ASUSmyCL6WKRkFsswjVwETyHpKm8NVs5DrjdTIA9B7KJbavKWZmSJuaJptMVFW1c7m1lDIChrKXr9IWdLDXhSgzyKqphRp1dHGqcsfsYaaD0bEy5q_RcLELtgtrbQrgcqxM39RrThykomQuI05YSK03O0ZI26kcxTjPUDCagDZ2teHErkWE_kgMjMokvnUWgrWCVjCKljnLNcq3_v1eDzmllwN8SVbgHZVhWnfBm9ljxzkbpNZqvK49kjXxqLGYxrpgxTn3Ej_yIA6OOfCnlddRCIbLd53eIykmTfG59faFt0P0Fc4fXQQWB41CRk9f6m-WM7udENWYjAKspXoYE5xQBZjJR_MBV5zV8kC-u2Eb9DuDwlsDLv3M8nQ4kLPMAL6JLp882znAYXRN4EN7h0WUaHdjfD8K4HT6CkChvk0OdVMIDivi4G6fnt_1e34tk1m5UksywIHLwo-ulyQ02xVk1vbicrTOGloVKcrn3outJO0dvJpMws7OjCEupbOtxqXK1Qry8PWZjQeEgYfKha1cEezrxj_cow2Gmf9fc6x3S-U7LjM7-paOMWG1hjfgr8Sa3RSTBCIKxC7nD28Y-IjWAPeoJ4vYE04vczeOVbTmPt9lsCuHgmYpl2BgDcUOMndp8acVsYKvg7JO9qDvtx3MX_q8n3XzITDEHWgvLkVnoMffj9sTnK19RaIgiHsrkwBZYNyTDS2sgKhFozii40yr65XYFgDhoSSsXgUSMDLltvj4DlICuxvCDF59A5kz20GDYA6Hz2DOpxeDCYb0OpCHfqOeG3deScAjEapv9fvi3UPQCxkwPb9XsaKCvOYQdPX9p9qsq20AEQWDPcZ4JP3Vcbjxhl7ZzNHS9GS7E1BVrGAcDSN49qrQNN1tDpxaxoCsfeVF5_uVsmi4ozqIMUrD32yzZp_gpYTls6PUptbVqx8tZ95cDxRdP4HNXwVCCq5KC4qJ8ck9eaSPd8F5HTvEo7I-TZeI_NCMal5578ULYOOL1U1SUh-r7J2Ad8ckl-iySarAVJYieXh18-mHDSkVX9KFH2hILb6tpSfB9q9Vji2C_353h8.GTeh8zQYh-virToyg6VFrW8pfsqro8jOk1uvRc2C9so";


        // Parse the JWE string
        JWEObject jweObject = JWEObject.parse(jweString);



        // Decrypt with private key
        jweObject.decrypt(new RSADecrypter(recPrivateKey));

        // Extract payload
        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

        EncryptedJWT JWT1 = EncryptedJWT.parse(jweString);
        RSADecrypter decrypter = new RSADecrypter(recPrivateKey);
        // Decrypt
        JWT1.decrypt(decrypter);

        System.out.println("JWT1.getHeader().getEncryptionMethod().getName()::" + JWT1.getHeader().getEncryptionMethod().getName());

        System.out.println("signedJWT::" + signedJWT.getPayload());
        System.out.println(signedJWT.getJWTClaimsSet().getIssuer());
        System.out.println(signedJWT.getJWTClaimsSet().getSubject());
        System.out.println(signedJWT.getJWTClaimsSet().getAudience().size());
        System.out.println(signedJWT.getJWTClaimsSet().getExpirationTime());
        System.out.println(signedJWT.getJWTClaimsSet().getNotBeforeTime());
        System.out.println(signedJWT.getJWTClaimsSet().getIssueTime());
        System.out.println(signedJWT.getJWTClaimsSet().getJWTID());

        System.out.println(signedJWT.getJWTClaimsSet().getClaim("params"));
        System.out.println("signedJWT.getHeader().getAlgorithm().getName()::" + signedJWT.getHeader().getAlgorithm().getName());
        System.out.println("signedJWT.verify(new RSASSAVerifier(publicKey3))::" + signedJWT.verify(new RSASSAVerifier(senderPublicKey)));


    }


}
