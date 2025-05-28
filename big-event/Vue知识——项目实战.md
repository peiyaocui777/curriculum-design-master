---

# Vue 3 初学者实战笔记

这份笔记是为完全零基础、想通过实战快速上手 Vue 3 的同学准备的。我们假设你已经有了后端 API（参考“大事件接口文档”），比如登录、注册、文章管理等功能。你要做的就是用 Vue 3 做出前端页面，和后端对接。笔记会用**大白话**解释概念，给出**简单的基础代码示例**，加上**常见问题和解决方法**，让你边学边做，遇到问题不慌。

---

## 一、基础知识概念

先搞懂 Vue 3 的几个核心概念，就像认识工具箱里的工具，明白它们是干啥的，才能用得顺手。

### 1. Vue 3 是什么？
- **概念**：Vue 3 是一个前端框架，帮你快速开发网页，让页面好看、好用，还能跟后端交互。
- **通俗解释**：就像你在手机上用美图秀秀编辑照片，Vue 3 给你提供模板、按钮、数据处理功能，你只需要填内容，就能做出动态网页。

### 2. 组件
- **概念**：组件是 Vue 3 的基本单位，一个组件就是一个页面或页面的一部分，比如一个登录表单、一个文章列表。
- **通俗解释**：就像乐高积木，每块积木（组件）可以单独设计，拼在一起就成了完整的网页。

### 3. 响应式数据
- **概念**：Vue 3 的数据是“活”的，数据变了，页面会自动更新，不用你手动刷新。
- **通俗解释**：就像你家里的智能灯，开关一按（数据变），灯就亮（页面更新），完全自动。

### 4. 模板语法
- **概念**：模板是 Vue 3 的 HTML 部分，里面可以用特殊写法（像 `{{ }}` 或 `v-bind`）来显示数据或控制行为。
- **通俗解释**：模板就是网页的“草稿纸”，你画好结构，Vue 3 帮你把数据填进去，还能加点互动效果。

### 5. 路由（Vue Router）
- **概念**：路由管理网页的跳转，比如从登录页跳到文章页。
- **通俗解释**：就像地图导航，告诉你点哪儿去哪儿。

### 6. 事件处理
- **概念**：Vue 3 可以监听用户操作（比如点击、输入），然后触发你的代码。
- **通俗解释**：就像遥控器，按一下按钮，电视就换台。

---

## 二、知识用法

下面是 Vue 3 的核心用法，每个用法都给一个简单代码示例，带详细注释
### 1. 创建和运行 Vue 3 项目
- **用法**：用 Vue CLI 快速搭建项目，准备好开发环境。
- **代码示例**：
  ```bash
  npm install -g @vue/cli  # 安装 Vue CLI 工具
  vue create my-app        # 创建项目，选默认配置或加 Vue Router
  cd my-app                # 进入项目
  npm run serve            # 启动项目，浏览器会打开 localhost:8080
  ```
- **用法详解**：
    - Vue CLI 是一个命令行工具，帮你生成项目文件夹，里面有 `src`（放代码）、`public`（放静态文件）等。
    - 项目跑起来后，改 `src/App.vue` 就能看到效果。
- **项目场景**：你可以用这个环境开发登录、文章列表等页面。

### 2. 写一个基本组件
- **用法**：组件是 `.vue` 文件，包含模板（HTML）、逻辑（JS）、样式（CSS）。
- **代码示例**：
  ```vue
  <!-- 文件名：Welcome.vue -->
  <template>
    <div>
      <h1>{{ message }}</h1> <!-- 显示数据 -->
      <button @click="changeMessage">点我换句话</button>
    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        message: "欢迎体验 Vue 3！" // 定义数据
      };
    },
    methods: {
      changeMessage() {
        this.message = "你点我了！"; // 点击时改数据
      }
    }
  };
  </script>

  <style>
  h1 {
    color: green; /* 让标题变绿 */
  }
  </style>
  ```
- **用法详解**：
    - `<template>`：写网页结构，`{{ message }}` 显示 `data` 里的变量。
    - `<script>`：`data()` 定义数据，`methods` 放函数。
    - `<style>`：写 CSS 样式，控制页面好看不好看。
- **项目场景**：可以用组件显示用户信息（`/user/userInfo` 接口返回的数据）。

