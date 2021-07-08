## 1 下载及安装

- 下载地址：https://maven.apache.org/download.cgi

- 将安装包解压到/opt/目录

  ```shell
  tar zxvf apache-maven-3.6.0-bin.tar.gz –C /opt
  ```

- 配置环境变量

  ```
  #/etc/environment 
  # 在 PAHT 后面增加：":/opt/apache-maven-3.8.1/bin/"

  export PATH=$PATH:/opt/redis-6.2.4/src:/opt/apache-maven-3.8.1/bin/
  ```
  
- 修改仓库提高下载速度

  打开文件

  ```
  /opt/apache-maven-3.8.1/conf/settings.xml
  ```

  在 <mirrors> 标签内增加如下配置

  ```xml
  <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
  </mirror>
  ```




## 2 下载依赖包

- 查找依赖配置

  查找地址：https://mvnrepository.com/

  例如搜索

  ```
  org.redisson:redisson:3.16.0
  ```

  可以查询到依赖配置如下

  ```xml
  <!-- https://mvnrepository.com/artifact/org.redisson/redisson -->
  <dependency>
      <groupId>org.redisson</groupId>
      <artifactId>redisson</artifactId>
      <version>3.16.0</version>
  </dependency>
  ```

- 新建pom.xml文件

  在pom.xml中加入上面查询的依赖配置

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
  	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  
  	<modelVersion>4.0.0</modelVersion>
  	<groupId>test</groupId>
  	<artifactId>test</artifactId>
  	<version>1.0-SNAPSHOT</version>
  
  	<!--依赖项信息，依赖到的jar包 -->
  	<dependencies>
  		<dependency>
  			<groupId>org.redisson</groupId>
  			<artifactId>redisson</artifactId>
  			<version>3.16.0</version>
  		</dependency>
  	</dependencies>
  
  </project>
  ```

- 在包含pom.xml的目录执行命令进行下载

  ```shell
  mvn -f pom.xml dependency:copy-dependencies
  ```

  这时会创建或更新仓库，目录如下

  ```
  ~/.m2/repository/
  ```

  我们需要的依赖包在当前目录的 target 目录中。
  
- 下载源码和文档

  ```shell
  mvn -f pom.xml dependency:sources -DdownloadSources=true -DdownloadJavadocs=true
  ```

  

