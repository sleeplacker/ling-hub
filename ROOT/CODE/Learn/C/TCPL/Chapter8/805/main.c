#include "dirent.h"

void fsize(char *);

/* 打印文件长度 */
/* 编译命令：gcc main.c dir.c walk.c -g */
/* 运行命令：./a.out ./*.c */
int main(int argc, char *argv[])
{
    if (argc == 1) /* 默认为当前目录 */
        fsize(".");
    else
        while (--argc > 0)
            fsize(*++argv);
    return 0;
}

void dirwalk(char *, void (*fcn)(char *));

/* fszie函数：打印文件name的长度 */
void fsize(char *name)
{
    struct stat stbuf;
    if (stat(name, &stbuf) == -1)
    {
        fprintf(stderr, "fsize: can't access %s\n", name);
        return;
    }
    if ((stbuf.st_mode & S_IFMT) == S_IFDIR)
        dirwalk(name, fsize);
    printf("i结点编号=%5u  文件模式(八进制)=%6o   文件链接个数=%u   文件长度=%ld 文件名=%s\n", stbuf.st_ino, stbuf.st_mode, stbuf.st_nlink, stbuf.st_size, name);
}