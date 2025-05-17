# ThreadLocal 与 Lambda 表达式全面笔记

## 第一部分：ThreadLocal

### 1. 什么是 ThreadLocal？

**ThreadLocal** 是 Java 提供的一个类，它为每个线程创建独立的变量副本。

> 💡 **核心概念**：线程专属的存储空间，实现线程间数据隔离。
>
> **形象比喻**：每个学生都有自己的私人抽屉，互不干扰。

### 2. 为什么需要 ThreadLocal？

在多线程编程中，ThreadLocal 解决以下问题：

| 问题 | 说明 | ThreadLocal 解决方案 |
|------|------|---------------------|
| 线程安全问题 | 多线程同时修改共享变量导致数据混乱 | 每个线程独立副本，互不影响 |
| 数据隔离问题 | Web 应用中不同用户请求需要隔离 | 每个线程存储自己的用户信息 |

### 3. 常见使用场景

- **用户上下文管理**：存储当前线程处理的用户 ID 或会话信息
- **数据库连接管理**：每个线程独立的数据库连接
- **请求追踪**：记录请求的处理过程和时间
- **临时变量传递**：线程内部各方法间传递数据

### 4. ThreadLocal 的基本用法

ThreadLocal 提供三个核心方法：

| 方法 | 作用 | 使用时机 |
|------|------|---------|
| `set(T value)` | 设置当前线程的变量值 | 线程开始处理任务时 |
| `get()` | 获取当前线程的变量值 | 线程中需要使用该值时 |
| `remove()` | 清除当前线程的变量值 | 线程结束任务时（防止内存泄漏） |

**示例代码**：

```java
public class ThreadLocalExample {
    // 创建一个 ThreadLocal，存储用户 ID
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();

    public static void main(String[] args) {
        // 线程 1
        new Thread(() -> {
            USER_ID.set("User001");
            System.out.println("线程 1: " + USER_ID.get());
            USER_ID.remove(); // 用完清理
        }).start();

        // 线程 2
        new Thread(() -> {
            USER_ID.set("User002");
            System.out.println("线程 2: " + USER_ID.get());
            USER_ID.remove(); // 用完清理
        }).start();
    }
}
```

**运行结果**：
```
线程 1: User001
线程 2: User002
```


**数据结构**：
- 每个线程有自己的 ThreadLocalMap
- ThreadLocalMap 存储键值对：键是 ThreadLocal 对象，值是存入的数据

**工作流程**：
1. `set(value)`：将值存入当前线程的 ThreadLocalMap
2. `get()`：从当前线程的 ThreadLocalMap 中取值
3. `remove()`：删除当前线程 ThreadLocalMap 中的对应键值对

### 6. ThreadLocal 的注意事项

#### 内存泄漏问题

⚠️ **风险**：如果不调用 `remove()`，线程结束后，ThreadLocalMap 中的值可能不会被回收。

**特别注意**：在线程池中，线程会被复用，必须在任务结束后调用 `remove()`。

```java
ExecutorService pool = Executors.newFixedThreadPool(2);
pool.submit(() -> {
    try {
        USER_ID.set("Task001");
        // 执行任务...
    } finally {
        USER_ID.remove(); // 防止内存泄漏，必须清理
    }
});
```

#### 设置初始值

可以通过重写 `initialValue()` 方法设置初始值：

```java
private static final ThreadLocal<String> USER_ID = new ThreadLocal<String>() {
    @Override
    protected String initialValue() {
        return "Guest";
    }
};
```

#### 线程安全考虑

ThreadLocal 本身是线程安全的，但存入的对象不一定安全：

```java
// 错误示例：共享对象仍需同步
ThreadLocal<List<String>> listThreadLocal = new ThreadLocal<>();
listThreadLocal.set(new ArrayList<>()); // 每个线程引用同一个 ArrayList
```

### 7. ThreadLocal 的进阶用法

#### InheritableThreadLocal

允许子线程继承父线程的 ThreadLocal 值：

```java
private static final InheritableThreadLocal<String> PARENT_DATA = new InheritableThreadLocal<>();

public static void main(String[] args) {
    PARENT_DATA.set("父线程数据");
    
    new Thread(() -> {
        System.out.println("子线程获取：" + PARENT_DATA.get()); // 输出：子线程获取：父线程数据
    }).start();
}
```

#### 结合 Spring 使用

在 Web 应用中管理用户上下文：

```java
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();
    
    public static void setUserContext(UserContext context) {
        userContext.set(context);
    }
    
    public static UserContext getUserContext() {
        return userContext.get();
    }
    
    public static void clear() {
        userContext.remove();
    }
}
```

## 第二部分：Lambda 表达式

