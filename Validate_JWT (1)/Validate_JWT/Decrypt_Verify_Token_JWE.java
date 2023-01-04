
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.util.DateUtils;
import jdk.nashorn.internal.parser.JSONParser;
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
import java.util.*;

public class Decrypt_Verify_Token_JWE {


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
        //dummy
//        String recprivatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC8rzUMzARt3KrFDuSBaGP2w3sD3kNOSFyaQOcC/lb7VIpA4F2gbnWax0kWPM35OjrqLcfzzlbD4N5AVeva24BYyI01YUXdSxGYRO5ZLPquFLWP1ej5vAlwmHsVPL44r8SLA4j3F+BdyQeVaOKfy8zBYV5VC38mzA3qy+ZMmHjJc7dO0LjqjBtaVVnOPohB1pzKEp7B3oDZX90erG64Sq8/6bumpoQ6lDFdDDKtOeRwuYCXdMKg+dw3M5yBomud0LJPmgrygg/X1V7NO/AYlj6d9n6uT1cQu0fzXM7gR7ezrWYZlio/mRUh1k/6FsoFEFvu0LTFZi9eoCOVabTI4aS/AgMBAAECggEBALmI43Zo6vYIpZdN9J3XQ2yzfbQpCy/f8cbwiPglluxejfALb6VA2L3Qtmp6ceNfQe1iBRsVS7Yi2QiUNG6ppoQm+MuDkgpvNabwKRthIkKpNHtyUTu4jeO83FLevwHIvFAhsr2eKc6uzZk6YJXW9ck18GdZ/bbtscWuqA+rYiJbxUrgA1NZxaaufafK1UzTfKhJKh0y0OWmO8lfoKbaSp+8lNYmrRaPpPEw2zVl/UXmk0WIjnlsYg3C7kGeYFkgYsMc8ABDNNXdvlAZzTp2u7nG4DsdKLmjCxs07Rp7GH9g2V9fR+xhlZzU2g3pn731z2UNtdTnZLpHUmRBwxCInBECgYEA/VpdUIfiVv7oHK54fuDW7EbxCD046PWm/QNtN9QrJbq2g8xYgTFffi7Q6WBJ6DX/c2p8k02Pz+dlgr9Q4aU1lOftBmPE3cwYed/LBm9/tl3Xcd1aSgyGFMmyNZSDX7mMMpoQS3OHCprwIEDYq6vQCfFEms1Ejc0r7v8G0vQtEIkCgYEAvqfgKYPfdZiztq572o9yNfs1cOGXfplG5gr8X3v02crlldLIYkSQZjCu9MG4ZTcTnmn1wfR41PRPeh4tGGg8/69JDqiqr+0dkoS/0DMd43gSVIAGHQbEW6M5kGFLDSMf4h7bgBoas4HzmRlh6YQYsnaTvK88LEHrlZwL5R1uaQcCgYB4S8XvDPZNtG9l3vnb1Mp4Gh8seFVjV/DYRh4/2gA6w/MRlZ+YiGb9NSGBDnVJSwpmNPO9xaA5aXfrowe2fIl6kL0cwZaicdxVUgvGnH6ZiulXaXELo/KuQC7b/g57D1EgXcLK8Kn4QSUvS1sV+Fipv+OBVWHtqrPnoTQOSl5zcQKBgEq8T9i2QvA8DaZ8uzwvotuaP5aiN9p+hxZCFC/xzUdai6ixWa97pQcJ6mszTO8QKfadHqMLDBqeMlFyZsb8hXzM31yJrW8NCdyXgQFCKGM0fCr3jG3wayRo1XXNTbg2npbq9aw/j1vyguTu2DzLdUrvxHXgUzweExUweFE1ukQdAoGAJEtNQs3j+Hgm2RCDIEnmPksduFrlh5GGvC2lS+ddHbinCFzwOiAuPI8piJlZc5JS23wquhH7R+uf56dJdUG7dMsMfmjjylvDjf5Xatik2SsCFq10YAizGExv23Js7zFroEv06D2QF0gPbQMx0Jh2bPEjSFFJ4prJrY0brUmKeSs=-----END PRIVATE KEY-----";
//        byte[] recprivKeyBytes =recprivatekeystr.getBytes(StandardCharsets.UTF_8);

