
#include <stdio.h>

unsigned rightrot(unsigned x, int n);
/* 
编写一个函数rightrot(x,n)，该函数返回将x循环右移(即
最左端移除的位将从最左端移入) n (二进制) 位后所得的值。
*/
int main()
{

    printf("255 循环右移34位 = %u\n", rightrot(255, 34));
    return 0;
}
unsigned rightrot(unsigned x, int n)
{
    int binLen, moveLen;
    binLen = sizeof(x) * 8; //计算操作数的二进制长度
    moveLen = n % binLen;   //计算要移位的长度，如果超出操作数长度，要取模
    printf("data len %d\n", binLen);
    printf("move len %d\n", moveLen);

    return x >> moveLen | x << binLen - moveLen;
}