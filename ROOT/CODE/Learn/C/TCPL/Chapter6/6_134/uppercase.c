#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

/* 将输入转换为大写并打印 */
/* 编译命令：gcc uppercase.c -o up */
/* 使用管道命令将 op 的输出重定向到 up 的输入：./op < in.txt | ./up */
int main()
{
    int c;
    while ((c = getchar()) != EOF)
        putchar(toupper(c));
    return 0;
}