### 3. 双向绑定（v-model）
- **用法**：用 `v-model` 让输入框和数据同步，适合表单（比如登录、注册）。
- **代码示例**：
  ```vue
  <template>
    <div>
      <input v-model="username" placeholder="输入用户名" />
      <p>你输入的用户名是：{{ username }}</p>
    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        username: "" // 一开始为空
      };
    }
  };
  </script>
  ```
- **用法详解**：
    - `v-model` 把输入框和 `username` 绑定，用户输入啥，`username` 就变啥。
    - 适合登录表单，收集用户名和密码，发给 `/user/login` 接口。
- **项目场景**：在登录页面用 `v-model` 收集用户输入，然后发给后端。

### 4. 事件处理（@click）
- **用法**：用 `@click` 监听点击，触发自定义逻辑。
- **代码示例**：
  ```vue
  <template>
    <button @click="sayHello">点我</button>
  </template>

  <script>
  export default {
    methods: {
      sayHello() {
        alert("你好！欢迎学习 Vue 3！"); // 弹窗提示
      }
    }
  };
  </script>
  ```
- **用法详解**：
    - `@click` 绑定 `sayHello` 函数，点击按钮就运行。
    - 可以用来触发登录、提交表单等操作。
- **项目场景**：点“登录”按钮，调用 `/user/login` 接口。

### 5. 列表渲染（v-for）
- **用法**：用 `v-for` 循环显示一堆数据，比如文章列表。
- **代码示例**：
  ```vue
  <template>
    <ul>
      <li v-for="item in items" :key="item.id">
        {{ item.name }}
      </li>
    </ul>
  </template>

  <script>
  export default {
    data() {
      return {
        items: [
          { id: 1, name: "文章标题1" },
          { id: 2, name: "文章标题2" }
        ]
      };
    }
  };
  </script>
  ```
- **用法详解**：
    - `v-for` 遍历 `items` 数组，每项生成一个 `<li>`。
    - `:key` 是唯一标识，防止列表乱序。
- **项目场景**：用 `v-for` 显示 `/article` 接口返回的文章列表。

### 6. 条件渲染（v-if, v-else）
- **用法**：根据条件显示或隐藏内容。
- **代码示例**：
  ```vue
  <template>
    <div>
      <button @click="toggle">显示/隐藏</button>
      <p v-if="isVisible">我在这里！</p>
      <p v-else>我被隐藏了！</p>
    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        isVisible: true // 控制显示
      };
    },
    methods: {
      toggle() {
        this.isVisible = !this.isVisible; // 切换状态
      }
    }
  };
  </script>
  ```
- **用法详解**：
    - `v-if` 判断 `isVisible`，为 `true` 显示，为 `false` 隐藏。
    - `v-else` 是相反情况。
- **项目场景**：根据用户是否登录，显示“欢迎”或“请登录”。

### 7. 调用后端 API（Axios）
- **用法**：用 Axios 跟后端交互，拿数据或发数据。
- **先安装**：
  ```bash
  npm install axios
  ```
- **创建 API 服务**（`src/services/api.js`）：
  ```javascript
  import axios from "axios";

  const api = axios.create({
    baseURL: "http://你的后端地址" // 换成实际地址
  });

  export default api;
  ```
- **GET 请求**：
  ```vue
  <template>
    <p>用户名：{{ user }}</p>
  </template>

  <script>
  import api from "@/services/api";

  export default {
    data() {
      return {
        user: ""
      };
    },
    mounted() {
      api.get("/user/userInfo").then((response) => {
        this.user = response.data.username; // 假设返回 { username: "xxx" }
      });
    }
  };
  </script>
  ```
- **POST 请求**：
  ```vue
  <template>
    <form @submit.prevent="submit">
      <input v-model="text" placeholder="输入内容" />
      <button type="submit">提交</button>
    </form>
  </template>

  <script>
  import api from "@/services/api";

  export default {
    data() {
      return {
        text: ""
      };
    },
    methods: {
      async submit() {
        try {
          await api.post("/user/register", { username: this.text });
          alert("注册成功！");
        } catch (error) {
          alert("注册失败！");
        }
      }
    }
  };
  </script>
  ```
- **用法详解**：
    - `mounted` 是组件加载时运行，适合发 GET 请求。
    - `@submit.prevent` 防止表单刷新页面。
    - `try/catch` 捕获错误，避免程序崩。
