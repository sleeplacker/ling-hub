### 1 内建函数名冲突

当内建函数名和自定义函数名冲突时，编译会有警告，如：

```error
503.c:3:6: 警告：conflicting types for built-in function ‘strcat’; expected ‘char *(char *, const char *)’ [-Wbuiltin-declaration-mismatch]
    3 | void strcat(char s[], char t[]);
      |      ^~~~~~
503.c:2:1: 附注：‘strcat’ is declared in header ‘<string.h>’
    1 | #include <stdio.h>
  +++ |+#include <string.h>
    2 | 
```

可以在编译命令加上 `-fno-builtin` 选项来去掉警告，例如：

```shell
gcc 503.c -fno-builtin-strcat
```



### 2 宏和函数

#### 2.1 宏和函数的区别

一个操作可以由函数或宏来实现。

通常函数的时间开销比较大，因为函数调用过程的时间开销比较费时间；空间开下比较小。

而宏的时间开销比较小，因为宏没有函数调用方面的开销；空间开下比较大，因为宏的每次执行都要进行展开。

#### 2.2 使用宏的潜在问题

如下面这个宏

```c
#define isupper(c) ((c) >= 'A' && (c) <= 'Z') ? 1 : 0
```

当下面的代码使用该宏时

```C
char *p = "this is a string";
if(isupper(*p++))
    ...
```

这个宏将被展开为：

```
((*p++) >= 'A' && (*p++) <= 'Z') ? 1 : 0
```

根据*p的取值情况，这个表达式可能对指针p做两次递增操作。但是，如果isupper是一个函数，就不存在对指针p做两次递增操作的隐患——因为函数的参数只会被求值一次。

指针p的第二次递增操作不是我们所期望的，只会导致不正确的结果。下面是一种值得参考的解决方案：

```C
char *p = "this is a string";
if(isupper(*p))
    ...
p++;
```

对那些有可能对参数进行多次求值的宏，一定要提高警惕。头文件<ctype.c>中的两个宏toupper和tolower是很好的学习例子。