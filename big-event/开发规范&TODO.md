# MyBatis 开发笔记与规范

---

## 一、MyBatis 为什么用实体类而不是字段？

### 1. 为什么不用字段？
字段传递是指在方法中单独传每个字段，比如 `update(Integer id, String name)`。但这会导致：
- 参数列表长，字段多时很乱。
- 动态生成 SQL 麻烦，需手动判断哪些字段有效。
- 表结构变化（如加字段）时，方法签名得改，维护成本高。

### 2. 为什么用实体类？
实体类是一个“容器”，把字段（如 `id`、`name`）打包在一起，比如 `Category` 类。MyBatis 用实体类的理由：

- **代码简洁**：
    - 实体类：`update(Category category)`，一个参数包含所有字段。
    - 字段：`update(Integer id, String name, String description)`，参数多，写起来麻烦。
- **自动映射**：
    - MyBatis 自动把实体类的字段（如 `category.getId()`）填到 SQL 的占位符（如 `#{id}`）。
    - 查询时，数据库的列（如 `name`）自动变成实体类的字段（如 `category.setName()`）。
- **动态 SQL**：
    - MyBatis 根据实体类字段是否为空，动态生成 SQL。比如：
      ```xml
      <update id="update">
          UPDATE category
          <set>
              <if test="name != null">name = #{name}</if>
          </set>
          WHERE id = #{id}
      </update>
      ```
      只更新非空字段，省去手动处理。
- **扩展方便**：
    - 表加新字段，只改实体类，方法签名不变。
    - 字段传递要改方法签名和调用代码，麻烦。
- **前端适配**：
    - 前端传 JSON（如 `{"id": 1, "name": "科技"}`），Spring 自动转成实体类，方便。

**结论**：实体类让代码简洁、灵活，MyBatis 的映射和动态 SQL依赖它，开发效率高。

---

## 二、MyBatis 如何映射？

### 1. 什么是映射？
映射是 MyBatis 的核心，把 Java 实体类和数据库表对接：
- 查询：数据库的行（如 `id=1, name="科技"`）变成实体类对象。
- 更新：实体类的字段（如 `name="新科技"`）填到 SQL。

### 2. 映射原理
MyBatis 像个“翻译员”，把 Java 和 SQL 互相转换：
1. **Mapper 接口**：定义操作。
   ```java
   public interface CategoryMapper {
       List<Category> list();
       Category findById(Integer id);
   }
   ```

2. **Mapper XML**：写 SQL 和映射规则。
   ```xml
   <select id="list" resultType="Category">
       SELECT id, name FROM category
   </select>
   ```

3. **参数映射**：
    - 调用 `findById(1)`，MyBatis 把 `1` 填到 `#{id}`：
      ```sql
      SELECT id, name FROM category WHERE id = 1
      ```

4. **结果映射**：
    - 数据库返回 `id=1, name="科技"`，MyBatis 创建 `Category` 对象，设置 `id=1`、`name="科技"`。

5. **动态 SQL**：
    - 根据实体类字段动态生成 SQL，比如只更新非空字段：
      ```xml
      <update id="update">
          UPDATE category
          <set>
              <if test="name != null">name = #{name}</if>
          </set>
          WHERE id = #{id}
      </update>
      ```

---

## 三、开发规范

以下是 Spring Boot + MyBatis 的通用规范，简洁实用，适合任何项目。

### 1. 命名规范
- **实体类**：与表名一致，驼峰命名。
  ```java
  public class Category {
      private Integer id;
      private String name;
  }
  ```

- **Mapper 接口**：`XxxMapper`，方法用动词：
    - 增：`insert`
    - 删：`delete`
    - 改：`update`
    - 查：`list`、`find`
  ```java
  public interface CategoryMapper {
      List<Category> list();
      Category findById(Integer id);
  }
  ```

