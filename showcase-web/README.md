集成`mybatis`、`mybatis-page-helper`、`mybatis-generate`。
增加阿里大于依赖，镜像配置：
```
<mirror>
     <id>nexus-aliyun</id>
     <mirrorOf>central</mirrorOf>
     <name>Nexus aliyun</name>
     <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
</mirror>
```