### 1. 什么是 Lambda 表达式？

**Lambda 表达式**是 Java 8 引入的特性，用于简洁地表示匿名函数。

> 💡 **核心概念**：函数即数据，可以像传递变量一样传递行为。
>
> **形象比喻**：直接说"帮我拿杯水"，而不是详细描述每个步骤。

### 2. Lambda 表达式的基本语法

```
(参数) -> { 代码块 }
```

| 组成部分 | 说明 | 示例 |
|---------|------|------|
| 参数列表 | 可以有 0 个或多个参数 | `()`, `x`, `(x, y)` |
| 箭头符号 | 分隔参数和代码 | `->` |
| 代码块 | 表达式或语句块 | `x * 2`, `{ return x * 2; }` |

**语法变体**：

```java
// 无参数
() -> System.out.println("Hello")

// 单参数（可省略括号）
x -> x * 2

// 多参数
(x, y) -> x + y

// 多行代码
(x, y) -> {
    int sum = x + y;
    return sum * 2;
}
```

### 3. Lambda 表达式的常见用法

#### 3.1 创建线程 (Runnable)

```java
// 传统方式
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("线程运行");
    }
}).start();

// Lambda 方式
new Thread(() -> System.out.println("线程运行")).start();
```

#### 3.2 集合排序 (Comparator)

```java
List<String> fruits = Arrays.asList("banana", "apple", "cherry");

// 传统方式
Collections.sort(fruits, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Lambda 方式
fruits.sort((a, b) -> a.compareTo(b));
// 或使用方法引用
fruits.sort(String::compareTo);

System.out.println(fruits); // [apple, banana, cherry]
```

#### 3.3 集合遍历 (Consumer)

```java
List<String> fruits = Arrays.asList("apple", "banana", "cherry");

// 传统方式
for (String fruit : fruits) {
    System.out.println(fruit);
}

// Lambda 方式
fruits.forEach(fruit -> System.out.println(fruit));
// 或使用方法引用
fruits.forEach(System.out::println);
```

#### 3.4 集合过滤 (Predicate)

```java
List<String> fruits = Arrays.asList("apple", "banana", "cherry");

// Lambda 方式
List<String> aFruits = fruits.stream()
    .filter(fruit -> fruit.startsWith("a"))
    .collect(Collectors.toList());

System.out.println(aFruits); // [apple]
```

#### 3.5 集合映射 (Function)

```java
List<String> fruits = Arrays.asList("apple", "banana", "cherry");

// Lambda 方式
List<String> upperFruits = fruits.stream()
    .map(fruit -> fruit.toUpperCase())
    .collect(Collectors.toList());
// 或使用方法引用
List<String> upperFruits = fruits.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

System.out.println(upperFruits); // [APPLE, BANANA, CHERRY]
```

### 4. 方法引用

方法引用是 Lambda 表达式的简写形式，使用 `::` 操作符：

| 类型 | 语法 | 示例 |
|------|------|------|
| 静态方法引用 | `类名::静态方法名` | `Math::max` |
| 实例方法引用 | `实例::方法名` | `System.out::println` |
| 对象方法引用 | `类名::实例方法名` | `String::length` |
| 构造方法引用 | `类名::new` | `ArrayList::new` |

```java
// Lambda 表达式
Function<String, Integer> strLength = s -> s.length();
// 方法引用等价形式
Function<String, Integer> strLength = String::length;

// Lambda 表达式
BiFunction<Integer, Integer, Integer> max = (a, b) -> Math.max(a, b);
// 方法引用等价形式
BiFunction<Integer, Integer, Integer> max = Math::max;
```

### 5. 函数式接口

Lambda 表达式需要函数式接口（只有一个抽象方法的接口）作为目标类型。Java 8 在 `java.util.function` 包中提供了许多标准函数式接口：

| 接口 | 方法 | 描述 | 示例 |
|------|------|------|------|
| `Runnable` | `run()` | 无参数无返回值 | `() -> System.out.println("Hello")` |
| `Consumer<T>` | `accept(T)` | 接收参数无返回值 | `s -> System.out.println(s)` |
| `Supplier<T>` | `get()` | 无参数有返回值 | `() -> "Hello"` |
| `Function<T,R>` | `apply(T)` | 接收参数有返回值 | `s -> s.length()` |
| `Predicate<T>` | `test(T)` | 接收参数返回布尔值 | `s -> s.isEmpty()` |
| `BiFunction<T,U,R>` | `apply(T,U)` | 接收两参数有返回值 | `(a, b) -> a + b` |
| `BiConsumer<T,U>` | `accept(T,U)` | 接收两参数无返回值 | `(k, v) -> System.out.println(k + "=" + v)` |

### 6. 自定义函数式接口

