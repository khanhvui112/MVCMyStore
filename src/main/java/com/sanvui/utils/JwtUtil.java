package com.sanvui.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * @author: VuiSK
 * @created: 13/12/2021-12:22 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private static String s;

    private static final String jwtSecret ="Vui@23102000Vui@23102000Vui@23102000";

    private static final Integer jwtExpirationDay =7;

    private static final String url ="http://localhost:9999";

    public static String encode(Map<String, Object> claims) throws ParseException, JOSEException {

        Date expiredTime = Date.from(
                LocalDateTime.now().plusDays(jwtExpirationDay).
                        atZone(ZoneId.systemDefault()).toInstant());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder(JWTClaimsSet.parse(claims))
                .issuer(url)
                .expirationTime(expiredTime).build();
        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSHeader jwsHeader = new JWSHeader.Builder((JWSAlgorithm.HS256))
                .contentType("JWT")
                .build();
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(jwtSecret.getBytes()));

        return jwsObject.serialize();
    }

    public static JWSObject decodeToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier jwsVerifier = new MACVerifier(jwtSecret.getBytes());
            boolean valid = jwsObject.verify(jwsVerifier);
            return valid ? jwsObject : null;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

}
