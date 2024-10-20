package com.ebaad.SpringSecurityJWTProject;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {

    @Test
    public void generateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build(); // this will generate secret key

        // Convert the key into string value
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.printf("\nKey = [%s]\n", encodedKey);
    }
}
