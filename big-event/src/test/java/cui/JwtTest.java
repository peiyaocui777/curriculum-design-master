package cui;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/15 23:50
 * @Description: TODO
 */

public class JwtTest {
	@Test
	public void testGen(){
		//map集合，存放一组载荷信息
		Map<String,Object> claims = new HashMap<>();
		claims.put("id",1);//相当于形参 实参
		claims.put("username","张三");
		//生成jwt的代码
		String token = JWT.create()//链式编码
				.withClaim("user", claims)//添加载荷
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//添加过期时间
				.sign(Algorithm.HMAC256("cuipeiyao"));//指定算法 配置秘钥
		System.out.println(token);
	}
	@Test
	public void testParse(){
		//定义字符串，模拟用户传递过来的token
		String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
				".eyJleHAiOjE3NDczNjgyMDcsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19" +
				".JWEqPpDOppO5fQCDNEfVjdn6qw3U3DM1o9Nw2G_xUKg";

		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("cuipeiyao")).build();

		DecodedJWT verify = jwtVerifier.verify(token);//生成一个解析后的JWT对象
		Map<String, Claim> claims = verify.getClaims();
		System.out.println(claims.get("user"));
	}
}