        String recprivatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC2S5ICGKh99jkY35gVJx2jd9KzXmxoXnlfF+hsH+7oe+HnEY0WuaIVlhZUsVGT6o1lxUesdQ7eJ2K1ySP/9hjmSg8vvzZ8IK+aYIbEb7tZwChsHqdsRmMfe06Iz8o0tRDYbj0Zx9qzuiV4xtoIxuPTD9xs1Jm0Rj1U6zyn28F8n9Sp3ili2SQf9dM4+XbWaiMaN0lWv36NH3sjluNkDovKweT7Oc5U8pooqW8iaIxEMym+pQvmap0WQe6AetbOm/BBxeoBVtmjBYIyPpTcrLA4DYHABU2Wvf0UhJsqLfbIO9mBbZlAAhksPki/Q3jMWIYcnXkahNty5EnsaBF6ZLy3AgMBAAECggEAYuxnc1PGO3SeT20mrnPnlYm0gTnAQnLiEkR7EBHUJ7zyPd48XhSmbGhh/durlUU3Lf78gg2T3+InotZf/sqhgHa3/wUYoAtsv62Z9Iddd7rbNLD90bfwmmjjmUKJTEjlt007Qv49JELjisL+Fr03bJWy3CwSrM1hAvAUnlKWkPWQfWWHxk2x0hm0QjLZU1NapG1yy2O5894nI0LngxbS4FgMyfgfRpOoxtxEQNN0RSTdJhI6339iM272aSpWKqofbK7ZDgIRggs9HH5Xt7+C7tUCEoqlPsLucv/8cHEq0uixaEZREupYOvchoSJlq6JlnuUITndqi8Pawe1egPEK4QKBgQDdhrg7QZ7DKnHlWj2Al8bLAafHiU9Cy1vSqRiifZ5cnwotH13nZpm1qNSZd6dXdNa3UnIviwNsw0P3Cq659egSpFQr2+czAvR1qH1XRo3dfCy+GFURbBavHXUZ5FI41x2m40TXutjI4n1LquR03Egiyl1x1ZaM2sRcu6J0SWimxQKBgQDSqfF162cuFT+zgcvb8NzjMoCnxk7wWbvcwbZ3cuc61obC/Lv2JKinf047pP+JHLD++EDKNHVfHyCQEB+PYORlWsWTcJe0pY+LHWGd8SuAbRmujYn/zldlEnRBm63hNoNRKQ806g6KyU/p32Q8TNXI7E2f4Gp8EIU7Q8ZF7nJtSwKBgG3V1BadTIhbzSj4ezH9DvWslnGapQ/rIFXsvWgjfJdTy4nf2YFYH7IwSWjmKLR2G9e9Kxxjgok0r8gXJdbSFATaMVmlqJZkSqzjo3HYa5J7dPuzuMnwm7arlloGGLZSAkOyv3lvDPldQt53ltpSNfn5+Cbuzbx844tPBRc/JoI5AoGAb0/2000YjwQRTcOGuWeS/8Xer0gcy+lOV75PsNwZDEDwojUzCEOkjiS5bNCpaYSsjX0VlwKTBjkHVrf+RR94b4nAHkD/GCfnKiXTP8aAKvL6//2v7mfUTAatQUGGZhsM1iSw8U+/cVwgCr7MUwRybiFWfuTwvy9nhsIwESGGevMCgYAgWRjqEkKX47iU0YCH7s/um8fqNY0NJXCTMXvqGzR6Y/zZ1B7dbksuFT9vauM6N3I5kVRucWKFjeT2SWqqX/YhMW9stPJojrpvXODREPdJLiwbzVoyeQzOfbU2/gPlCQfoqiKSDswgi9vTxA7EVdDpQ67zBxbFOW+RUA1afsPdxw==-----END PRIVATE KEY-----";
//ra
//        String bb = "-----BEGIN RSA PRIVATE KEY-----\n" +
//            "MIIEogIBAAKCAQEAs0XS+BahVUSY99W6YSZvaA1Cq2ix5mcYzgbSz1CpmdYsQZfm\n" +
//            "fEuuyhGBXLkmHPeJ4ijKtsTA6VoK6eBPe4PK5P6uzDk7hFMzK2tskXVeI9IIkrVW\n" +
//            "NjIZoWlRoVRPt8YTcRbqhKD0ElEvhF5hzok9Upc3p81EQa8a41Vw/X0wOwfyeIf9\n" +
//            "6y2dig7me8YN/uEgwpU7PaGudtTZdBZf0HX+2arYBZgGO2OpLsnEggqmyliiIwv4\n" +
//            "iSNWce40IEzO4IM3+gMvK/V/iWZFnXj3EHi+Nx3gAeJwVSrkkgiaqlxck2CaWidE\n" +
//            "AH30pjwdiBZOnYuTBFe7FEYJ3ZoC3Xu3sKOpdQIDAQABAoIBAB4YaLRRSEBCRdPF\n" +
//            "Q4avpyA2qayQ5NyjDhz4NEetaOt0cvWIBJ91LSCrE6Z3RpiyTalz46QPH7uM7Cwb\n" +
//            "BfzcQSF0R+/s47rxJLPzUsSQiX9F8T5/xrtOadMZFSUaPE/DptvjVvdw9chlbK6y\n" +
//            "EYVUsMC+tg8rfHvyY3cqjs9fRsuB4Af+YQLwRluUFKceyOX5guuuZlHmiZa+/lm9\n" +
//            "2ijDnzONMUewK2xs18I4h79eVh7Dem0O2KYIjhyu5bI/ktxSiFCbyOIDhx7K7Yf+\n" +
//            "JCpc6/0kv7Njo/ij0wmhtqc3iKbf5+xjlsacOdTAbKiO9BejZscclNVe4MMJK34/\n" +
//            "kAfEPvkCgYEA4DVBBR4DTiy8jo8Qz+F6gLkEKEho33fT8ZkKMcTtfjba9pQOFW5W\n" +
//            "I6gdYe3q76l4TBElC4/XJm99cnC/x1OSnBx75364jhuYY8zPKhLtF3WCZ3xlrrle\n" +
//            "I/o6YpBI2QwD5T0td3in+Gbb3hGnE/uBSL7deyd+U1FqRmFpqCpUds8CgYEAzLFr\n" +
//            "tnSfOjGREeex8CXQhcND3oDz2kZUyU9uj7Y8alFFIm9pMwJgxaEZg9rbsK5D2eWR\n" +
//            "uShdcwpf8qmcdDeDF6x546B6NLbF6q6nAT1edK5S5mReShhWao8KAzbU7LFfP5m0\n" +
//            "zdQ578C+LFwOmmRv3uYLihsjP4302ss0lajtLHsCgYBzLmiMvHJuQsVLZ2v3n5pT\n" +
//            "brkYCLr3spOLVlfKYaSwZnXunvjtQZW3E7KkkJlVLfbC/TvOyMJ62GPV2t5rBCAZ\n" +
//            "Wvi8YHlnZ/3Np3X142sx1gh8KYgDTu9yBCbiY8VI28dUiS1GZxHDpvPlDaSLu3Ub\n" +
//            "bhJSJmPbYcP7DCg/YB+sOQKBgFIcsirlKaK8k4dtPJIreTTPmgA+FpRvNj/zX1TD\n" +
//            "NAUqSOOBceAGii99arDcMNj1bz6Pux9ds+8cMFU1+Q8d3ba3d8X6cEuJLmfwXApB\n" +
//            "RgzkiVjm1nQ+wxvH+apEarHwjVcEH+WZxwwIc8FzxF/m/oGaIvLp4//wwFKsxZFr\n" +
//            "zIjVAoGAKo6FqaKFSRXQLSG5FRJPX/KOkNOk/LRWY2JvkYUd31u+Ai9ybHcl3kGV\n" +
//            "njg2mH7Swn59bx1JpnwS7dw4nYY2/GZGan7lkcfkuHxDJW1R0ttYwALLOUUjpf/u\n" +
//            "QD/+651Pa2eL6BDkFGbsrACbZlvVeczR17lIV4LiyEtTE3rN3jk=\n" +
//            "-----END RSA PRIVATE KEY-----";
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

//        String jweString = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.XDHBEiAZ53ykW-lmi8Oz_TQ6w_PGj5nKbioqsNrtXiSa_oTUXMMsoHXqEnEKtKk2vqS06d685zKXSTB1nD2fh_TCS2b-APoyPHWDXFfCjC03CNEXOxn9qmSlQnLLYGcsZ4UWPD-mKNKCq_oYFKgbmgXQuxKRibHMIc5okBZJvZSYkVupZS0S6LBB71JX73IEfXicO6A3KzbwUcHN_Dv1q4Qs6zSXTDqX8gmV5fSI3jXQ7ns8N3UtGXz_ekTHCL0dWDSiO8OEwZPi1Ll1GCOgwWCaCAMLV-7tcNzXaekpjEVfHu7Z1x6SsTDAI1p-ftc54ZqnqwI1Y1G8I_o0li81fA.iGbhGKQ3VZ620-QJzeuJEg.HPzcqOrw0cvtyoD7cn9rmcJKequW9HH18amN0hkQpdmbm63tV0Cox4BfZyVk22w3NTUkmHyM28UQwO9tDZwlbUCCk-KaVNlpEUn40r3B0ks-H0u4XOU7yZJz3EJjKZMnYszkclMNV6_yjkTnwtvlrX4Gp3f7Cw9Tj8d0TO7vAmhGd56MajIxz_hQD1E7DpdufQkCFZN-zI8dviYe7w4zgDuNNBMEAvRZk1E8tnjBlWg.dyZfmH3uHQhFnPvY4bZksn9dqnbpcIbGmvYYLujyXaA";

//        String jweString = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.BTZUueGTki7opvctcgoGt93w2E8vTw-EoLkergXPdGBV4qvf0geY0l21JzluW0laO8uLp44Wb2E1x8BLmmArq1GvfZdA3iYNAuFJAtP8YNUVv_DncT6PURnHABCav5dKbMSvfFG9krgRhbth_ebvNrrokXTOjAS1Y_YHgBe7jKnKgPWmy5hhMm7an-amOjrI2KunzqMiA5FoLMSBRxyavMt1Eg0kro7xmAO6ZSiMeVFZRDx3IJtqjUCIwrmdzVplw8wBt9N8I1gSuUF-L2i4ZY6gKuM8SZJUxiHuB1PLLmscUzTLpGauseu7LkQwNbkHAAmS3h2Lz-Cn_DHwxdoKZA.fNhQwrdIosG_a83i1TPCEQ.yEAQrlPRsXUTn7sBa4yDCPDtE2Iu4P4vVIYWHk7RMUv818xVwAhelXpqulEmpbSVJoFvjNOkdBEZf9KNWvaXARaN71o5vOcZpe_f3gaMBmovIZO5izV-4o94UdkG_Eshdd0ErMkGd5KpSg3Ry8gTsVyknLsIZFLoOs5uPiUr2AtxCPn6UYIOQBoq3HaHoppnpscbVmkkdWE3wNSnwjiFq2WCVSSNKs1BOzg19iB2luM.Dy-bo05UWa-RYMKOFrZPPqmkB7oBagXQPD1s_693uW8";


