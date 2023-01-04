//package au.com.cuscal.domain.web.rest.validator;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class Decode_Verify_HS256 {


    public static void main(String s[]) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, IOException {

        // Generate random 256-bit (32-byte) shared secret
        SecureRandom random = new SecureRandom();
        String secretKey = "Lets have this as secret key forever";
        random.nextBytes(secretKey.getBytes(StandardCharsets.UTF_8));

        final SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HMAC");

        String s1 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXNjYWwgdGVzdCIsInNzX3R5cGUiOiJTdHJpbmciLCJpc3MiOiJBOjEiLCJ0eXAiOiJSUzI1NiIsInJfY29kZSI6IlJ2OGIzbGE0OG8xZ21pOGdtaHMxOXloa3Q5OW41Z2Q3Iiwic2Vzc2lvbklkIjoiU3RyaW5nIiwicGFyYW1zIjp7ImR1cmF0aW9uIjoiMTAwIiwiY2RyX2FycmFuZ2VtZW50X2lkIjoiQ0RSIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBiYW5rOmFjY291bnRzLmJhc2ljOnJlYWQgYmFuazphY2NvdW50cy5kZXRhaWw6cmVhZCJ9LCJ1c2VySWQiOiJhY2NjdW5pY29ybmJhbmsiLCJhdWQiOiJjb25zZW50LnVpLmNkcy5jdXNjYWwuY29tLmF1IiwibmJmIjoxNjI2MzM5MjIzLCJzcF9pZCI6IjMwNTFiM2FiLTQwOTYtZWExMS1hODMxLTAwMGQzYTgiLCJkYXRhX2hvbGRlcl9icmFuZF9pZCI6ImFjY2N1bmljb3JuYmFuayIsImV4cCI6MTYyNjM0MjgyMywiaWF0IjoxNjI2MzM5MjIzLCJqdGkiOiI5ZDZlM2Y5MC1kMDgyLTQ2NjAtOTcxNS1kMDcwOGIxOTc0MzMiLCJsb2EiOiIxLjAifQ.2GzFkmgCNLir34zS-xMqPfrXlG3QO9c6n_7PjyLioH8";

        ConfigurableJWTProcessor<SimpleSecurityContext> processor = new DefaultJWTProcessor<>();
        processor.setJWSKeySelector(new JWSKeySelector<SimpleSecurityContext>() {
            @Override
            public List<? extends Key> selectJWSKeys(JWSHeader header, SimpleSecurityContext context) {
                return Collections.singletonList(key);
            }
        });
        SignedJWT s2 = SignedJWT.parse(s1);
        System.out.println("getContentType::" + s2.getHeader().getContentType());
        System.out.println("getAlgorithm::" + s2.getHeader().getAlgorithm());
        System.out.println("getType::" + s2.getHeader().getType());
        System.out.println("alg verification::" + s2.getHeader().getAlgorithm().equals(JWSAlgorithm.HS256));
        try {
            processor.process(SignedJWT.parse(s1), null);
        } catch (BadJWTException e) {
            e.printStackTrace();
        } catch (BadJOSEException e) {
            e.printStackTrace();
        }


        SignedJWT signedJWT = SignedJWT.parse(s1);
        System.out.println("payload::" + signedJWT.getPayload());
        JWSVerifier verifier = new MACVerifier(secretKey.getBytes(StandardCharsets.UTF_8));

        System.out.println("signed jwt verification:" + signedJWT.verify(verifier));


    }


}
