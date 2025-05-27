# Redis 学习笔记：从入门到实践

## 一、Redis 基础知识

### 1. Redis 是什么？
Redis (Remote Dictionary Server) 是一个开源的、基于内存的键值对数据库，以高性能、灵活性和丰富的数据结构著称。

### 2. Redis 特点
- **高性能**：基于内存操作，读写速度极快
- **持久化**：支持数据持久化到硬盘
- **丰富的数据结构**：支持多种数据类型
- **原子性操作**：所有操作都是原子性的
- **支持事务**：可以一次执行多个命令
- **支持发布订阅**：可用于消息系统

## 二、Redis 八大数据结构

### 1. 字符串 (String)
**概念**：最基本的数据类型，可以存储文本、整数或二进制数据。

**Java 操作示例**：
```java
// 设置字符串
stringRedisTemplate.opsForValue().set("name", "张三");

// 获取字符串
String name = stringRedisTemplate.opsForValue().get("name");

// 设置带过期时间的字符串（10秒）
stringRedisTemplate.opsForValue().set("verifyCode", "123456", 10, TimeUnit.SECONDS);

// 计数器操作
stringRedisTemplate.opsForValue().increment("pageViews", 1);
```

**应用场景**：
- 存储用户信息
- 缓存页面数据
- 计数器（如点赞数、访问量）
- 分布式锁

### 2. 列表 (List)
**概念**：有序的字符串列表，基于双向链表实现。

**Java 操作示例**：
```java
// 从左侧添加元素
stringRedisTemplate.opsForList().leftPush("messages", "消息1");

// 从右侧添加元素
stringRedisTemplate.opsForList().rightPush("messages", "消息2");

// 获取列表范围
List<String> messages = stringRedisTemplate.opsForList().range("messages", 0, -1);

// 从左侧弹出元素
String message = stringRedisTemplate.opsForList().leftPop("messages");
```

**应用场景**：
- 消息队列
- 最新动态列表
- 文章列表

### 3. 哈希表 (Hash)
**概念**：键值对的集合，适合存储对象。

**Java 操作示例**：
```java
// 设置单个字段
stringRedisTemplate.opsForHash().put("user:1001", "name", "张三");
stringRedisTemplate.opsForHash().put("user:1001", "age", "25");

// 一次设置多个字段
Map<String, String> map = new HashMap<>();
map.put("name", "李四");
map.put("age", "28");
map.put("city", "北京");
stringRedisTemplate.opsForHash().putAll("user:1002", map);

// 获取单个字段
String name = (String) stringRedisTemplate.opsForHash().get("user:1001", "name");

// 获取所有字段
Map<Object, Object> userInfo = stringRedisTemplate.opsForHash().entries("user:1001");
```

**应用场景**：
- 存储用户信息
- 购物车
- 商品详情

### 4. 集合 (Set)
**概念**：无序、唯一元素的集合。

**Java 操作示例**：
```java
// 添加元素
stringRedisTemplate.opsForSet().add("tags:article:1", "Java", "Redis", "Spring");

// 获取所有元素
Set<String> tags = stringRedisTemplate.opsForSet().members("tags:article:1");

// 判断元素是否存在
Boolean hasTag = stringRedisTemplate.opsForSet().isMember("tags:article:1", "Java");

// 计算交集
Set<String> commonTags = stringRedisTemplate.opsForSet().intersect("tags:article:1", "tags:article:2");
```

**应用场景**：
- 标签系统
- 共同好友
- 黑名单/白名单

### 5. 有序集合 (Sorted Set/ZSet)
**概念**：有序、唯一元素的集合，每个元素关联一个分数。

**Java 操作示例**：
```java
// 添加元素
stringRedisTemplate.opsForZSet().add("ranking", "张三", 89.5);
stringRedisTemplate.opsForZSet().add("ranking", "李四", 92.0);
stringRedisTemplate.opsForZSet().add("ranking", "王五", 76.5);

// 获取分数
Double score = stringRedisTemplate.opsForZSet().score("ranking", "李四");

// 获取排名（从高到低）
Long rank = stringRedisTemplate.opsForZSet().reverseRank("ranking", "张三");

// 获取排名区间的元素
Set<String> topThree = stringRedisTemplate.opsForZSet().reverseRange("ranking", 0, 2);
```

