package cui.example;

import com.cui.BigEventApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/27 1:38
 * @Description: TODO
 */
@SpringBootTest(classes = BigEventApplication.class)//初始化容器？
public class redisTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Test
	public void testSet(){
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		stringStringValueOperations.set("id","123",15, TimeUnit.SECONDS);
	}
	public void getTest(){
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		System.out.println(stringStringValueOperations.get("id"));
	}
	//继续

}
