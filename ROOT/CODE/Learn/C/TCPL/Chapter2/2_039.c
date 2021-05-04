
#include <stdio.h>

unsigned gitbits(unsigned x, int p, int n);
/* 
getbits函数
*/
int main()
{
    printf("~0 = %u\n", ~0);
    printf("1022 倒数第2、3、4位 = %u\n", gitbits(1022, 3, 3));
    printf("1022 倒数第1、2、3位 = %u\n", gitbits(1022, 2, 3));
    return 0;
}
unsigned gitbits(unsigned x, int p, int n)
{
    return (x >> (p + 1 - n)) & ~(~0 << n);
}