**应用场景**：
- 排行榜
- 带权重的任务队列
- 延时队列

### 6. 位图 (Bitmap)
**概念**：基于String类型的位操作，可以将String视为位数组。

**Java 操作示例**：
```java
// 设置位图中指定位置的值
stringRedisTemplate.opsForValue().setBit("user:signin:1001", 1, true); // 表示第1天签到

// 获取位图中指定位置的值
Boolean signed = stringRedisTemplate.opsForValue().getBit("user:signin:1001", 1);

// 统计位图中值为1的位数
Long count = stringRedisTemplate.execute(
    (RedisCallback<Long>) connection -> connection.bitCount("user:signin:1001".getBytes())
);
```

**应用场景**：
- 用户签到
- 在线状态统计
- 布隆过滤器

### 7. HyperLogLog
**概念**：用于基数统计的概率数据结构，占用空间小。

**Java 操作示例**：
```java
// 添加元素
stringRedisTemplate.opsForHyperLogLog().add("page:uv", "user1", "user2", "user3");

// 统计基数
Long count = stringRedisTemplate.opsForHyperLogLog().size("page:uv");

// 合并多个HyperLogLog
stringRedisTemplate.opsForHyperLogLog().union("total:uv", "page:uv:1", "page:uv:2");
```

**应用场景**：
- 网站UV统计
- 大数据去重

### 8. 地理空间 (Geo)
**概念**：用于存储地理位置信息。

**Java 操作示例**：
```java
// 添加地理位置
Point point1 = new Point(116.48, 39.96);
stringRedisTemplate.opsForGeo().add("stores", point1, "store1");

// 计算两点距离
Distance distance = stringRedisTemplate.opsForGeo().distance(
    "stores", "store1", "store2", Metrics.KILOMETERS
);

// 查找附近的点
Circle circle = new Circle(point1, new Distance(5, Metrics.KILOMETERS));
GeoResults<RedisGeoCommands.GeoLocation<String>> results = 
    stringRedisTemplate.opsForGeo().radius("stores", circle);
```

**应用场景**：
- 附近的人/店铺
- 打车应用
- 位置共享

## 三、在 Spring Boot 中集成 Redis

### 1. 添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 2. 配置 Redis 连接
```properties
# application.properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=  # 如果有密码
spring.redis.database=0
```

### 3. 创建 Redis 配置类
```java
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // 使用Jackson2JsonRedisSerializer序列化值
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        
        // 设置key和value的序列化规则
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        
        return template;
    }
}
```

### 4. 创建 Redis 工具类
```java
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 设置带过期时间的缓存
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 获取缓存
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 删除缓存
     */
    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
    
    // 更多方法可以根据需要添加...
}
```

## 四、Redis 实战案例

### 1. 实现商品缓存
```java
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String PRODUCT_KEY = "product:";
    private static final long CACHE_TIME = 3600; // 1小时
    
    public Product getProductById(Long id) {
        String key = PRODUCT_KEY + id;
        
        // 先从缓存获取
        Product product = (Product) redisTemplate.opsForValue().get(key);
        if (product != null) {
            return product;
        }
        
        // 缓存未命中，从数据库获取
        product = productMapper.selectById(id);
        if (product != null) {
            // 放入缓存
            redisTemplate.opsForValue().set(key, product, CACHE_TIME, TimeUnit.SECONDS);
        }
        
        return product;
    }
    
    public void updateProduct(Product product) {
        // 更新数据库
        productMapper.updateById(product);
        
        // 更新缓存
        String key = PRODUCT_KEY + product.getId();
        redisTemplate.opsForValue().set(key, product, CACHE_TIME, TimeUnit.SECONDS);
    }
    
    public void deleteProduct(Long id) {
        // 删除数据库记录
        productMapper.deleteById(id);
        
        // 删除缓存
        String key = PRODUCT_KEY + id;
        redisTemplate.delete(key);
    }
}
```

