#include "mystdio.h"

/* 编译命令：gcc main.c fopen.c buf.c */
int main()
{
    int c;
    FILE *fp = fopen("./1.txt", "r");
    if (fp != NULL)
        while ((c = getc(fp)) != EOF)
            putc(c, stdout);
    else
        putc('F', stdout);
    return 0;
}