- **项目场景**：用 GET 拿 `/user/userInfo`，用 POST 发 `/user/login`。

### 8. 路由跳转（Vue Router）
- **用法**：用 Vue Router 管理页面跳转，比如从登录页到文章页。
- **先安装**（创建项目时可选，或手动）：
  ```bash
  npm install vue-router@4
  ```
- **配置路由**（`src/router/index.js`）：
  ```javascript
  import { createRouter, createWebHistory } from "vue-router";
  import Home from "../views/Home.vue";
  import Login from "../views/Login.vue";

  const routes = [
    { path: "/", component: Home },
    { path: "/login", component: Login }
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes
  });

  export default router;
  ```
- **跳转**：
  ```vue
  <template>
    <div>
      <router-link to="/login">去登录</router-link>
      <button @click="goHome">回首页</button>
      <router-view></router-view>
    </div>
  </template>

  <script>
  export default {
    methods: {
      goHome() {
        this.$router.push("/"); // 程序化跳转
      }
    }
  };
  </script>
  ```
- **用法详解**：
    - `<router-link>` 是导航链接，`<router-view>` 显示页面。
    - `this.$router.push` 可以代码控制跳转。
- **项目场景**：登录成功后跳到文章列表页。

### 9. 文件上传
- **用法**：用 `FormData` 上传文件，比如文章图片。
- **代码示例**：
  ```vue
  <template>
    <input type="file" @change="uploadFile" />
  </template>

  <script>
  import api from "@/services/api";

  export default {
    methods: {
      uploadFile(event) {
        const file = event.target.files[0];
        const formData = new FormData();
        formData.append("file", file);
        api.post("/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" }
        }).then(() => {
          alert("上传成功！");
        });
      }
    }
  };
  </script>
  ```
- **用法详解**：
    - `@change` 监听文件选择，`FormData` 包装文件。
    - 设置 `multipart/form-data` 头，告诉后端是文件。
- **项目场景**：上传文章封面图片，调用 `/upload` 接口。

---

## 三、可能遇到的问题及解决方法

实战中肯定会碰到小问题，这里列出常见问题和解决办法，能少走弯路。

| **问题**                     | **可能原因**                          | **解决方法**                                                                 |
|------------------------------|---------------------------------------|-----------------------------------------------------------------------------|
| 页面没显示数据               | 数据没定义或拼写错误                  | 检查 `data()` 里是否定义变量，`{{ }}` 里变量名是否正确。                     |
| 按钮点击没反应               | `@click` 绑定的函数名不对             | 确认 `@click="函数名"` 和 `methods` 里的函数名一致。                         |
| 列表不显示或乱序             | 数组没数据，或没加 `:key`             | 确保 `v-for` 绑定的变量是数组，检查 `:key` 是否唯一。                        |
| 输入框不更新                 | `v-model` 变量没在 `data()` 里        | 确认 `v-model` 绑定的变量在 `data()` 中定义。                                |
| API 请求失败（401）          | 没带 token 或地址错                   | 检查 `headers` 是否加了 token，确认接口地址和后端是否运行。                   |
| 文件上传失败                 | 文件格式不对或后端限制                | 检查文件类型和大小，确认后端 `/upload` 接口支持 `multipart/form-data`。       |
| 页面跳转没反应               | 路由没配置或没引入                    | 确保 `router/index.js` 配置正确，`main.js` 里引入了 `router`。                |
| 数据更新但页面不刷新         | 数据没响应式                          | 用 `this.$set(this.array, 0, 新值)` 更新数组，或确保变量在 `data()` 中。     |
| 跨域错误（CORS）             | 后端没允许前端访问                    | 让后端加 `Access-Control-Allow-Origin` 头，或前端用代理。                     |

---

## 四、学习建议

1. **边做边学**：别光看，动手敲代码！试着做一个简单的登录页面，调 `/user/login` 接口。
2. **对照接口文档**：看“大事件接口文档”，比如 `/user/register` 需要 `username` 和 `password`，就用 `v-model` 收集数据。
3. **参考视频**：跟着视频教程做，遇到问题查笔记里的“问题解决”部分。
4. **查官方文档**：Vue 3 官网（[https://v3.vuejs.org/](https://v3.vuejs.org/)）有详细说明，遇到新用法可以去翻。
5. **小步快跑**：先做一个登录表单，再做一个文章列表，慢慢加功能。

---