### 2. 实现接口限流
```java
@Component
public class RateLimiter {
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 简单限流：每个用户每分钟只能访问指定次数
     */
    public boolean isAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("limit:%s:%s", userId, actionKey);
        
        // 当前时间
        long now = System.currentTimeMillis();
        
        // 创建一个事务
        redisTemplate.multi();
        
        // 添加当前时间戳到有序集合
        redisTemplate.opsForZSet().add(key, String.valueOf(now), now);
        
        // 移除时间窗口之前的数据
        redisTemplate.opsForZSet().removeRangeByScore(key, 0, now - period * 1000);
        
        // 获取集合大小
        Long count = redisTemplate.opsForZSet().zCard(key);
        
        // 设置过期时间，避免冷用户持续占用内存
        redisTemplate.expire(key, period + 1, TimeUnit.SECONDS);
        
        // 执行事务
        redisTemplate.exec();
        
        return count != null && count <= maxCount;
    }
}

// 使用示例
@RestController
public class ApiController {
    @Autowired
    private RateLimiter rateLimiter;
    
    @GetMapping("/api/test")
    public String test(HttpServletRequest request) {
        String userId = getUserId(request);  // 获取用户ID的方法
        
        // 限制用户每分钟最多访问10次
        boolean allowed = rateLimiter.isAllowed(userId, "test_api", 60, 10);
        
        if (!allowed) {
            return "访问频率过高，请稍后再试";
        }
        
        // 正常处理业务逻辑
        return "访问成功";
    }
}
```

### 3. 实现分布式锁
```java
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 尝试获取分布式锁
     * @param lockKey 锁的key
     * @param requestId 请求标识（用于释放自己的锁）
     * @param expireTime 锁的过期时间（秒）
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId, int expireTime) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(
            lockKey, requestId, expireTime, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(result);
    }
    
    /**
     * 释放分布式锁
     * @param lockKey 锁的key
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute(
            new DefaultRedisScript<>(script, Long.class),
            Collections.singletonList(lockKey),
            requestId
        );
        return Long.valueOf(1).equals(result);
    }
}

// 使用示例
@Service
public class OrderService {
    @Autowired
    private RedisLock redisLock;
    
    public boolean createOrder(String userId, String productId) {
        String lockKey = "order:lock:" + productId;
        String requestId = UUID.randomUUID().toString();
        
        try {
            // 尝试获取锁，防止超卖
            boolean locked = redisLock.tryLock(lockKey, requestId, 10);
            if (!locked) {
                return false;
            }
            
            // 检查库存并创建订单的逻辑
            // ...
            
            return true;
        } finally {
            // 释放锁
            redisLock.releaseLock(lockKey, requestId);
        }
    }
}
```

### 4. 实现排行榜
```java
@Service
public class RankingService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    private static final String RANKING_KEY = "game:ranking";
    
    /**
     * 更新玩家分数
     */
    public void updateScore(String playerId, double score) {
        redisTemplate.opsForZSet().add(RANKING_KEY, playerId, score);
    }
    
    /**
     * 获取玩家排名（从0开始）
     */
    public Long getPlayerRank(String playerId) {
        return redisTemplate.opsForZSet().reverseRank(RANKING_KEY, playerId);
    }
    
    /**
     * 获取前N名玩家
     */
    public List<Map<String, Object>> getTopPlayers(int n) {
        Set<ZSetOperations.TypedTuple<String>> tuples = 
            redisTemplate.opsForZSet().reverseRangeWithScores(RANKING_KEY, 0, n - 1);
            
        List<Map<String, Object>> result = new ArrayList<>();
        if (tuples != null) {
            int rank = 0;
            for (ZSetOperations.TypedTuple<String> tuple : tuples) {
                Map<String, Object> player = new HashMap<>();
                player.put("playerId", tuple.getValue());
                player.put("score", tuple.getScore());
                player.put("rank", ++rank);
                result.add(player);
            }
        }
        
        return result;
    }
}
```

