
#include <stdio.h>

unsigned invert(unsigned x, int p, int n);
/* 
编写一个函数invert(x,p,n)，该函数返回对x执行下
列操作后的结果值：将x中从第p位开始的n个（二进制）位
求反(即1变成0，0变成1)，x的其余各位保持不变。
*/
int main()
{

    printf("3876545 低15至低9位取反后 = %u\n", invert(3876545, 15, 7));
    return 0;
}
unsigned invert(unsigned x, int p, int n)
{
    return x ^ (~(~0 << n) << (p + 1 - n));
}