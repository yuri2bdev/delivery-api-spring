package br.com.delivery.deliveryapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jks.pass}")
    private String jksPass;
    @Value("${jwt.expiration}")
    private String EXPIRATION_TIME;

    @PostConstruct
    public void init() {
        try {
            this.keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/delivery.jks");
            this.keyStore.load(resourceAsStream, this.jksPass.toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        Date.from(
                                LocalDateTime.now().plusMinutes(Long.parseLong(this.EXPIRATION_TIME))
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()))
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    private Key getPrivateKey() {
        try {
            return (PrivateKey) this.keyStore.getKey("delivery", this.jksPass.toCharArray());
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return this.keyStore.getCertificate("mira").getPublicKey();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserNameFromJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

}
