#include <stdio.h>
#include <ctype.h>

/* 输入输出和管道命令 */
/* 编译命令：gcc output.c -o op */
/* 输入命令：./op < in.txt */
/* 输出命令：./op < in.txt > out.txt */
int main()
{
    char s[20];
    scanf("%s", s);
    /* 输出小写字母 */
    printf("abc %s def\n", s);
    return 0;
}