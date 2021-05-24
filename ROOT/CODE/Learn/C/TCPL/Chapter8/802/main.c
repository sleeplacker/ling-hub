#include "mystdio.h"

/* 
用字段代替显示的按位操作，重写fopen和_fillbuf函数。
比较相应代码的长度和执行速度。

结论：新方案的代码规模增加了不少，函数的执行速度也会
变慢了。位字段操作不仅要依赖于计算机硬件，还会降低执
行速度。
 */
/* 注意，这时个不完整的例子，编译不会通过，完整例子看803 */
int main()
{
    int c;
    FILE *fp = fopen("./1.txt", "r");
    while ((c = getc(fp)) != EOF)
        putc(c, stdout);
    return 0;
}