        String jweString = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Ea8cddHaLqqQ3iZwbAouIzCkIKsIA6A3jSSiFQhtOSWr__EqYS-9WpaHsEg43hmIZec2wE3bJa-xoMWnXAqxiDxFMZQCNAevXyMYXgL8ycUSBFAzTCdBBUGNDBx_gQGLoQVjMbPJ0yk3-9k_NyT5aX20yq-NgOV7YXUZQ85kPvScirWPz45sWIfM7SzFtpkPdtRy6jLjQubvNwgz-3Eb1wtN-8742IeubeHB26s7u8p4c31ybu-jtyGDcPg0S-8nBp2-IU4JYUA-P4m2nXGvGeFyJhUW3vqFFRJQgBrB2J8lbKirZE3804vnkrgXdvnLRwFKQLxE_BBIZWSwjZe0mA.tU9jwMrVnF8ipKIe-w1gIg.FOkt1X6FfXpMxn4YF_mQlsLz9Xk6EXy4OdrRJRIm3fhMz0zhMu-aMvNOS6pUnc5hpN9eqbYcSYTrTP9fHYhA3jhmVx3Z0nHmVBt4cvq3ND0bjoTtXZIgPUWpxVBF9U0X1k189ddS5I5xO28sLrHRUK3UXTX3VVr082yiVMErV15GArk-7R6cOPzG3tTwVRq9GSN23VlpNahmDauQz0Bz4sd4LVC2YfwXBfvoNlw_Zcc.6c4rPaoQXHZkpCcg_b2pEoUdFNtUuqqmmmHzzmlxVWU";

