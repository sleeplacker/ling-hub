

### 1 源码安装

下载，解压并编译 Redis：

```shell
$ wget https://download.redis.io/releases/redis-6.2.4.tar.gz
$ tar xzf redis-6.2.4.tar.gz
$ cd redis-6.2.4
$ make
```

现在 src 目录中编译后的二进制文件已经可用，可以使用下面命令启动 Redis 服务：        

```shell
$ src/redis-server
```

使用 Redis 内建的客户端进行交互：

```shell
$ src/redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
```



### 2 使用官方的 Ubuntu PPA 安装

​            You can install the latest stable version of Redis from the            `redislabs/redis` package repository. Add the repository to the            `apt` index, update it and install:          

```
$ sudo add-apt-repository ppa:redislabs/redis
$ sudo apt-get update
$ sudo apt-get install redis
```



### 3 使用 Snapcraft 安装

​            You can install the latest stable version of Redis from the Snapcraft            marketplace:          

```
$ sudo snap install redis
```

​            Are you new to Redis? Try our            [online, interactive tutorial](http://try.redis.io).          