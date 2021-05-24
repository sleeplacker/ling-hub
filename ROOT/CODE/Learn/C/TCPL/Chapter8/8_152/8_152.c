#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdarg.h>
#include <stdlib.h>

#define PERMS 0666 /* 对于所有者、所有者组和其他成员均可读写 */

void error(char *, ...);

/* cp函数：将f1复制到f2 */
/* 执行命令：./a.out 1.txt 2.txt */
int main(int argc, char *argv[])
{
    int f1, f2, n;
    char buf[BUFSIZ];
    if (argc != 3)
        error("Usage: cp from to");
    if ((f1 = open(argv[1], O_RDONLY, 0)) == -1)
        error("cp: can't open %s", argv[1]);
    if ((f2 = creat(argv[2], PERMS)) == -1)
        error("cp: can't create %s, mode %03o", argv[2], PERMS);
    while ((n = read(f1, buf, BUFSIZ)) > 0)
        if (write(f2, buf, n) != n)
            error("cp: write error on file %s", argv[2]);
    close(f1); /* 关闭文件，但不刷新缓冲区 */
    sleep(2);
    unlink("2.txt"); /* 删除文件 */
    return 0;
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