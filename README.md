# showcase
基于springboot的web开发demo

## 热部署开启

### 设置(mac)
- command + SHIFT + A --> 查找make project automatically --> 选中
- command + SHIFT + A --> 查找Registry --> 找到并勾选compiler.automake.allow.when.app.running

### 添加依赖
```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                </configuration>
        </plugin>
    </plugins>
</build>
```

