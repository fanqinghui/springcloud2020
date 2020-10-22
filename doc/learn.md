# devtools 热部署
1 . 添加pom devtools 依赖
```
 <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
      <optional>true</optional>
    </dependency>
```
2 . 添加build 插件
```
 <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <!--<version>2.2.2.RELEASE</version>-->
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
3 . idea setting配置compire auto build project 四个选项勾选

4 . 配置maintenance
```
1. 使用快捷键 ctrl+shift+alt+/ 打开 maintenance窗口，选择registry
2. 勾选actionSystem.asertFocusAccessFromEdit
3. 勾选compilre.automake.allow.when.app.running
```