//package au.com.cuscal.domain.web.rest.validator;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class Generate_HS256_Token {


    public static void main(String s[]) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, IOException {

        // Generate random 256-bit (32-byte) shared secret
        SecureRandom random = new SecureRandom();
        String secretKey = "Lets have this as secret key forever";
        random.nextBytes(secretKey.getBytes(StandardCharsets.UTF_8));

        new SecureRandom().nextBytes(secretKey.getBytes(StandardCharsets.UTF_8));
        final SecretKey key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HMAC");


        JWSSigner signer = new MACSigner(key);

        final Date now = new Date();
        AdditionalClaims additionalClaims = new AdditionalClaims();

        additionalClaims.setDuration("100");
        additionalClaims.setScope("openid profile bank:accounts.basic:read bank:accounts.detail:read");
        additionalClaims.setCdr_arrangement_id("CDR");
        SignedJWT signedJWT = new SignedJWT(
            new JWSHeader.Builder(JWSAlgorithm.HS256).build(),
            new JWTClaimsSet.Builder()
                .audience("consent.ui.cds.cuscal.com.au")
                .subject("cuscal test")
                .notBeforeTime(new Date(new Date().getTime() / 1000 * 1000))
                .issueTime(new Date(new Date().getTime() / 1000 * 1000))
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(new Date().getTime() + 360 * 100))
                .issuer("A:1")
                .claim("data_holder_brand_id", "acccunicornbank")
                .claim("r_code", "14gppgrfdhtmi9jj0lrd0ggspp")
                .claim("userId", "acccunicornbank")
                .claim("typ", "RS256")
                .claim("loa", "1.0")
                .claim("sessionId", "String")
                .claim("sp_id", "af9f578f-3d96-ea11-a831-000d3a8842e1")
                .claim("ss_type", "String")
                .claim("params", additionalClaims)
                .build());

        // Apply the HMAC
        signedJWT.sign(signer);

        String s1 = signedJWT.serialize();
        System.out.println("token::" + s1);

    }


}
