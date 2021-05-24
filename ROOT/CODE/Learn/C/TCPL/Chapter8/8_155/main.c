#include "mystdio.h"

/* 编译命令：gcc main.c fopen.c buf.c */
int main()
{
    int c;
    FILE *fp = fopen("./1.txt", "r");
    while ((c = getc(fp)) != EOF)
        putc(c, stdout);
    return 0;
}