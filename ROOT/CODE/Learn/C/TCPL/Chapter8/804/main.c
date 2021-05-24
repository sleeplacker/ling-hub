#include "mystdio.h"

/* 
标准库函数 
int fseek(FILE *fp, long offset, int origin)
类似于函数lseek，所不同的是，该函数中的fp是一个文件
指针而不是文件描述符，且返回值是一个int类型的状态而非
位置值。编写函数fseek，并确保该函数与库中其他函数使用
的缓冲能够协同工作。
 */
/* 编译命令：gcc main.c buf.c fopen.c fseek.c */
int main()
{int c;
    FILE *fpr = fopen("./1.txt", "r");
    FILE *fpw = fopen("./1.txt", "w");
    putchar((int)fpr);
    putchar((int)fpw);
    if (fseek(fpr, 2, 0) == EOF)
        putchar('F');
    else
    {
        putchar('S');
        putchar(':');
        putchar(' ');
        c = getc(fpr);
        putchar(c);
        putchar(getc(fpr));
        putchar(getc(fpr));
    }
    putchar('\n');
    fclose(stdout);
    return 0;
}