#include <stdio.h>
int main()
{
    /* 
    以上介绍的getch与ungetch函数不能正确地处理
    压回的EOF。考虑压回EOF时应该如何处理。请实现
    你的设计方案

    答：将buf数字声明为int类型，因为EOF(-1)在
    char 和 int 类型之间转换来转换去时，在不同
    机器有不同的结果，所以要避免这种转换，把buf
    声明为 int 类型

    看 
    ./4_064/getch.c 
    */
    /* EOF=~0 */
    printf("%d\n", sizeof(EOF));
    printf("%d\n", sizeof(~0));
    printf("%d\n", EOF);
    printf("%d\n", ~0);
    return 0;
}
