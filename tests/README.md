# java - 基础学习

项目笔记
访问链接 https://www.yuque.com/g/fengjianqianmeng/il5m2w/collaborator/join?token=qqErM0LqlqpQsMD7&source=book_collaborator# 《默认知识库》

---
>枚举 异常 泛型 集合 常用类
# Java 基础知识-- 常用知识

## 1. 枚举（Enum）

### 1.1 什么是枚举？
枚举（Enum）是 Java 中的一种特殊类，专门用来表示一组固定的常量。想象一下生活中固定的选项，比如一年有 12 个月、一周有 7 天、颜色有红绿蓝，这些值不会轻易改变。Java 用枚举来管理这类数据，让代码更安全、更清晰。

- **通俗比喻**：枚举就像一个菜单，你只能从中挑选，不能随便加菜。这样就不会点到“空气”这种不存在的选项。
- **实际用途**：比如定义游戏中的状态（开始、暂停、结束）或者订单状态（待支付、已支付、已发货）。

### 1.2 为什么要用枚举？
枚举不是随便设计的，它有几个大优点：
- **安全性**：限制变量只能取预定义的值。比如你定义了 `Day.MONDAY`，就不会不小心赋值为 `8`（星期八不存在）。
- **可读性**：用 `Day.MONDAY` 比用数字 `1` 或字符串 `"Monday"` 更直观，别人一看就懂。
- **功能强大**：枚举不仅仅是常量，它还能有属性、方法，甚至实现接口，比普通的常量灵活得多。

### 1.3 基本用法
定义一个枚举很简单，就像列出一堆选项：
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```
使用时就像调用一个类的对象：
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Day today = Day.MONDAY;  // 今天是星期一
        System.out.println("今天是: " + today);  // 输出: 今天是: MONDAY
    }
}
```

### 1.4 枚举的高级特性
枚举不像普通常量那么简单，它可以有自己的属性、构造器和方法。不过要注意，枚举的构造器必须是私有的（默认就是 private），因为我们不希望外部随便创建枚举对象。

**示例：带属性和方法的枚举**
```java
enum Color {
    RED("红色", 1), GREEN("绿色", 2), BLUE("蓝色", 3);  // 每个枚举值后面跟参数

    private String description;  // 属性：描述
    private int code;           // 属性：编号

    // 构造器，初始化属性
    Color(String description, int code) {
        this.description = description;
        this.code = code;
    }

    // 获取属性的方法
    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
```
**使用示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Color myColor = Color.RED;
        System.out.println("颜色描述: " + myColor.getDescription());  // 输出: 颜色描述: 红色
        System.out.println("颜色编号: " + myColor.getCode());         // 输出: 颜色编号: 1

        Color green = Color.GREEN;
        System.out.println("绿色描述: " + green.getDescription());    // 输出: 绿色描述: 绿色
        System.out.println("绿色编号: " + green.getCode());          // 输出: 绿色编号: 2
    }
}
```

### 1.5 枚举自带的方法
Java 为枚举内置了一些超级好用的方法：
- **`values()`**：返回所有枚举值的数组。
- **`valueOf(String name)`**：根据名字（字符串）返回对应的枚举值，名字必须完全匹配。
- **`ordinal()`**：返回枚举值的顺序，从 0 开始计数。

**示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        // 用 values() 遍历所有枚举值
        System.out.println("所有星期:");
        for (Day day : Day.values()) {
            System.out.println(day + " 的序号是: " + day.ordinal());
        }
        // 输出:
        // MONDAY 的序号是: 0
        // TUESDAY 的序号是: 1
        // ...

        // 用 valueOf() 获取指定枚举值
        Day friday = Day.valueOf("FRIDAY");
        System.out.println("星期五的序号: " + friday.ordinal());  // 输出: 星期五的序号: 4
    }
}
```

### 1.6 枚举在 switch 语句中的应用
枚举和 `switch` 是绝配，因为枚举值是固定的，非常适合做条件判断。
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Day today = Day.MONDAY;
        switch (today) {
            case MONDAY:
                System.out.println("周一，新的开始！");
                break;
            case FRIDAY:
                System.out.println("周五，快放假啦！");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("周末，休息一下！");
                break;
            default:
                System.out.println("工作日，继续努力！");
        }
    }
}
```

### 1.7 枚举与接口
枚举还能实现接口，变得更灵活：
```java
interface Describable {
    String getDescription();
}

