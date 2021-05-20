#include <stdio.h>
#include <stdlib.h>

/* 
随机函数rand生成0-RAND_MAX之间的数，会发现每次
运行程序会得到相同的序列，如果想得到不同的序列，
可以使用srand函数设置种子，但是相同的种子也会得到
相同的序列
*/
int main()
{
    int i;
    srand(47); /* 设置种子 */
    for (i = 0; i < 10; ++i)
        printf("%f\t", rand() / (RAND_MAX + 1.0));
    putchar('\n');
    return 0;
}