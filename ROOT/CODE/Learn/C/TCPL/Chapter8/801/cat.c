#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdarg.h>
#include <stdlib.h>

void error(char *fmt, ...);

/* 
用read、write、open和close系统调用代替标准库中等价
的函数，重写第7章的cat程序，并通过实验比较两个版本的相对
速度

结论：这个版本要比第7章的原始版本快大约两倍

不过这个版本的报错信息会打印在输出内容的末尾，因为系统
调用write方法没有缓冲，所以会直接写到屏幕。
 */
/* 执行命令：./a.out 1.txt 2.txt */
int main(int argc, char const *argv[])
{
    int fd;
    void filecopy(int ifd, int ofd);

    if (argc == 1)
        filecopy(0, 1);/* 标准输入复制到标准输出 */
    else
        while (--argc > 0)
            if ((fd = open(*++argv, O_RDONLY)) == -1)
                error("cat: can't open %s", *argv);
            else
            {
                filecopy(fd, 1);
                close(fd);
            }
    return 0;
}

void filecopy(int ifd, int ofd)
{
    int n;
    char buf[BUFSIZ];
    while ((n = read(ifd, buf, BUFSIZ)) > 0)
        if (write(ofd, buf, n) != n)
            error("cat: write error");
}

/* error函数：打印一个出错信息，然后终止 */
void error(char *fmt, ...)
{
    va_list args;
    va_start(args, fmt);
    fprintf(stderr, "error: ");
    vfprintf(stderr, fmt, args);
    fprintf(stderr, "\n");
    va_end(args);
    exit(1);
}