enum Color implements Describable {
    RED("红色"), GREEN("绿色"), BLUE("蓝色");

    private String description;

    Color(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Color color = Color.BLUE;
        System.out.println(color.getDescription());  // 输出: 蓝色
    }
}
```

### 1.8 枚举的实际场景
- **游戏开发**：用枚举表示角色状态（`ALIVE`, `DEAD`, `INJURED`）。
- **表单选项**：用枚举定义性别（`MALE`, `FEMALE`, `OTHER`）。
- **配置管理**：用枚举定义环境（`DEV`, `TEST`, `PROD`）。

---

## 2. 异常（Exception）

### 2.1 什么是异常？
异常是程序运行时出现的错误或意外情况，比如：
- 你想打开一个文件，但文件不存在。
- 你不小心让程序除以零。
- 网络断了，连不上服务器。

Java 用异常处理机制来应对这些问题，让程序不至于直接崩溃，而是优雅地处理错误。

- **比喻**：异常就像路上突然出现的坑，异常处理就是修路的工具，能填坑或者绕过去。

### 2.2 异常的分类
Java 把异常分成两大类：
- **受检异常（Checked Exception）**：
    - 在编译时就必须处理（用 `try-catch` 或 `throws`）。
    - 比如 `IOException`（文件读写错误）、`SQLException`（数据库错误）。
    - 通常是外部因素导致的，程序员控制不了。
- **非受检异常（Unchecked Exception）**：
    - 运行时才发现，不强制处理。
    - 比如 `NullPointerException`（空指针）、`ArrayIndexOutOfBoundsException`（数组越界）。
    - 通常是程序员的代码问题。

### 2.3 异常处理的关键字
Java 提供了 5 个关键字来处理异常：
- **`try`**：放可能出错的代码。
- **`catch`**：捕获异常并处理。
- **`finally`**：无论有没有异常，都会执行（比如关闭文件）。
- **`throw`**：手动抛出异常。
- **`throws`**：在方法上声明可能会抛出的异常。

### 2.4 基本异常处理示例
**简单例子：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;  // 除以零，会抛出 ArithmeticException
            System.out.println("结果: " + result);  // 这行不会执行
        } catch (ArithmeticException e) {
            System.out.println("错误: 不能除以零！");
        } finally {
            System.out.println("程序结束，无论如何我都会执行！");
        }
    }
}
```
**输出：**
```
错误: 不能除以零！
程序结束，无论如何我都会执行！
```

**多重 catch：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);  // 数组越界
            int result = 10 / 0;            // 除以零
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界了！");
        } catch (ArithmeticException e) {
            System.out.println("不能除以零！");
        } finally {
            System.out.println("清理工作完成！");
        }
    }
}
```
**输出：**
```
数组越界了！
清理工作完成！
```

### 2.5 自定义异常
你可以定义自己的异常类，通常继承 `Exception` 或 `RuntimeException`。
```java
class MyException extends Exception {
    public MyException(String message) {
        super(message);  // 调用父类的构造器，设置错误信息
    }
}