```java
@FunctionalInterface  // 确保接口只有一个抽象方法
interface MathOperation {
    int operate(int a, int b);
    
    // 可以有默认方法
    default void description() {
        System.out.println("数学运算接口");
    }
}

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        // 加法实现
        MathOperation add = (a, b) -> a + b;
        System.out.println("2 + 3 = " + add.operate(2, 3));
        
        // 乘法实现
        MathOperation multiply = (a, b) -> a * b;
        System.out.println("2 * 3 = " + multiply.operate(2, 3));
    }
}
```

### 7. Lambda 表达式的进阶用法

#### 7.1 变量捕获

Lambda 可以访问外部的 final 或"实际上的 final"变量：

```java
String prefix = "User: ";  // 实际上的 final 变量
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

names.forEach(name -> {
    // 可以访问外部变量 prefix
    System.out.println(prefix + name);
    
    // 错误：不能修改外部变量
    // prefix = "Person: ";
});
```

#### 7.2 复合 Lambda 操作

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

int result = numbers.stream()
    .filter(n -> n % 2 == 0)       // 过滤偶数
    .map(n -> n * n)               // 平方
    .reduce(0, Integer::sum);      // 求和

System.out.println(result);  // 56 (4 + 16 + 36)
```

#### 7.3 并行流处理

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// 串行流
long serialTime = System.currentTimeMillis();
int serialSum = numbers.stream()
    .filter(n -> n % 2 == 0)
    .mapToInt(n -> {
        try { Thread.sleep(100); } catch (Exception e) {}
        return n * n;
    })
    .sum();
System.out.println("串行耗时: " + (System.currentTimeMillis() - serialTime));

// 并行流
long parallelTime = System.currentTimeMillis();
int parallelSum = numbers.parallelStream()
    .filter(n -> n % 2 == 0)
    .mapToInt(n -> {
        try { Thread.sleep(100); } catch (Exception e) {}
        return n * n;
    })
    .sum();
System.out.println("并行耗时: " + (System.currentTimeMillis() - parallelTime));
```

### 8. Lambda 表达式最佳实践

1. **保持简洁**：Lambda 适合简短的逻辑，复杂逻辑应提取为方法
2. **使用方法引用**：当 Lambda 只是调用一个方法时，使用方法引用
3. **避免副作用**：Lambda 应该是纯函数，避免修改外部状态
4. **使用标准函数式接口**：优先使用 `java.util.function` 包中的接口
5. **注意异常处理**：Lambda 中的异常需要在 Lambda 内部处理

## 综合应用示例

结合 ThreadLocal 和 Lambda 表达式实现用户上下文管理：

```java
public class UserContext {
    private String userId;
    private String username;
    
    // 构造器、getter、setter 省略
}

public class UserContextManager {
    private static final ThreadLocal<UserContext> USER_CONTEXT = ThreadLocal.withInitial(() -> new UserContext());
    
    public static UserContext get() {
        return USER_CONTEXT.get();
    }
    
    public static void set(UserContext context) {
        USER_CONTEXT.set(context);
    }
    
    public static void clear() {
        USER_CONTEXT.remove();
    }
    
    // 使用 Lambda 封装上下文操作
    public static <R> R withUser(UserContext userContext, Supplier<R> operation) {
        UserContext oldContext = get();
        try {
            set(userContext);
            return operation.get();
        } finally {
            set(oldContext);
        }
    }
}

// 使用示例
public class Application {
    public static void main(String[] args) {
        UserContext alice = new UserContext();
        alice.setUserId("001");
        alice.setUsername("Alice");
        
        // 使用 Lambda 在特定用户上下文中执行操作
        String result = UserContextManager.withUser(alice, () -> {
            // 在这个 Lambda 中，当前线程的用户上下文是 Alice
            UserContext current = UserContextManager.get();
            return "操作由用户 " + current.getUsername() + " 执行";
        });
        
        System.out.println(result);  // 输出：操作由用户 Alice 执行
    }
}
```

## 总结

### ThreadLocal

- **核心功能**：为每个线程提供独立的数据副本
- **主要方法**：`set()`、`get()`、`remove()`
- **关键注意点**：防止内存泄漏，务必在线程任务结束时调用 `remove()`
- **常见应用**：用户上下文管理、数据库连接管理、请求追踪

### Lambda 表达式

- **核心功能**：简洁地表示匿名函数，支持函数式编程
- **基本语法**：`(参数) -> { 代码块 }`
- **常见应用**：线程创建、集合操作（排序、过滤、映射）、事件处理
- **进阶特性**：方法引用、变量捕获、流操作

掌握这两个强大的工具，能够显著提高 Java 开发效率和代码质量！
