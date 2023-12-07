package riordanverse.riordanverse.security;

import java.util.Date;

import static riordanverse.riordanverse.security.SecurityConstants.EXPIRATION_TIME;
import static riordanverse.riordanverse.security.SecurityConstants.SECRET;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {
  public static String getToken(String idUsuario) {
    String token = JWT.create()
							.withSubject(idUsuario)
							.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
							.sign(Algorithm.HMAC512(SECRET.getBytes()));
    return token;
  }
}