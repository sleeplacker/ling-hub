## 1 编译成可调试的可执行文件

必须使用 -g 选项来编译源文件

```shell
gcc test.c -g -o a.out
```



## 2 在VSCode中设置GDB配置

配置 launch.json，在 program 属性中写可执行文件路径，其他属性不用动

![VSCodeGDB](../static/image/VSCodeGDB.png)

然后点击 "(gdb)启动" 就能进行Debug