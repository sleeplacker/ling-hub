
#include <stdio.h>
#include <limits.h>

unsigned setbits(unsigned x, int p, int n, unsigned y);
/* 
编写一个函数setbits(x,p,n,y)，该函数返回对x执行下
列操作后的结果值：将x中从第p位开始的n个（二进制）位
设置为y中最右边n位的值，x的其余各位保持不变。
*/
int main()
{

    printf("147483647 低29至低20位替换为 483647 的低10位后 = %u\n", setbits(147483647, 28, 10, 483647));
    return 0;
}
unsigned setbits(unsigned x, int p, int n, unsigned y)
{
    return x & (~0 << (p + 1) | ~(~0 << (p + 1 - n))) | (((y & ~(~0 << n)) << (p + 1 - n)));
}