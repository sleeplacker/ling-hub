#include <stdio.h>
/* 
验证：静态变量只在第一次进入程序块时被初始化一次。
*/
int main()
{
    int i;
    for (i = 0; i < 10; ++i)
    {
        static int j = 0; /* 静态内部变量，只初始化一次，第二次进入循环时，j不会再被赋值为0 */
        int k = 0;        /* 普通内部变量，每次进入循环，k都会被赋值为0 */
        printf("j=%d,k=%d\n", j, k);
        j++;
        k++;
        printf("j=%d,k=%d\n", j, k);
    }
    return 0;
}