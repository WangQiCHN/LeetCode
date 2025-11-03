**当然可以！**  
下面是为 **VS Code + Maven + Eureka** 定制的 **完整、零基础、可运行的 Spring Cloud 环境搭建教程（Mac 专用）**。

> 目标：用 **VS Code** 开发，**Maven** 构建，**Eureka** 做服务注册中心，**不依赖 IDEA**，**纯本地运行（无需 Docker）**

---

## 最终目标：运行一个 Eureka Server + 一个微服务（注册 + 心跳）

```
Eureka Server (8848)
      ↑
User Service (8081) → 注册到 Eureka
```

---

## 一、环境准备（Mac + VS Code）

| 工具 | 安装方式 |
|------|----------|
| **JDK 17** | `brew install temurin@17` |
| **VS Code** | [官网下载](https://code.visualstudio.com/) |
| **VS Code 插件** | 搜索安装以下插件：<br>• **Extension Pack for Java**<br>• **Maven for Java**<br>• **Spring Boot Extension Pack** |
| **Maven** | VS Code 已集成，无需手动安装 |
| **Eureka Server** | 直接用 Spring Boot 启动 |

---

## 二、步骤 1：安装 JDK 17

```bash
# 安装
brew install temurin@17

# 配置环境变量（~/.zshrc）
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc

# 验证
java -version
# 应输出: openjdk version "17.x"
```

---

## 三、步骤 2：创建项目目录结构

```bash
mkdir spring-cloud-eureka-vscode
cd spring-cloud-eureka-vscode

# 创建两个模块
mkdir eureka-server
mkdir user-service
```

---

## 四、步骤 3：创建 **Eureka Server**（eureka-server）

### 1. 创建 `pom.xml`

```xml
<!-- eureka-server/pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>eureka-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>eureka-server</name>

    <properties>
        <java.version>17</java.version>
        <spring-boot.version>3.2.5</spring-boot.version>
        <spring-cloud.version>2023.0.1</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

### 2. 创建 `src/main/resources/application.yml`

```yaml
# eureka-server/src/main/resources/application.yml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false   # 不把自己注册到自己
    fetch-registry: false         # 不拉取服务列表
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  server:
    enable-self-preservation: false  # 开发环境关闭自保护

spring:
  application:
    name: eureka-server
```

---

### 3. 创建主类

```java
// eureka-server/src/main/java/com/example/EurekaServerApplication.java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

---

## 五、步骤 4：创建 **User Service**（user-service）

### 1. 创建 `pom.xml`

```xml
<!-- user-service/pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>user-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>user-service</name>

    <properties>
        <java.version>17</java.version>
        <spring-boot.version>3.2.5</spring-boot.version>
        <spring-cloud.version>2023.0.1</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

### 2. 创建 `src/main/resources/application.yml`

```yaml
# user-service/src/main/resources/application.yml
server:
  port: 8081

spring:
  application:
    name: user-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}
```

---

### 3. 创建主类 + 接口

```java
// user-service/src/main/java/com/example/UserServiceApplication.java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

```java
// user-service/src/main/java/com/example/controller/UserController.java
package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from " + appName + "!";
    }
}
```

---

## 六、VS Code 操作步骤

1. 打开 VS Code
2. `File → Open Folder` → 选择 `spring-cloud-eureka-vscode` 文件夹
3. 等待 Maven 自动下载依赖（右下角会显示进度）

---

## 七、启动服务

### 方式 1：使用 VS Code 终端（推荐）

```bash
# 启动 Eureka Server
cd eureka-server
mvn spring-boot:run

# 新开一个终端
cd user-service
mvn spring-boot:run
```

### 方式 2：使用 VS Code 运行按钮

- 打开 `EurekaServerApplication.java`
- 点击方法名上方的 **Run** 按钮
- 同理运行 `UserServiceApplication.java`

---

## 八、验证成功

| 步骤 | 操作 | 预期结果 |
|------|------|----------|
| 1 | 访问 http://localhost:8761 | 看到 Eureka 控制台 |
| 2 | 控制台中看到 `user-service` | 服务已注册 |
| 3 | 访问 http://localhost:8081/hello | 返回 `Hello from user-service!` |

---

## 九、完整目录结构

```
spring-cloud-eureka-vscode/
├── eureka-server/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/EurekaServerApplication.java
│           └── resources/application.yml
├── user-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/example/
│           │   ├── UserServiceApplication.java
│           │   └── controller/UserController.java
│           └── resources/application.yml
```

---

## 十、常见问题 & 解决

| 问题 | 原因 | 解决 |
|------|------|------|
| `Cannot execute mojo: spring-boot:run` | Maven 未下载完 | 等待或 `mvn clean install` |
| 端口被占用 | 8761/8081 被占用 | 修改 `application.yml` 中的 `server.port` |
| 服务未注册 | `@EnableEurekaClient` 缺失 | 确保主类上有 |
| 控制台报错 `Connection refused` | Eureka Server 未启动 | 先启动 `eureka-server` |

---

## 成功标志

```
Eureka 控制台显示：
Instances currently registered with Eureka

Application    | AMIs | Availability Zones | Status
USER-SERVICE   | n/a  | n/a                | UP (1)
```

---

## 推荐 VS Code 快捷键

| 快捷键 | 功能 |
|-------|------|
| `Cmd + Shift + P` → `Maven: Execute Commands` | 快速运行 Maven 命令 |
| `Cmd + B` | 打开/关闭侧边栏 |
| `F5` | 启动调试 |

---

## 总结：VS Code + Maven + Eureka 搭建完成！

| 步骤 | 内容 |
|------|------|
| 1 | 安装 JDK 17 |
| 2 | 创建 `eureka-server` 和 `user-service` |
| 3 | 配置 `pom.xml` + `application.yml` |
| 4 | 用 `mvn spring-boot:run` 启动 |
| 5 | 访问 `localhost:8761` 和 `localhost:8081/hello` |

---

**你现在已经拥有一个纯 VS Code 驱动的 Spring Cloud + Eureka 开发环境！**

---

需要我打包成 **GitHub 仓库模板** 或 **ZIP 文件** 一键导入吗？  
回复 `yes` 我就生成！