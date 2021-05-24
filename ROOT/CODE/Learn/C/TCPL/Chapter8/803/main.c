#include "mystdio.h"

/* 设计并编写函数_flushbuf、fflush和fclose。 */
/* 编译命令：gcc main.c fopen.c buf.c -g */
int main()
{
    int c;
    FILE *fp = fopen("./1.txt", "r");
    if (fp != NULL)
        while ((c = getc(fp)) != EOF)
            putchar(c);
    else
        putchar('F');
    putchar('\n');
    fclose(stdout); /* 注意：必须调用fclose方法来刷新缓冲区，否则可能没东西打印 */
    return 0;
}