- **Mapper XML**：文件名 `XxxMapper.xml`，SQL ID 与方法名一致。
  ```xml
  <mapper namespace="com.example.mapper.CategoryMapper">
      <select id="list" resultType="Category">
          SELECT id, name FROM category
      </select>
  </mapper>
  ```

- **Service**：`XxxService` 和 `XxxServiceImpl`。
  ```java
  public interface CategoryService {
      List<Category> list();
  }
  ```

- **Controller**：`XxxController`，方法名反映操作。
  ```java
  @RestController
  @RequestMapping("/categories")
  public class CategoryController {
      @GetMapping
      public Result<List<Category>> list() {...}
  }
  ```

- **数据库**：表名、字段名小写，用下划線（如 `category_name`），主键为 `id`。

### 2. 接口规范
- **RESTful 风格**：
    - `GET`：查（如 `GET /categories`）
    - `POST`：增（如 `POST /categories`）
    - `PUT`：改（如 `PUT /categories`）
    - `DELETE`：删（如 `DELETE /categories/{id}`）
    - 路径：资源复数（`/categories`），详情加 ID（`/categories/1`）。

- **返回值**：用 `Result<T>` 封装。
  ```java
  public class Result<T> {
      private int code; // 0: 成功
      private String message;
      private T data;
      public static <T> Result<T> success(T data) {...}
  }
  ```

- **参数校验**：用 `@Validated` 和注解。
  ```java
  public class Category {
      @NotNull(message = "ID 不能为空")
      private Integer id;
  }
  ```

### 3. 增删改查流程
- **新增**：
    1. 前端发 `POST`，带 JSON。
    2. Controller 校验，调用 Service。
    3. Service 调用 Mapper 的 `insert`。
    4. 返回新增对象。
  ```java
  @PostMapping
  public Result<Category> add(@RequestBody Category category) {
      categoryService.insert(category);
      return Result.success(category);
  }
  ```

- **删除**：
    1. 前端发 `DELETE`，带 ID。
    2. Service 调用 Mapper 的 `deleteById`。
    3. 返回成功。
  ```java
  @DeleteMapping("/{id}")
  public Result<?> delete(@PathVariable Integer id) {
      categoryService.deleteById(id);
      return Result.success();
  }
  ```

- **修改**：
    1. 前端发 `PUT`，带 JSON。
    2. Service 调用 Mapper 的 `update`。
    3. 返回更新后的对象。
  ```java
  @PutMapping
  public Result<Category> update(@RequestBody Category category) {
      categoryService.update(category);
      return Result.success(category);
  }
  ```

- **查询**：
    1. 前端发 `GET`，带参数或 ID。
    2. Service 调用 Mapper 的 `list` 或 `findById`。
    3. 返回结果。
  ```java
  @GetMapping
  public Result<List<Category>> list() {
      return Result.success(categoryService.list());
  }
  ```

### 4. SQL 规范
- **XML 优先**：复杂 SQL 用 XML，简单 SQL 用注解。
  ```xml
  <select id="list" resultType="Category">
      SELECT id, name FROM category
  </select>
  ```

- **动态 SQL**：用 `<if>`、`<set>`。
  ```xml
  <update id="update">
      UPDATE category
      <set>
          <if test="name != null">name = #{name}</if>
      </set>
      WHERE id = #{id}
  </update>
  ```

- **安全**：用 `#{}` 防 SQL 注入，避免 `${}`。
- **性能**：常用字段加索引，分页用 `LIMIT`，考虑缓存。

---

## 四、总结
1. **为什么用实体类**：简洁、自动映射、动态 SQL、易扩展、前端友好。
2. **映射原理**：MyBatis 把实体类字段和 SQL 占位符对接，查询结果映射回对象。
3. **开发规范**：
    - 命名：实体、Mapper、Service、Controller 统一。
    - 接口：RESTful，`Result` 封装，校验参数。
    - 流程：增删改查各有步骤，Service 管业务，Mapper 管 SQL。
    - SQL：XML 写复杂逻辑，动态 SQL，安全第一。