## 五、Redis 使用注意事项

### 1. 缓存穿透
**问题**：查询不存在的数据，每次都会穿透缓存到数据库。
**解决方案**：
- 缓存空对象
- 布隆过滤器

```java
public Product getProductById(Long id) {
    String key = PRODUCT_KEY + id;
    
    // 先从缓存获取
    Product product = (Product) redisTemplate.opsForValue().get(key);
    if (product != null) {
        // 如果是空对象标记，直接返回null
        if (product instanceof NullValueProduct) {
            return null;
        }
        return product;
    }
    
    // 缓存未命中，从数据库获取
    product = productMapper.selectById(id);
    
    // 即使为null也缓存，但缓存时间短一些
    if (product == null) {
        redisTemplate.opsForValue().set(key, new NullValueProduct(), 60, TimeUnit.SECONDS);
    } else {
        redisTemplate.opsForValue().set(key, product, CACHE_TIME, TimeUnit.SECONDS);
    }
    
    return product;
}

// 空值占位类
class NullValueProduct extends Product {
    private static final long serialVersionUID = 1L;
}
```

### 2. 缓存击穿
**问题**：热点key过期导致大量请求直接访问数据库。
**解决方案**：
- 互斥锁
- 热点数据永不过期

```java
public Product getProductById(Long id) {
    String key = PRODUCT_KEY + id;
    
    // 先从缓存获取
    Product product = (Product) redisTemplate.opsForValue().get(key);
    if (product != null) {
        return product;
    }
    
    // 缓存未命中，使用互斥锁
    String lockKey = "lock:" + key;
    boolean locked = redisTemplate.opsForValue().setIfAbsent(lockKey, "1", 10, TimeUnit.SECONDS);
    
    if (!locked) {
        // 获取锁失败，休眠一段时间后重试
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return getProductById(id);
    }
    
    try {
        // 双重检查
        product = (Product) redisTemplate.opsForValue().get(key);
        if (product != null) {
            return product;
        }
        
        // 从数据库获取
        product = productMapper.selectById(id);
        if (product != null) {
            redisTemplate.opsForValue().set(key, product, CACHE_TIME, TimeUnit.SECONDS);
        }
        
        return product;
    } finally {
        // 释放锁
        redisTemplate.delete(lockKey);
    }
}
```

### 3. 缓存雪崩
**问题**：大量缓存同时过期导致数据库压力骤增。
**解决方案**：
- 过期时间随机化
- 服务熔断或限流
- 多级缓存

```java
public void cacheProduct(Product product) {
    String key = PRODUCT_KEY + product.getId();
    
    // 过期时间加上随机值，防止同时过期
    long randomExpireTime = CACHE_TIME + new Random().nextInt(60 * 10); // 随机增加0-10分钟
    redisTemplate.opsForValue().set(key, product, randomExpireTime, TimeUnit.SECONDS);
}
```

## 六、Redis 实用工具方法

```java
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    // ====== 通用操作 ======
    
    /**
     * 指定缓存过期时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 获取过期时间
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 删除缓存
     */
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }
    
    // ====== String类型操作 ======
    
    /**
     * 获取缓存
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 设置缓存
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 设置带过期时间的缓存
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 递增
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }
    
    /**
     * 递减
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    
    // ====== Hash类型操作 ======
    
    /**
     * 获取Hash中的数据
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    
    /**
     * 获取Hash的所有键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * 设置Hash的值
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 设置Hash的值，并设置过期时间
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 向Hash中放入数据
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 向Hash中放入数据，并设置过期时间
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 删除Hash中的值
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }
    
    /**
     * 判断Hash中是否有该项
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    
    /**
     * Hash递增
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    
    /**
     * Hash递减
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
}
```

## 七、总结

Redis 作为一种高性能的内存数据库，提供了多种数据结构，每种结构都有其特定的应用场景。在 Java 开发中，特别是使用 Spring Boot 框架时，可以轻松集成 Redis 来提升应用性能。

