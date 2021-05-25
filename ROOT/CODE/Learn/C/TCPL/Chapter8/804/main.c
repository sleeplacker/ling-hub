#include "mystdio.h"

/* 
标准库函数 
int fseek(FILE *fp, long offset, int origin)
类似于函数lseek，所不同的是，该函数中的fp是一个文件
指针而不是文件描述符，且返回值是一个int类型的状态而非
位置值。编写函数fseek，并确保该函数与库中其他函数使用
的缓冲能够协同工作。
 */
/* 编译命令：gcc main.c buf.c fopen.c fseek.c -g */
int main()
{
    int c;
    FILE *fpr = fopen("./1.txt", "r");
    /* 现在缓冲区填入6个字符 */
    getc(fpr);
    getc(fpr);
    getc(fpr);
    getc(fpr);
    getc(fpr);
    getc(fpr);
    if (fseek(fpr, 4, 1) == EOF)/* 从当前位置的第4位开始 */
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