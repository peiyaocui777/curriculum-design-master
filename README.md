
# 📚 Java 基础核心知识导航

> 最后更新：2025-04-08 | 版本：v2.1.3

🔗 项目笔记同步访问 👉 [📒 默认知识库@语雀](https://www.yuque.com/g/fengjianqianmeng/il5m2w/collaborator/join?token=qqErM0LqlqpQsMD7&source=book_collaborator)

<details>
<summary>🔍 展开完整目录（点击可展开，Ctrl+F 搜索关键词）</summary>

## 📂 目录
- [1. 枚举（Enum）](#1-枚举enum)
  - [1.1 什么是枚举？](#11-什么是枚举)
  - [1.2 为什么要用枚举？](#12-为什么要用枚举)
  - [1.3 基本用法](#13-基本用法)
  - [1.4 枚举的高级特性](#14-枚举的高级特性)
  - [1.5 枚举自带的方法](#15-枚举自带的方法)
  - [1.6 枚举在 switch 语句中的应用](#16-枚举在-switch-语句中的应用)
  - [1.7 枚举与接口](#17-枚举与接口)
  - [1.8 枚举的实际场景](#18-枚举的实际场景)
  
- [2. 异常（Exception）](#2-异常exception)
  - [2.1 什么是异常？](#21-什么是异常)
  - [2.2 异常的分类](#22-异常的分类)
  - [2.3 异常处理的关键字](#23-异常处理的关键字)
  - [2.4 基本异常处理示例](#24-基本异常处理示例)
  - [2.5 自定义异常](#25-自定义异常)
  - [2.6 异常的传播](#26-异常的传播)
  - [2.7 异常的实际场景](#27-异常的实际场景)
  - [2.8 最佳实践](#28-最佳实践)

- [3. 泛型（Generics）](#3-泛型generics)
  - [3.1 什么是泛型？](#31-什么是泛型)
  - [3.2 泛型的好处](#32-泛型的好处)
  - [3.3 泛型类的定义](#33-泛型类的定义)
  - [3.4 泛型方法](#34-泛型方法)
  - [3.5 泛型的通配符](#35-泛型的通配符)
  - [3.6 泛型的限制](#36-泛型的限制)
  - [3.7 泛型的实际场景](#37-泛型的实际场景)

- [4. 常用类](#4-常用类)
  - [4.1 String](#41-string)
  - [4.2 StringBuilder / StringBuffer](#42-stringbuilder--stringbuffer)
  - [4.3 Math](#43-math)
  - [4.4 Date / Calendar](#44-date--calendar)
  - [4.5 Arrays](#45-arrays)
  - [4.6 System](#46-system)

- [5. 集合（Collections）](#5-集合collections)
  - [5.1 什么是集合？](#51-什么是集合)
  - [5.2 List](#52-list)
  - [5.3 Set](#53-set)
  - [5.4 Map](#54-map)
  - [5.5 集合的遍历](#55-集合的遍历)
  - [5.6 集合与泛型](#56-集合与泛型)
  - [5.7 集合工具类](#57-集合工具类)

- [6. 反射（Reflection）](#6-反射reflection)
  - [6.1 什么是反射？](#61-什么是反射)
  - [6.2 反射的核心类](#62-反射的核心类)
  - [6.3 获取Class对象](#63-获取class对象)
  - [6.4 反射创建对象](#64-反射创建对象)
  - [6.5 反射访问字段](#65-反射访问字段)
  - [6.6 反射调用方法](#66-反射调用方法)
  - [6.7 反射获取类信息](#67-反射获取类信息)
  - [6.8 反射的应用场景](#68-反射的应用场景)
  - [6.9 反射的优缺点](#69-反射的优缺点)

- [7. 数据库操作（JDBC）](#7-数据库操作jdbc)
  - [7.1 什么是JDBC？](#71-什么是jdbc)
  - [7.2 JDBC的核心组件](#72-jdbc的核心组件)
  - [7.3 建立数据库连接](#73-建立数据库连接)
  - [7.4 执行SQL语句](#74-执行sql语句)
  - [7.5 处理ResultSet](#75-处理resultset)
  - [7.6 事务管理](#76-事务管理)
  - [7.7 批处理操作](#77-批处理操作)
  - [7.8 数据库连接池](#78-数据库连接池)
  - [7.9 DAO设计模式](#79-dao设计模式)
  - [7.10 最佳实践](#710-最佳实践)
</details>

---

---

## 1. 枚举（Enum）<a id="1-枚举enum"></a>

### 1.1 什么是枚举？<a id="11-什么是枚举"></a>
枚举（Enum）是 Java 中的一种特殊类，专门用来表示一组固定的常量。想象一下生活中固定的选项，比如一年有 12 个月、一周有 7 天、颜色有红绿蓝，这些值不会轻易改变。Java 用枚举来管理这类数据，让代码更安全、更清晰。

```java
enum Day { MONDAY, TUESDAY, WEDNESDAY... }  // ← 点击目录体验跳转
```

- **通俗比喻**：枚举就像一个菜单，你只能从中挑选，不能随便加菜。这样就不会点到"空气"这种不存在的选项。
- **实际用途**：比如定义游戏中的状态（开始、暂停、结束）或者订单状态（待支付、已支付、已发货）。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.2 为什么要用枚举？<a id="12-为什么要用枚举"></a>
枚举不是随便设计的，它有几个大优点：
- **安全性**：限制变量只能取预定义的值。比如你定义了 `Day.MONDAY`，就不会不小心赋值为 `8`（星期八不存在）。
- **可读性**：用 `Day.MONDAY` 比用数字 `1` 或字符串 `"Monday"` 更直观，别人一看就懂。
- **功能强大**：枚举不仅仅是常量，它还能有属性、方法，甚至实现接口，比普通的常量灵活得多。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.3 基本用法<a id="13-基本用法"></a>
定义一个枚举很简单，就像列出一堆选项：
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```
使用时就像调用一个类的对象：
```java
public class Main {
    public static void main(String[] args) {
        Day today = Day.MONDAY;  // 今天是星期一
        System.out.println("今天是: " + today);  // 输出: 今天是: MONDAY
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.4 枚举的高级特性<a id="14-枚举的高级特性"></a>
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.5 枚举自带的方法<a id="15-枚举自带的方法"></a>
Java 为枚举内置了一些超级好用的方法：
- **`values()`**：返回所有枚举值的数组。
- **`valueOf(String name)`**：根据名字（字符串）返回对应的枚举值，名字必须完全匹配。
- **`ordinal()`**：返回枚举值的顺序，从 0 开始计数。

**示例：**
```java
public class Main {
    public static void main(String[] args) {
        // 用 values() 遍历所有枚举值
        System.out.println("所有星期:");
        for (Day day : Day.values()) {
            System.out.println(day + " 的序号是: " + day.ordinal());
        }
        
        // 用 valueOf() 获取指定枚举值
        Day friday = Day.valueOf("FRIDAY");
        System.out.println("星期五的序号: " + friday.ordinal());  // 输出: 星期五的序号: 4
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.6 枚举在 switch 语句中的应用<a id="16-枚举在-switch-语句中的应用"></a>
枚举和 `switch` 是绝配，因为枚举值是固定的，非常适合做条件判断。
```java
public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.7 枚举与接口<a id="17-枚举与接口"></a>
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

public class Main {
    public static void main(String[] args) {
        Color color = Color.BLUE;
        System.out.println(color.getDescription());  // 输出: 蓝色
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 1.8 枚举的实际场景<a id="18-枚举的实际场景"></a>
- **游戏开发**：用枚举表示角色状态（`ALIVE`, `DEAD`, `INJURED`）。
- **表单选项**：用枚举定义性别（`MALE`, `FEMALE`, `OTHER`）。
- **配置管理**：用枚举定义环境（`DEV`, `TEST`, `PROD`）。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 2. 异常（Exception）<a id="2-异常exception"></a>

### 2.1 什么是异常？<a id="21-什么是异常"></a>
异常是程序运行时出现的错误或意外情况，比如：
- 你想打开一个文件，但文件不存在。
- 你不小心让程序除以零。
- 网络断了，连不上服务器。

Java 用异常处理机制来应对这些问题，让程序不至于直接崩溃，而是优雅地处理错误。

- **比喻**：异常就像路上突然出现的坑，异常处理就是修路的工具，能填坑或者绕过去。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.2 异常的分类<a id="22-异常的分类"></a>
Java 把异常分成两大类：
- **受检异常（Checked Exception）**：
    - 在编译时就必须处理（用 `try-catch` 或 `throws`）。
    - 比如 `IOException`（文件读写错误）、`SQLException`（数据库错误）。
    - 通常是外部因素导致的，程序员控制不了。
- **非受检异常（Unchecked Exception）**：
    - 运行时才发现，不强制处理。
    - 比如 `NullPointerException`（空指针）、`ArrayIndexOutOfBoundsException`（数组越界）。
    - 通常是程序员的代码问题。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.3 异常处理的关键字<a id="23-异常处理的关键字"></a>
Java 提供了 5 个关键字来处理异常：
- **`try`**：放可能出错的代码。
- **`catch`**：捕获异常并处理。
- **`finally`**：无论有没有异常，都会执行（比如关闭文件）。
- **`throw`**：手动抛出异常。
- **`throws`**：在方法上声明可能会抛出的异常。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.4 基本异常处理示例<a id="24-基本异常处理示例"></a>
**简单例子：**
```java
public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.5 自定义异常<a id="25-自定义异常"></a>
你可以定义自己的异常类，通常继承 `Exception` 或 `RuntimeException`。
```java
class MyException extends Exception {
    public MyException(String message) {
        super(message);  // 调用父类的构造器，设置错误信息
    }
}

public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.6 异常的传播<a id="26-异常的传播"></a>
如果一个方法抛出异常但没处理，异常会传给调用它的方法，直到被捕获或传到 `main` 方法（程序崩溃）。
```java
public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.7 异常的实际场景<a id="27-异常的实际场景"></a>
- **文件操作**：读取文件时捕获 `FileNotFoundException`。
- **网络编程**：连接服务器时处理 `SocketException`。
- **用户输入**：检查输入格式，抛出自定义异常。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 2.8 最佳实践<a id="28-最佳实践"></a>
- **具体捕获**：用 `catch (NullPointerException e)` 而不是 `catch (Exception e)`，精确处理。
- **避免滥用**：不要用异常代替 `if-else` 来控制流程。
- **资源清理**：在 `finally` 中关闭文件、数据库连接等。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 3. 泛型（Generics）<a id="3-泛型generics"></a>

### 3.1 什么是泛型？<a id="31-什么是泛型"></a>
泛型是 Java 的一种机制，允许你在定义类、接口或方法时使用"类型参数"，让代码能适应多种数据类型，同时保证类型安全。

- **比喻**：泛型就像一个万能模具，你可以往里面倒不同的材料（类型），做出不同形状的东西。
- **目的**：避免类型转换的麻烦，防止运行时出错。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.2 泛型的好处<a id="32-泛型的好处"></a>
- **类型安全**：编译时就检查类型错误，比如不能往 `List<String>` 里加整数。
- **代码复用**：一个类可以处理多种类型，不用为每种类型写一个类。
- **简化代码**：不用手动转换类型，代码更干净。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.3 泛型类的定义<a id="33-泛型类的定义"></a>
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

public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.4 泛型方法<a id="34-泛型方法"></a>
不仅类可以用泛型，方法也可以单独用泛型：
```java
public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.5 泛型的通配符<a id="35-泛型的通配符"></a>
泛型有时需要更灵活的类型匹配，用通配符：
- **`?`**：表示未知类型，只能读，不能写。
- **`? extends T`**：上界，表示 T 或 T 的子类。
- **`? super T`**：下界，表示 T 或 T 的父类。

**示例：**
```java
import java.util.*;

public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.6 泛型的限制<a id="36-泛型的限制"></a>
- **不能用基本类型**：`Box<int>` 是错的，必须用 `Box<Integer>`。
- **不能创建泛型数组**：`T[] array = new T[10];` 不行，得用 `Object[]` 再转换。
- **静态方法不能直接用类的泛型参数**：得单独定义。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 3.7 泛型的实际场景<a id="37-泛型的实际场景"></a>
- **集合类**：`List<String>`、`Map<Integer, String>`。
- **工具类**：写一个通用的比较器或打印器。
- **API 设计**：让方法支持多种类型输入。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 4. 常用类<a id="4-常用类"></a>

### 4.1 String<a id="41-string"></a>
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
public class Main {
    public static void main(String[] args) {
        String str = "Hello, Java!";
        System.out.println("长度: " + str.length());          // 输出: 12
        System.out.println("第3个字符: " + str.charAt(2));   // 输出: l
        System.out.println("子串: " + str.substring(0, 5));  // 输出: Hello
        System.out.println("大写: " + str.toUpperCase());    // 输出: HELLO, JAVA!
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 4.2 StringBuilder / StringBuffer<a id="42-stringbuilder--stringbuffer"></a>
- **区别**：
    - `StringBuilder`：非线程安全，速度快。
    - `StringBuffer`：线程安全，速度稍慢。
- **常用方法**：`append()`、`insert()`、`delete()`、`reverse()`。

**示例：**
```java
public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 4.3 Math<a id="43-math"></a>
- **用途**：数学计算。
- **常用方法**：
    - `abs()`：绝对值。
    - `sqrt()`：平方根。
    - `pow(a, b)`：a 的 b 次方。
    - `random()`：0.0 到 1.0 的随机数。

**示例：**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Math.abs(-5));      // 输出: 5
        System.out.println(Math.sqrt(16));     // 输出: 4.0
        System.out.println(Math.pow(2, 3));    // 输出: 8.0
        System.out.println(Math.random());     // 输出: 随机数，如 0.723
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 4.4 Date / Calendar<a id="44-date--calendar"></a>
- **`Date`**：表示当前时间。
- **`Calendar`**：日期计算。

**示例：**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("现在: " + date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);  // 加一天
        System.out.println("明天: " + cal.getTime());
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 4.5 Arrays<a id="45-arrays"></a>
- **用途**：操作数组。
- **常用方法**：`sort()`、`binarySearch()`、`copyOf()`。

**示例：**
```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1};
        Arrays.sort(numbers);                          // 排序
        System.out.println(Arrays.toString(numbers));  // 输出: [1, 2, 5, 8]
        System.out.println(Arrays.binarySearch(numbers, 5));  // 输出: 2
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 4.6 System<a id="46-system"></a>
- **常用方法**：
    - `currentTimeMillis()`：当前时间戳。
    - `exit(0)`：退出程序。

**示例：**
```java
public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("时间戳: " + start);
        
        // 测量代码执行时间
        for(int i = 0; i < 1000000; i++) {
            // 执行一些操作
        }
        
        long end = System.currentTimeMillis();
        System.out.println("执行耗时: " + (end - start) + "毫秒");
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 5. 集合（Collections）<a id="5-集合collections"></a>

### 5.1 什么是集合？<a id="51-什么是集合"></a>
集合是用来存放多个对象的容器，比数组更灵活。Java 的集合框架包括 **List**、**Set**、**Map** 三种主要类型。

![集合框架图](https://via.placeholder.com/600x300?text=Java+Collections+Framework)

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.2 List<a id="52-list"></a>
- **特点**：有序、可重复。
- **实现类**：
    - `ArrayList`：基于数组，查询快。
    - `LinkedList`：基于链表，增删快。

**示例：**
```java
import java.util.*;

public class Main {
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

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.3 Set<a id="53-set"></a>
- **特点**：无序、不可重复。
- **实现类**：
    - `HashSet`：哈希表，速度快。
    - `TreeSet`：红黑树，自动排序。

**示例：**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // 重复的不会加进去
        System.out.println(set);  // 输出: [Banana, Apple]
        
        // TreeSet 自动排序
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Cherry");
        System.out.println(treeSet);  // 输出: [Apple, Banana, Cherry]
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.4 Map<a id="54-map"></a>
- **特点**：键值对，键唯一。
- **实现类**：
    - `HashMap`：哈希表。
    - `TreeMap`：红黑树，键排序。

**示例：**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Apple", 3);  // 键重复会覆盖
        System.out.println(map);        // 输出: {Apple=3, Banana=2}
        System.out.println(map.get("Banana"));  // 输出: 2
        
        // 检查键是否存在
        if (map.containsKey("Cherry")) {
            System.out.println("找到了Cherry");
        } else {
            System.out.println("没有Cherry");  // 输出这行
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.5 集合的遍历<a id="55-集合的遍历"></a>
**List：**
```java
// 方法1：for-each 循环
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String item : list) {
    System.out.println(item);  // 输出: A B C
}

// 方法2：迭代器
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// 方法3：Java 8 Stream API
list.forEach(System.out::println);
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

// 方法1：遍历键值对
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// 方法2：只遍历键
for (String key : map.keySet()) {
    System.out.println(key + ": " + map.get(key));
}

// 方法3：只遍历值
for (Integer value : map.values()) {
    System.out.println(value);
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.6 集合与泛型<a id="56-集合与泛型"></a>
集合通常搭配泛型使用：
```java
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123);  // 编译错误，类型安全

// 泛型嵌套
Map<Integer, List<String>> complexMap = new HashMap<>();
List<String> valueList = new ArrayList<>();
valueList.add("Item 1");
valueList.add("Item 2");
complexMap.put(1, valueList);

// 访问嵌套集合
List<String> retrievedList = complexMap.get(1);
System.out.println(retrievedList.get(0));  // 输出: Item 1
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 5.7 集合工具类<a id="57-集合工具类"></a>
`Collections` 类提供很多实用方法：
- `sort()`：排序。
- `shuffle()`：打乱顺序。
- `reverse()`：反转。
- `binarySearch()`：二分查找。
- `max()` / `min()`：找最大/最小值。
- `synchronizedList()` / `synchronizedMap()`：线程安全包装。

**示例：**
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 2));
        
        // 排序
        Collections.sort(list);
        System.out.println("排序后: " + list);  // 输出: [1, 2, 3, 4]
        
        // 打乱
        Collections.shuffle(list);
        System.out.println("打乱后: " + list);  // 输出: 随机顺序
        
        // 反转
        Collections.reverse(list);
        System.out.println("反转后: " + list);
        
        // 查找最大值
        int max = Collections.max(list);
        System.out.println("最大值: " + max);
        
        // 线程安全包装
        List<Integer> syncList = Collections.synchronizedList(list);
        // 现在 syncList 是线程安全的
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 6. 反射（Reflection）<a id="6-反射reflection"></a>

### 6.1 什么是反射？<a id="61-什么是反射"></a>
反射是Java的一种强大机制，允许程序在运行时检查和操作类、接口、方法和字段，即使在编译时不知道它们的具体信息。

- **通俗比喻**：反射就像是给程序装了一面"镜子"，能够让程序自己照镜子，看清自己的结构和能力。
- **实际用途**：框架开发、插件机制、动态代理等高级应用场景。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.2 反射的核心类<a id="62-反射的核心类"></a>
Java反射API主要包含以下核心类：

- **`Class`**：代表类的实体，包含类的所有信息。
- **`Field`**：代表类的成员变量（字段）。
- **`Method`**：代表类的方法。
- **`Constructor`**：代表类的构造方法。
- **`Modifier`**：提供访问修饰符信息。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.3 获取Class对象<a id="63-获取class对象"></a>
获取Class对象有三种主要方式：

```java
// 方式1：通过对象的getClass()方法
String str = "Hello";
Class<?> cls1 = str.getClass();

// 方式2：通过类的class属性
Class<?> cls2 = String.class;

// 方式3：通过Class.forName()方法（最常用）
try {
    Class<?> cls3 = Class.forName("java.lang.String");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.4 反射创建对象<a id="64-反射创建对象"></a>
通过反射可以动态创建类的实例：

```java
public class Main {
    public static void main(String[] args) {
        try {
            // 获取Class对象
            Class<?> cls = Class.forName("java.util.ArrayList");
            
            // 创建实例（调用无参构造器）
            Object list = cls.newInstance();  // 已过时，推荐使用下面的方式
            
            // 推荐的创建实例方式
            Object list2 = cls.getDeclaredConstructor().newInstance();
            
            System.out.println("创建的对象: " + list2);
            System.out.println("对象类型: " + list2.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.5 反射访问字段<a id="65-反射访问字段"></a>
通过反射可以获取和修改类的字段，包括私有字段：

```java
class Person {
    public String name = "Default";
    private int age = 0;
}

public class Main {
    public static void main(String[] args) {
        try {
            Person p = new Person();
            Class<?> cls = p.getClass();
            
            // 获取公共字段
            Field nameField = cls.getField("name");
            System.out.println("原始name值: " + nameField.get(p));
            
            // 修改字段值
            nameField.set(p, "张三");
            System.out.println("修改后name值: " + p.name);  // 输出: 张三
            
            // 获取私有字段
            Field ageField = cls.getDeclaredField("age");
            // 设置可访问
            ageField.setAccessible(true);
            System.out.println("原始age值: " + ageField.get(p));
            
            // 修改私有字段
            ageField.set(p, 25);
            System.out.println("修改后age值: " + ageField.get(p));  // 输出: 25
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.6 反射调用方法<a id="66-反射调用方法"></a>
通过反射可以调用类的方法，包括私有方法：

```java
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    private String secret(String message) {
        return "Secret: " + message;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calc = new Calculator();
            Class<?> cls = calc.getClass();
            
            // 调用公共方法
            Method addMethod = cls.getMethod("add", int.class, int.class);
            Object result = addMethod.invoke(calc, 10, 20);
            System.out.println("10 + 20 = " + result);  // 输出: 10 + 20 = 30
            
            // 调用私有方法
            Method secretMethod = cls.getDeclaredMethod("secret", String.class);
            secretMethod.setAccessible(true);
            Object secretResult = secretMethod.invoke(calc, "Hello");
            System.out.println(secretResult);  // 输出: Secret: Hello
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.7 反射获取类信息<a id="67-反射获取类信息"></a>
反射可以用来分析类的结构：

```java
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("java.util.ArrayList");
            
            // 获取类名
            System.out.println("类名: " + cls.getName());
            System.out.println("简单类名: " + cls.getSimpleName());
            
            // 获取修饰符
            int modifiers = cls.getModifiers();
            System.out.println("是否是公共类: " + Modifier.isPublic(modifiers));
            
            // 获取父类
            Class<?> superClass = cls.getSuperclass();
            System.out.println("父类: " + superClass.getName());
            
            // 获取实现的接口
            Class<?>[] interfaces = cls.getInterfaces();
            System.out.println("实现的接口:");
            for (Class<?> i : interfaces) {
                System.out.println("  " + i.getName());
            }
            
            // 获取所有公共方法
            Method[] methods = cls.getMethods();
            System.out.println("公共方法数量: " + methods.length);
            
            // 获取所有字段
            Field[] fields = cls.getDeclaredFields();
            System.out.println("字段数量: " + fields.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.8 反射的应用场景<a id="68-反射的应用场景"></a>
- **框架开发**：Spring、Hibernate等框架大量使用反射。
- **注解处理**：运行时处理注解信息。
- **动态代理**：创建接口的实现类。
- **插件机制**：动态加载和使用插件。
- **测试工具**：访问私有方法和字段进行测试。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 6.9 反射的优缺点<a id="69-反射的优缺点"></a>
**优点：**
- 灵活性高，可以在运行时动态操作类和对象。
- 可以访问私有成员，突破访问限制。
- 是很多框架的基础技术。

**缺点：**
- 性能开销大，比直接调用慢。
- 破坏了封装性，可能导致安全问题。
- 代码可读性降低，难以维护。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 7. 数据库操作（JDBC）<a id="7-数据库操作jdbc"></a>

### 7.1 什么是JDBC？<a id="71-什么是jdbc"></a>
JDBC（Java Database Connectivity）是Java标准的数据库访问API，允许Java程序连接和操作各种关系型数据库。

- **通俗比喻**：JDBC就像是Java与数据库之间的"翻译官"，负责将Java的命令翻译成数据库能理解的语言，再把数据库的回应翻译给Java。
- **实际用途**：连接数据库、执行SQL语句、处理查询结果。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.2 JDBC的核心组件<a id="72-jdbc的核心组件"></a>
JDBC API主要包含以下核心组件：

- **`DriverManager`**：管理数据库驱动程序。
- **`Connection`**：代表与数据库的连接。
- **`Statement`/`PreparedStatement`/`CallableStatement`**：执行SQL语句。
- **`ResultSet`**：存储查询结果。
- **`SQLException`**：处理数据库操作异常。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.3 建立数据库连接<a id="73-建立数据库连接"></a>
连接数据库是使用JDBC的第一步：

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // 数据库连接参数
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "password";
        
        // 建立连接
        try {
            // 注册驱动（Java 6之后可以省略）
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 获取连接
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功!");
            
            // 使用完毕后关闭连接
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到数据库驱动: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.4 执行SQL语句<a id="74-执行sql语句"></a>
JDBC提供了三种执行SQL语句的方式：

**1. Statement（基本语句）**
```java
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 省略连接代码...
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();
            
            // 执行更新（INSERT, UPDATE, DELETE）
            String sql = "INSERT INTO users (name, email) VALUES ('张三', 'zhangsan@example.com')";
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("插入了 " + rowsAffected + " 条记录");
            
            // 执行查询（SELECT）
            String query = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(query);
            
            // 处理结果集
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id + ": " + name + " (" + email + ")");
            }
            
            // 关闭资源
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**2. PreparedStatement（预编译语句，推荐使用）**
```java
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 省略连接代码...
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // 预编译SQL，使用?作为参数占位符
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // 设置参数
            pstmt.setString(1, "李四");  // 第一个?
            pstmt.setString(2, "lisi@example.com");  // 第二个?
            
            // 执行更新
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("插入了 " + rowsAffected + " 条记录");
            
            // 查询示例
            String query = "SELECT * FROM users WHERE name = ?";
            PreparedStatement queryStmt = conn.prepareStatement(query);
            queryStmt.setString(1, "李四");
            
            ResultSet rs = queryStmt.executeQuery();
            while (rs.next()) {
                System.out.println("找到: " + rs.getString("name"));
            }
            
            // 关闭资源
            rs.close();
            queryStmt.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.5 处理ResultSet<a id="75-处理resultset"></a>
`ResultSet`对象包含查询返回的数据，提供了多种方法获取不同类型的数据：

```java
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 省略连接和查询代码...
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            
            // 遍历结果集
            while (rs.next()) {
                // 通过列名获取
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Date createTime = rs.getDate("create_time");
                boolean isAvailable = rs.getBoolean("is_available");
                
                // 通过列索引获取（从1开始）
                // int id = rs.getInt(1);
                // String name = rs.getString(2);
                
                System.out.println(id + ": " + name + ", 价格: " + price + 
                                  ", 创建时间: " + createTime + 
                                  ", 是否可用: " + isAvailable);
                
                // 检查是否为NULL
                if (rs.getObject("description") == null) {
                    System.out.println("  描述为空");
                } else {
                    System.out.println("  描述: " + rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.6 事务管理<a id="76-事务管理"></a>
事务确保一组数据库操作要么全部成功，要么全部失败：

```java
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 省略连接代码...
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            
            // 关闭自动提交
            conn.setAutoCommit(false);
            
            // 执行多个操作作为一个事务
            Statement stmt = conn.createStatement();
            
            // 操作1: 从账户A减去100
            stmt.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE id = 1");
            
            // 操作2: 向账户B增加100
            stmt.executeUpdate("UPDATE accounts SET balance = balance + 100 WHERE id = 2");
            
            // 如果一切正常，提交事务
            conn.commit();
            System.out.println("转账成功!");
            
            // 恢复自动提交
            conn.setAutoCommit(true);
            stmt.close();
        } catch (SQLException e) {
            // 发生错误，回滚事务
            System.out.println("转账失败，回滚事务: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 关闭连接
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.7 批处理操作<a id="77-批处理操作"></a>
批处理允许一次性执行多条SQL语句，提高性能：

```java
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 省略连接代码...
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            conn.setAutoCommit(false);  // 关闭自动提交
            
            // 创建PreparedStatement
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // 添加批处理记录
            for (int i = 1; i <= 1000; i++) {
                pstmt.setString(1, "用户" + i);
                pstmt.setString(2, "user" + i + "@example.com");
                pstmt.addBatch();  // 添加到批处理
                
                // 每500条执行一次
                if (i % 500 == 0) {
                    pstmt.executeBatch();  // 执行批处理
                    pstmt.clearBatch();    // 清空批处理
                }
            }
            
            // 执行剩余的批处理
            pstmt.executeBatch();
            
            // 提交事务
            conn.commit();
            System.out.println("批处理完成!");
            
            // 恢复自动提交
            conn.setAutoCommit(true);
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.8 数据库连接池<a id="78-数据库连接池"></a>
创建数据库连接很耗时，连接池可以重用连接提高性能：

```java
// 使用HikariCP连接池（需要添加依赖）
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // 配置连接池
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("root");
        config.setPassword("password");
        config.setMaximumPoolSize(10);  // 最大连接数
        
        // 创建数据源
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            // 从连接池获取连接
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")) {
                
                if (rs.next()) {
                    System.out.println("用户总数: " + rs.getInt(1));
                }
            }
            
            System.out.println("连接池状态:");
            System.out.println("  总连接数: " + dataSource.getHikariPoolMXBean().getTotalConnections());
            System.out.println("  活跃连接数: " + dataSource.getHikariPoolMXBean().getActiveConnections());
            System.out.println("  空闲连接数: " + dataSource.getHikariPoolMXBean().getIdleConnections());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.9 DAO设计模式<a id="79-dao设计模式"></a>
DAO（Data Access Object）模式将数据访问逻辑与业务逻辑分离：

```java
// 实体类
class User {
    private int id;
    private String name;
    private String email;
    
    // 构造器、getter和setter省略...
    
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

// DAO接口
interface UserDAO {
    void insert(User user) throws SQLException;
    User findById(int id) throws SQLException;
    List<User> findAll() throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
}

// DAO实现类
class UserDAOImpl implements UserDAO {
    private Connection conn;
    
    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            
            // 获取生成的ID
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        }
    }
    
    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
                return null;
            }
        }
    }
    
    // 其他方法实现省略...
}

// 使用DAO
public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            UserDAO userDAO = new UserDAOImpl(conn);
            
            // 创建用户
            User newUser = new User();
            newUser.setName("王五");
            newUser.setEmail("wangwu@example.com");
            userDAO.insert(newUser);
            System.out.println("创建用户: " + newUser);
            
            // 查询用户
            User user = userDAO.findById(newUser.getId());
            System.out.println("查询结果: " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

### 7.10 最佳实践<a id="710-最佳实践"></a>
- **使用PreparedStatement**：防止SQL注入，提高性能。
- **正确关闭资源**：使用try-with-resources自动关闭资源。
- **使用连接池**：重用数据库连接，提高性能。
- **事务管理**：确保数据一致性。
- **参数化配置**：将数据库连接信息放在配置文件中。
- **异常处理**：妥善处理SQLException。
- **分页查询**：处理大量数据时使用LIMIT进行分页。

[🔙 返回目录](#📂-目录) | [🔝 返回顶部](#-java-基础核心知识导航)

---

## 🌟 文档使用技巧

### 导航指南
1. **精准跳转**：点击目录中的链接直接跳转到对应章节
2. **快速返回**：每个章节底部有"返回目录"和"返回顶部"链接
3. **折叠展开**：点击顶部的"展开完整目录"可查看全部内容

### 学习进度追踪
可以在本地添加进度标记：
```markdown
- [x] 已学习：枚举
- [x] 已学习：异常
- [x] 已学习：泛型
- [x] 已学习：常用类
- [x] 已学习：集合
- [ ] 待学习：反射
- [ ] 待学习：数据库操作
```

### 搜索提示
- 使用 `Ctrl+F` 搜索关键词（如"泛型方法"、"异常处理"等）
- 搜索特定代码示例可用关键字如"示例："

### 自定义笔记
可以在各章节下方添加自己的笔记和理解，例如：
```markdown
### 我的笔记
- 反射虽然强大但会降低性能，只在必要时使用
- 使用PreparedStatement而不是Statement可以防止SQL注入
```

[🔝 返回顶部](#-java-基础核心知识导航)


