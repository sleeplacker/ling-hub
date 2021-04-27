
#include <stdio.h>

int bitcount(unsigned x);
/* 
在求二进制补码时，表达式x&=(x-1)可以删除x中最右边值为1的
一个二进制位。请解释这样做的道理。用这一方法重写bitcount
函数，以加快其执行速度。

解释：假设x的二进制位为：XX...XX100..00，则x-1的最右边的1会变成0，
后面所有低位的0会变成1(这样x和x-1的低位&操作会得到全0，和x对应的低位
一致)，而高位的XX...XX不会变，所以x&=(x-1)会清除最右边的0，而不会改变
其他位
*/
int main()
{

    printf("147899 的二进制位中1的个数是 %d\n", bitcount(147899));
    return 0;
}
int bitcount(unsigned x)
{
    int b = 0;
    while (x != 0)
    {
        x &= (x - 1);
        ++b;
    }

    return b;
}