public class com.cpystu.threaduse.Main {
    public static void checkAge(int age) throws MyException {
        if (age < 18) {
            throw new MyException("年龄太小，不能参加活动！");
        } else {
            System.out.println("欢迎参加！");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(15);
        } catch (MyException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
}
```
**输出：**
```
错误: 年龄太小，不能参加活动！
```

### 2.6 异常的传播
如果一个方法抛出异常但没处理，异常会传给调用它的方法，直到被捕获或传到 `main` 方法（程序崩溃）。
```java
public class com.cpystu.threaduse.Main {
    public static void method1() throws Exception {
        throw new Exception("出错了！");
    }

    public static void method2() throws Exception {
        method1();  // 异常从这里传上来
    }

    public static void main(String[] args) {
        try {
            method2();
        } catch (Exception e) {
            System.out.println(e.getMessage());  // 输出: 出错了！
        }
    }
}
```

### 2.7 异常的实际场景
- **文件操作**：读取文件时捕获 `FileNotFoundException`。
- **网络编程**：连接服务器时处理 `SocketException`。
- **用户输入**：检查输入格式，抛出自定义异常。

### 2.8 最佳实践
- **具体捕获**：用 `catch (NullPointerException e)` 而不是 `catch (Exception e)`，精确处理。
- **避免滥用**：不要用异常代替 `if-else` 来控制流程。
- **资源清理**：在 `finally` 中关闭文件、数据库连接等。

---

## 3. 泛型（Generics）

### 3.1 什么是泛型？
泛型是 Java 的一种机制，允许你在定义类、接口或方法时使用“类型参数”，让代码能适应多种数据类型，同时保证类型安全。

- **比喻**：泛型就像一个万能模具，你可以往里面倒不同的材料（类型），做出不同形状的东西。
- **目的**：避免类型转换的麻烦，防止运行时出错。

### 3.2 泛型的好处
- **类型安全**：编译时就检查类型错误，比如不能往 `List<String>` 里加整数。
- **代码复用**：一个类可以处理多种类型，不用为每种类型写一个类。
- **简化代码**：不用手动转换类型，代码更干净。

### 3.3 泛型类的定义
**示例：**
```java
class Box<T> {  // T 是类型参数，可以是任意名字
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();  // T 被指定为 String
        stringBox.setItem("Hello");
        String value = stringBox.getItem();   // 无需强制转换
        System.out.println(value);            // 输出: Hello

        Box<Integer> intBox = new Box<>();    // T 被指定为 Integer
        intBox.setItem(123);
        int number = intBox.getItem();
        System.out.println(number);           // 输出: 123
    }
}
```

### 3.4 泛型方法
不仅类可以用泛型，方法也可以单独用泛型：
```java
public class com.cpystu.threaduse.Main {
    public static <T> void printArray(T[] array) {  // T 是方法级别的泛型参数
        for (T element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4};
        printArray(numbers);  // 输出: 1 2 3 4

        String[] words = {"Hello", "World"};
        printArray(words);    // 输出: Hello World
    }
}
```

### 3.5 泛型的通配符
泛型有时需要更灵活的类型匹配，用通配符：
- **`?`**：表示未知类型，只能读，不能写。
- **`? extends T`**：上界，表示 T 或 T 的子类。
- **`? super T`**：下界，表示 T 或 T 的父类。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void printList(List<?> list) {  // 接受任意类型的 List
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("A", "B", "C");
        printList(stringList);  // 输出: A B C

        List<Integer> intList = Arrays.asList(1, 2, 3);
        printList(intList);     // 输出: 1 2 3
    }
}
```

### 3.6 泛型的限制
- **不能用基本类型**：`Box<int>` 是错的，必须用 `Box<Integer>`。
- **不能创建泛型数组**：`T[] array = new T[10];` 不行，得用 `Object[]` 再转换。
- **静态方法不能直接用类的泛型参数**：得单独定义。

### 3.7 泛型的实际场景
- **集合类**：`List<String>`、`Map<Integer, String>`。
- **工具类**：写一个通用的比较器或打印器。
- **API 设计**：让方法支持多种类型输入。

---

## 4. 常用类

Java 提供了很多现成的类，帮我们快速解决问题。以下是最常用的几个：

### 4.1 String
- **特点**：字符串是不可变的，改动会创建新对象。
- **常用方法：**
    - `length()`：字符串长度。
    - `charAt(int index)`：指定位置的字符。
    - `substring(int begin, int end)`：截取子串。
    - `equals(Object obj)`：比较内容。
    - `split(String regex)`：按规则分割。
    - `toUpperCase()` / `toLowerCase()`：大小写转换。

**示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        String str = "Hello, Java!";
        System.out.println("长度: " + str.length());          // 输出: 12
        System.out.println("第3个字符: " + str.charAt(2));   // 输出: l
        System.out.println("子串: " + str.substring(0, 5));  // 输出: Hello
        System.out.println("大写: " + str.toUpperCase());    // 输出: HELLO, JAVA!
    }
}
```

### 4.2 StringBuilder / StringBuffer
- **区别**：
    - `StringBuilder`：非线程安全，速度快。
    - `StringBuffer`：线程安全，速度稍慢。
- **常用方法**：`append()`、`insert()`、`delete()`、`reverse()`。

**示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");      // 添加
        sb.insert(5, ",");        // 插入
        sb.delete(0, 2);          // 删除
        System.out.println(sb);   // 输出: llo, World
        sb.reverse();             // 反转
        System.out.println(sb);   // 输出: dlroW ,oll
    }
}
```

