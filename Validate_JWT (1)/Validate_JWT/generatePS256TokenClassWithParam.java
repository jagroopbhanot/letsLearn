/**package com.cuscal.userlib;
import com.accelq.common.library.annotations.Command;
import com.accelq.common.library.annotations.Parameter;
import com.accelq.common.library.annotations.UserLibrary;
import com.accelq.core.SingletonInstancesRepo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.accelq.utils.AQLogUtils;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
@UserLibrary()
public class generatePS256TokenClassWithParam {
    private AQLogUtils aqLogUtils = SingletonInstancesRepo.getAqLogUtils();
    @Command(
            displayName = "generatePS256Token",
            templateStr = "generatePS256Token duration <param1> cdr_arrangement_id <param2>",
            description = "generatePS256Token",
            isVerification = false,
            tags = "duration;cdrArrangementId;")
    public String generatePS256Token(
            @Parameter(name = "duration", description = "Provide duration") String duration_input,
            @Parameter(name = "cdr_arrangement_id", description = "Provide cdr_arrangement_id") String cdr_arrangement_id_input)
    {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            String privatekeystr = "-----BEGIN PRIVATE KEY-----MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS6V7URmgEHqX7HIGPRWmsK3cTjgPUm9tc9KbQa4pJqNwmnC1De4sKcIu/C/l973FaJ6/NeZ/cMiLRL5ZjtwZ8u5IAck8W5rT22qU9BAiK/a3KFqwFFzXaDWmnRWMs5WOpJblmUeK9rbvg2EiYNrV5hfSAiN8Cwk6aAvp1zyyG2iqlCx80bhKMbb1Fe2J/hzidKQflytfBBnGwEPiM9VKtcrh6AvFXtLH2whN3c5Gey3G30mZIczKZbSIeNjyQX1v+eP34OKmMMHCmUrPat9HVjfvsimfE4oXB78IBEf2CmWGmC2WiwYg0sNYiPjA/VI4zg14ky6bLiIeMXUzmXDrzAgMBAAECggEAU3oltyrWedjMaZZ2C+neNMVx4PumM43oJLajfvRMXLSFe67sjVbK18DBt2nfd26yEw9P9spwSpd2bzTCGKDsH74ZJQXO6O1o0IfGspfzHr8snBMr5aydnexXJ4Pm6aDicz+LTJek5jCs/AMJpOwZTBcQqmSbi57LOPoORRjWbTz1j7My7m0nHK5NbX8befJ8yAw8vjgPRFsyQ4wYaEykfTwOWFwoQ68ow68f7uo8lxRZ/tPefTrpy1OjRx5GHJ2AXjbM3PMBjjPzWpGJWTKXU3IlN6NSNWmo1kmJK9GzfbyJ8c6mHijWQ/WM/HZSN8NF8DHerMUMpq0ujUrGkxefYQKBgQDgk0synIFKo6oW5ASexpQgsIkZzsuewI4s48LXiXEarL4NCAHTwhK7vpUGGaynP3qQm2k22oZsXsOesm/Iw7TGmNq2JeEWBPujqY//8a7OQUuOVkGRK9ZYU2BWWItjx4HHri2lCJabDUXdemcpgS+xDmoHISXeCj7dcnZZMFT90QKBgQCneARApNIouWHb9kldjZXc+MBROx30jKiXoX7AsTUkhzHpmE5BzrHxI+df8VSU/t3uGkKVK+f7jZfjy+XRtMJq6hbIirCgENnlLNk5aIYCeNq8OB7glp1p4T0BdxsUu8XGu5DXP4jPBj4i2CwrXNo+OTxaSfuN+tXgHitCEVIJgwKBgQDYxdTkOaWjgm+AQQEksdHx6/Wq764kwwF52zAMFSMT+IJK2dvJp5+lkcUPamD535BTXXAl/rGaka6J1PuJ8z7gJFOZVt75/j1YMShKieOOPADOl/waQRJZl8F6F2YVHlLrh/mPP8cAS8OQkcJakdSTN7KoxxpiRcmAwCf0125ywQKBgEKtIRxPwzDdpl26cDlkPs+s5n0xCVstiY5diSbVJzB092Vm83l1/xjgT6W+Ywuzcc7z+6CCy6k3Fctnigf1bRa+PvX3ah1AuFBri800lW50ibo4qeqHbQMT34Mu1cRqgnL+iMt6i1DJzoF3Chb1sBroFORp4lMEFJVXzadPWBdbAoGBAKw2V3jlp/2qxCWigWVN8+1izEnJJ61NFKaIBFxGEudcy5TSUP1rb0fVqQxEFu+5ry3om7zsYoKCe0dcesXUJdwsAHdaVjVPKQaOv351pA+IbqTMrkMVCUlNkKHQlqVa90LaCZ7CjLHKC62nTFVPYBUNNwjKYLza9MMPGLmpTbak-----END PRIVATE KEY-----";
            String privateKeyPEM = privatekeystr
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encoded = decoder.decode(privateKeyPEM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            RSAPrivateKey senderPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            Provider bc = BouncyCastleProviderSingleton.getInstance();
            Security.addProvider(bc);
            JWSSigner signer = new RSASSASigner(senderPrivateKey);
            signer.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
            AdditionalClaims additionalClaims = new AdditionalClaims();
            additionalClaims.setDuration(duration_input);
            additionalClaims.setScope("openid profile bank:accounts.basic:read bank:accounts.detail:read");
            additionalClaims.setCdr_arrangement_id(cdr_arrangement_id_input);
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("jwt-rsa").build(),
                    new JWTClaimsSet.Builder()
                            .audience("consent.ui.cds.cuscal.com.au")
                            .subject("cuscal test")
                            .notBeforeTime(new Date(new Date().getTime() / 1000 * 1000))
                            .issueTime(new Date(new Date().getTime() / 1000 * 1000))
                            .jwtID(UUID.randomUUID().toString())
                            .expirationTime(new Date(new Date().getTime() + 3600 * 1000))
                            .issuer("A:1")
                            .claim("data_holder_brand_id", "acccunicornbank")
                            .claim("r_code", "Rlhswtacrga779hht4eqw1m7vihcq8ov")
                            .claim("userId", "acccunicornbank")
                            .claim("typ", "RS256")
                            .claim("loa", "1.0")
                            .claim("sessionId", "String")
                            .claim("sp_id", "af9f578f-3d96-ea11-a831-000d3a8842e1")
                            .claim("ss_type", "String")
                            .claim("params", additionalClaims)
                            .build());
            signedJWT.sign(signer);
            String s1 = signedJWT.serialize();
            return s1;
        }catch (Exception ex) {
            return "0";
        }
    }
    public static class AdditionalClaims {
        @JsonProperty("scope")
        private String scope;
        public String getScope() {
            return scope;
        }
        public void setScope(String scope) {
            this.scope = scope;
        }
        public String getDuration() {
            return duration;
        }
        public void setDuration(String duration) {
            this.duration = duration;
        }
        public String getCdr_arrangement_id() {
            return cdr_arrangement_id;
        }
        public void setCdr_arrangement_id(String cdr_arrangement_id) {
            this.cdr_arrangement_id = cdr_arrangement_id;
        }
        @JsonProperty("duration")
        private String duration;
        @JsonProperty("cdr_arrangement_id")
        private String cdr_arrangement_id;
    }
}**/