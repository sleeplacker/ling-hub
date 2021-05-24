#include "mystdio.h"

/* 设计并编写函数_flushbuf、fflush和fclose。 */
/* 编译命令：gcc main.c fopen.c buf.c -g */
/* 
缓冲区的作用：有缓冲区的存在，不必在每次读写操作都进行系统调用，
系统调用会由用户态进入内核态，开销较大，因此缓冲区很有必要，尤其
是在有单字节频繁读写的情况。
 */
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
    /* 注意：必须调用fclose方法来刷新缓冲区，否则可能没东西打印 */
    fclose(stdout); 
    return 0;
}