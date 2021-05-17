#include <stdio.h>

/* stderr 会在 stdout 的前面打印 */
int main()
{
    /* 预期结果：outerr */
    /* 实际结果：errout */
    putc('o', stdout);
    putc('u', stdout);
    putc('t', stdout);

    putc('e', stderr);
    putc('r', stderr);
    putc('r', stderr);
    return 0;
}