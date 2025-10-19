Below is a simple example of setting up and running a microservice using **Spring Cloud** with **Spring Boot** on a Mac. We’ll create a basic microservice that uses **Spring Cloud Config** for centralized configuration and **Eureka** for service discovery. This example assumes you have basic familiarity with Java and Maven.

---

### Example: Spring Cloud Microservice with Eureka and Config Server

We’ll set up two services:
1. **Config Server**: Manages configuration for the microservice.
2. **Eureka Server**: Handles service discovery.
3. **Sample Microservice**: A simple REST service that uses Config Server and registers with Eureka.

---

### Prerequisites
- **Java 17+**: Installed on your Mac (check with `java -version`).
- **Maven**: Installed (check with `mvn -version`).
- **Git**: For Config Server to fetch configurations (optional, but used in this example).
- **IDE**: IntelliJ IDEA, VS Code, or any editor (optional for convenience).

---

### Step 1: Set Up the Config Server

1. **Create a Config Server Project**
   - Create a new directory: `mkdir spring-cloud-config-server && cd spring-cloud-config-server`.
   - Initialize a Spring Boot project using Spring Initializr or create a `pom.xml`:

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.example</groupId>
       <artifactId>config-server</artifactId>
       <version>0.0.1-SNAPSHOT</version>
       <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>3.3.4</version>
       </parent>
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-config-server</artifactId>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-actuator</artifactId>
           </dependency>
       </dependencies>
       <dependencyManagement>
           <dependencies>
               <dependency>
                   <groupId>org.springframework.cloud</groupId>
                   <artifactId>spring-cloud-dependencies</artifactId>
                   <version>2023.0.3</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
           </dependencies>
       </dependencyManagement>
   </project>
   ```

2. **Enable Config Server**
   - Create a file `src/main/java/com/example/configserver/ConfigServerApplication.java`:

   ```java
   package com.example.configserver;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.config.server.EnableConfigServer;

   @SpringBootApplication
   @EnableConfigServer
   public class ConfigServerApplication {
       public static void main(String[] args) {
           SpringApplication.run(ConfigServerApplication.class, args);
       }
   }
   ```

3. **Configure the Config Server**
   - Create `src/main/resources/application.yml`:

   ```yaml
   server:
     port: 8888
   spring:
     cloud:
       config:
         server:
           git:
             uri: https://github.com/your-username/config-repo
             clone-on-start: true
   ```

   - Create a Git repository (e.g., on GitHub) named `config-repo` and add a configuration file `microservice.properties` with content:

   ```properties
   greeting.message=Hello from Config Server!
   ```

   - Replace `your-username` with your GitHub username.

4. **Run the Config Server**
   - In the terminal, navigate to the project directory and run:
     ```bash
     mvn spring-boot:run
     ```
   - The Config Server will start on `http://localhost:8888`.

---

### Step 2: Set Up the Eureka Server

1. **Create an Eureka Server Project**
   - Create a new directory: `mkdir eureka-server && cd eureka-server`.
   - Create a `pom.xml`:

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.example</groupId>
       <artifactId>eureka-server</artifactId>
       <version>0.0.1-SNAPSHOT</version>
       <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>3.3.4</version>
       </parent>
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
                   <version>2023.0.3</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
           </dependencies>
       </dependencyManagement>
   </project>
   ```

2. **Enable Eureka Server**
   - Create `src/main/java/com/example/eurekaserver/EurekaServerApplication.java`:

   ```java
   package com.example.eurekaserver;

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

3. **Configure Eureka Server**
   - Create `src/main/resources/application.yml`:

   ```yaml
   server:
     port: 8761
   eureka:
     client:
       register-with-eureka: false
       fetch-registry: false
   ```

4. **Run the Eureka Server**
   - In the terminal, run:
     ```bash
     mvn spring-boot:run
     ```
   - Access the Eureka dashboard at `http://localhost:8761`.

---

### Step 3: Create a Sample Microservice

1. **Create a Microservice Project**
   - Create a new directory: `mkdir sample-microservice && cd sample-microservice`.
   - Create a `pom.xml`:

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.example</groupId>
       <artifactId>sample-microservice</artifactId>
       <version>0.0.1-SNAPSHOT</version>
       <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>3.3.4</version>
       </parent>
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-config</artifactId>
           </dependency>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
           </dependency>
       </dependencies>
       <dependencyManagement>
           <dependencies>
               <dependency>
                   <groupId>org.springframework.cloud</groupId>
                   <artifactId>spring-cloud-dependencies</artifactId>
                   <version>2023.0.3</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
           </dependencies>
       </dependencyManagement>
   </project>
   ```

2. **Create a REST Controller**
   - Create `src/main/java/com/example/microservice/SampleController.java`:

   ```java
   package com.example.microservice;

   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   public class SampleController {
       @Value("${greeting.message}")
       private String message;

       @GetMapping("/greet")
       public String greet() {
           return message;
       }
   }
   ```

3. **Enable Eureka Client and Config Client**
   - Create `src/main/java/com/example/microservice/SampleMicroserviceApplication.java`:

   ```java
   package com.example.microservice;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

   @SpringBootApplication
   @EnableDiscoveryClient
   public class SampleMicroserviceApplication {
       public static void main(String[] args) {
           SpringApplication.run(SampleMicroserviceApplication.class, args);
       }
   }
   ```

4. **Configure the Microservice**
   - Create `src/main/resources/bootstrap.yml` (note: `bootstrap.yml` is used for Config Server properties):

   ```yaml
   spring:
     application:
       name: microservice
     cloud:
       config:
         uri: http://localhost:8888
   ```

   - Create `src/main/resources/application.yml`:

   ```yaml
   server:
     port: 8080
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8761/eureka/
   ```

5. **Run the Microservice**
   - In the terminal, run:
     ```bash
     mvn spring-boot:run
     ```

---

### Step 4: Test the Setup

1. **Verify Config Server**
   - Open `http://localhost:8888/microservice/default` in a browser. You should see the configuration from `microservice.properties`.

2. **Verify Eureka Server**
   - Open `http://localhost:8761`. You should see the `microservice` registered in the Eureka dashboard.

3. **Test the Microservice**
   - Use a browser or `curl` to access the endpoint:
     ```bash
     curl http://localhost:8080/greet
     ```
   - Expected output: `Hello from Config Server!`

---

### Notes
- **Order of Execution**: Start the Config Server first, then the Eureka Server, and finally the microservice.
- **Git Repository**: Ensure the Git repository (`config-repo`) is accessible. You can use a local Git repository by replacing the `uri` in the Config Server’s `application.yml` with `file:///path/to/local/repo`.
- **Troubleshooting**:
  - Ensure ports `8888`, `8761`, and `8080` are free.
  - Check Maven dependencies are resolved (`mvn clean install`).
  - Verify Java and Maven versions are compatible.
- **Scaling**: To demonstrate service discovery, you can run multiple instances of the microservice on different ports (e.g., `8081`) by changing `server.port` in `application.yml`.

This example demonstrates a basic Spring Cloud setup with Config Server and Eureka. If you want to extend this with additional features like Spring Cloud Gateway or Resilience4j, let me know!