### 4.3 Math
- **用途**：数学计算。
- **常用方法**：
    - `abs()`：绝对值。
    - `sqrt()`：平方根。
    - `pow(a, b)`：a 的 b 次方。
    - `random()`：0.0 到 1.0 的随机数。

**示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        System.out.println(Math.abs(-5));      // 输出: 5
        System.out.println(Math.sqrt(16));     // 输出: 4.0
        System.out.println(Math.pow(2, 3));    // 输出: 8.0
        System.out.println(Math.random());     // 输出: 随机数，如 0.723
    }
}
```

### 4.4 Date / Calendar
- **`Date`**：表示当前时间。
- **`Calendar`**：日期计算。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("现在: " + date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);  // 加一天
        System.out.println("明天: " + cal.getTime());
    }
}
```

### 4.5 Arrays
- **用途**：操作数组。
- **常用方法**：`sort()`、`binarySearch()`、`copyOf()`。

**示例：**
```java
import java.util.Arrays;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1};
        Arrays.sort(numbers);                          // 排序
        System.out.println(Arrays.toString(numbers));  // 输出: [1, 2, 5, 8]
        System.out.println(Arrays.binarySearch(numbers, 5));  // 输出: 2
    }
}
```

### 4.6 System
- **常用方法**：
    - `currentTimeMillis()`：当前时间戳。
    - `exit(0)`：退出程序。

**示例：**
```java
public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("时间戳: " + start);
    }
}
```

---

## 5. 集合（Collections）

### 5.1 什么是集合？
集合是用来存放多个对象的容器，比数组更灵活。Java 的集合框架包括 **List**、**Set**、**Map** 三种主要类型。

### 5.2 List
- **特点**：有序、可重复。
- **实现类**：
    - `ArrayList`：基于数组，查询快。
    - `LinkedList`：基于链表，增删快。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");  // 可以重复
        System.out.println(list);         // 输出: [Apple, Banana, Apple]
        System.out.println(list.get(1));  // 输出: Banana
    }
}
```

### 5.3 Set
- **特点**：无序、不可重复。
- **实现类**：
    - `HashSet`：哈希表，速度快。
    - `TreeSet`：红黑树，自动排序。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // 重复的不会加进去
        System.out.println(set);  // 输出: [Banana, Apple]
    }
}
```

### 5.4 Map
- **特点**：键值对，键唯一。
- **实现类**：
    - `HashMap`：哈希表。
    - `TreeMap`：红黑树，键排序。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Apple", 3);  // 键重复会覆盖
        System.out.println(map);        // 输出: {Apple=3, Banana=2}
        System.out.println(map.get("Banana"));  // 输出: 2
    }
}
```

### 5.5 集合的遍历
**List：**
```java
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String item : list) {
    System.out.println(item);  // 输出: A B C
}
```

**Set：**
```java
Set<String> set = new HashSet<>(Arrays.asList("A", "B", "C"));
for (String item : set) {
    System.out.println(item);  // 输出: A B C（顺序不定）
}
```

**Map：**
```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
// 输出:
// A: 1
// B: 2
```

### 5.6 集合与泛型
集合通常搭配泛型使用：
```java
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123);  // 编译错误，类型安全
```

### 5.7 集合工具类
`Collections` 类提供很多实用方法：
- `sort()`：排序。
- `shuffle()`：打乱顺序。
- `reverse()`：反转。

**示例：**
```java
import java.util.*;

public class com.cpystu.threaduse.Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4));
        Collections.sort(list);
        System.out.println(list);  // 输出: [1, 3, 4]
        Collections.shuffle(list);
        System.out.println(list);  // 输出: 随机顺序，如 [3, 4, 1]
    }
}
```

---

## 总结
- **枚举**：管理固定值，安全又清晰。
- **异常**：处理错误，让程序更健壮。
- **泛型**：类型安全，代码复用。
- **常用类**：现成的工具，省时省力。
- **集合**：灵活管理多个对象。
