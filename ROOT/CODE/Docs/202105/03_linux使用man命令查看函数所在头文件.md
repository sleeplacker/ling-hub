## 1 查看当前系统的man命令用法

```shell
man man

MAN(1)                                                                                        手册分页显示工具                                                                                       MAN(1)

名称
       man - 系统参考手册的接口

概述
       man [man 选项] [[章节] 页 ...] ...
       man -k [apropos 选项] 正则表达式 ...
       man -K [man 选项] [章节] 关键词 ...
       man -f [whatis 选项] 页 ...
       man -l [man 选项] 文件 ...
       man -w|-W [man 选项] page ...

描述
       man 是系统的手册分页程序。指定给 man 的 页 选项通常是程序、工具或函数名。程序将显示每一个找到的相关 手册页。如果指定了 章节，man 将只在手册的指定 章节 搜索。默认将按预定的顺序查找所有可用的 章节（
       参见 默认值 一节），并只显示找到的第一个 页，即使多个 章节 中都有这个 页面。

       下表显示了手册的 章节 号及其包含的手册页类型。

       1   可执行程序或 shell 命令
       2   系统调用(内核提供的函数)
       3   库调用(程序库中的函数)
       4   特殊文件(通常位于 /dev)
       5   文件格式和规范，如  /etc/passwd
       6   游戏
       7   杂项(包括宏包和规范，如 man(7)，groff(7))
       8   系统管理命令(通常只针对 root 用户)
       9   内核例程 [非标准

```

可以看到 1 2 3 分别代表的类型为可执行程序、系统调用和库调用。

## 2 使用man命令查看库函数描述

```shell
[lingang@lingang-nuc8i5beh ~]$ man 3 write

WRITE(3P)                                                                                POSIX Programmer's Manual                                                                                WRITE(3P)

PROLOG
       This  manual  page  is part of the POSIX Programmer's Manual.  The Linux implementation of this interface may differ (consult the corresponding Linux manual page for details of Linux behavior), or
       the interface may not be implemented on Linux.

NAME
       pwrite, write — write on a file

SYNOPSIS
       #include <unistd.h>

       ssize_t pwrite(int fildes, const void *buf, size_t nbyte,
           off_t offset);
       ssize_t write(int fildes, const void *buf, size_t nbyte);

```

因为 write 函数其实也属于系统调用，所以使用下面命令也是可以的

```shell
[lingang@lingang-nuc8i5beh ~]$ man 2 write

WRITE(2)                                                                                 Linux Programmer's Manual                                                                                 WRITE(2)

NAME
       write - write to a file descriptor

SYNOPSIS
       #include <unistd.h>

       ssize_t write(int fd, const void *buf, size_t count);

```

 