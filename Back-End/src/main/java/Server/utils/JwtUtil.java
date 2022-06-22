package Server.utils;

import Server.pojo.UserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
//    private static final long EXPIRE_TIME = 20 * 1000;
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;
    private static final Algorithm algorithm = Algorithm.HMAC256("KEXIE_SECRET");
    public static String createToken(UserInfo user) {
        Map<String, Object> map = new HashMap<>();
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + EXPIRE_TIME);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userID", user.getUserId())
                .withClaim("dNo", user.getDNo())
                .withClaim("isAdmin", user.getType())
                .withAudience(user.getUserName())
                .withIssuer("KEXIE")
                .withIssuedAt(nowDate).withExpiresAt(expireDate)
                .sign(algorithm);
        System.out.println(token);
        return token;
    }

    public static String getUserName(String token) {
        return JWT.decode(token).getAudience().get(0);
    }

    public static String getUserdNo(String token) {
        return JWT.decode(token).getClaim("dNo").asString();
    }

    public static boolean isAdmin(String token) {
        return JWT.decode(token).getClaim("isAdmin").asBoolean();
    }

    public static boolean checkSign(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
//            throw new RuntimeException("token无效,请重新获取");
        }

    }
}