        // Parse the JWE string
        JWEObject jweObject = JWEObject.parse(jweString);



        // Decrypt with private key
        jweObject.decrypt(new RSADecrypter(recPrivateKey));

//        jweObject.getPayload().
        Payload payload = jweObject.getPayload();
        System.out.println("jweObject.getHeader()::" + jweObject.getHeader());
        System.out.println("payload::" + payload);

        System.out.println("jweObject.getPayload().toJSONObject()::" + jweObject.getPayload().toJSONObject());
        // Extract payload
//        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

        EncryptedJWT JWT1 = EncryptedJWT.parse(jweString);

        System.out.println("JWT1.getHeader()::" + JWT1.getHeader());

        RSADecrypter decrypter = new RSADecrypter(recPrivateKey);
        // Decrypt
        JWT1.decrypt(decrypter);

        System.out.println("JWT1.getHeader().getEncryptionMethod().getName()::" + JWT1.getHeader().getEncryptionMethod().getName());



//        System.out.println(signedJWT.getJWTClaimsSet().getClaim("params"));
//        System.out.println("signedJWT.getHeader().getAlgorithm().getName()::" + signedJWT.getHeader().getAlgorithm().getName());
//        System.out.println("signedJWT.verify(new RSASSAVerifier(publicKey3))::" + signedJWT.verify(new RSASSAVerifier(senderPublicKey)));

        System.out.println("signedJWT::" + payload);
//        System.out.println(payload..getJWTClaimsSet().getIssuer());
//        System.out.println(signedJWT.getJWTClaimsSet().getSubject());
//        System.out.println(signedJWT.getJWTClaimsSet().getAudience().size());
//        System.out.println(signedJWT.getJWTClaimsSet().getExpirationTime());
//        System.out.println(signedJWT.getJWTClaimsSet().getNotBeforeTime());
//        System.out.println(signedJWT.getJWTClaimsSet().getIssueTime());
//        System.out.println(signedJWT.getJWTClaimsSet().getJWTID());


//        ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
//        JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(secretKey);
//        JWEKeySelector<SimpleSecurityContext> jweKeySelector =
//            new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
//        jwtProcessor.setJWEKeySelector(jweKeySelector);

        String tokenStr = decodeToken(payload.toString(), null);

        String[] chunks = payload.toString().split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();

        String tokenStr11 = Optional.ofNullable(chunks)
                .filter(strings -> strings.length >= 1)
                .map(strings -> new String(decoder.decode(strings[0])))
                .orElse(null);

        System.out.println("tokenStr11::" + tokenStr11);


//        JsonNode payload1 = parseJsonString(tokenStr);
//        System.out.println("request payload::{}" + payload1);
//


        JWTClaimsSet claimsSet = JWTClaimsSet.parse(tokenStr);

//        Date now = new Date();
//        Date exp = claimsSet.getExpirationTime();
//        if (exp != null && !DateUtils.isAfter(exp, now, 0)) {
//            throw new BadJWTException("Expired JWT");
//        }

        System.out.println("claimsSet.getExpirationTime()::" + claimsSet.getExpirationTime());

        System.out.println("claimsSet::" + claimsSet);

        /*DefaultJWTClaimsVerifier<?> verifier = new DefaultJWTClaimsVerifier<>(
                new JWTClaimsSet.Builder()
                .issuer("rac575.uat")
                .audience("consent-mgt-ui.nonprod-cdrbank.racq.com.au")
                        .build()
                new HashSet<>(Arrays.asList("iss","aud","exp"))
        );*/
        JSONObject payload1 = new JSONObject();
        //System.out.println("String.valueOf(payload1.get(\"iss\")):" + String.valueOf(payload1.get("iss")));
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
            .issuer(String.valueOf(payload1.get("rac575.uat")))
            .audience(String.valueOf(payload1.get("consent-mgt-ui.nonprod-cdrbank.racq.com.au")))
            //.subject(String.valueOf(payload1.get("sub")))
            .claim("exp", "1643582696")
            .build();

        //verifier.verify(claimsSet, null);


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
//            logger.info("Token String::{}", tokenStr);
        } else {
            tokenStr = signedJWT.getPayload().toString();
        }

        return tokenStr;

    }



}
