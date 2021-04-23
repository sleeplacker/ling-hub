
#include <stdio.h>
#include <limits.h>

/*
编写一个程序以确定分别由signed及unsigned限定打cahr、
short、int与long类型打变量的取值范围。采用打印标准
头文件中的相应值以及直接计算两种方式实现
 */
int main()
{
    printf("1.打印头文件符号常量方式得到数据取值范围\n");
    printf("signed char min = %d\n", SCHAR_MIN);
    printf("signed char max = %d\n", SCHAR_MAX);
    printf("signed short min = %d\n", SHRT_MIN);
    printf("signed short max = %d\n", SHRT_MAX);
    printf("signed int min = %d\n", INT_MIN);
    printf("signed int max = %d\n", INT_MAX);
    printf("signed long min = %ld\n", LONG_MIN);
    printf("signed long max = %ld\n", LONG_MAX);

    // 因为无符号数最小值都是0,所以只有最大值有意义
    printf("unsigned char max = %u\n", UCHAR_MAX); // 注意：无符号数打印格式为%u
    printf("unsigned short max = %u\n", USHRT_MAX);
    printf("unsigned int max = %u\n", UINT_MAX);
    printf("unsigned long max = %lu\n\n", ULONG_MAX); // 无符号long类型打印格式为%lu

    printf("2.移位计算方式得到数据取值范围\n");
    /*     
    思路：先将每种数据类型的所有位置为1,然后右移一位去掉符号位
    即得到了无符号最大值，然后将再将二进制位强转为对应的signed类型
    就可以得到最大的signed值
    需要注意的是：补码表示中负数总是比正数多一个，所以最小signed值要再-1 
    */
    printf("signed char min = %d\n", -(char)((unsigned char)~0 >> 1) - 1);
    printf("signed char max = %d\n", (char)((unsigned char)~0 >> 1));
    printf("signed short min = %d\n", -(short)((unsigned short)~0 >> 1) - 1);
    printf("signed short max = %d\n", (short)((unsigned short)~0 >> 1));
    printf("signed int min = %d\n", -(int)((unsigned int)~0 >> 1) - 1);
    printf("signed int max = %d\n", (int)((unsigned int)~0 >> 1));
    printf("signed long min = %ld\n", -(long)((unsigned long)~0 >> 1) - 1);
    printf("signed long max = %ld\n", (long)((unsigned long)~0 >> 1));

    printf("unsigned char max = %u\n", (unsigned char)~0);
    printf("unsigned short max = %u\n", (unsigned short)~0);
    printf("unsigned int max = %u\n", (unsigned int)~0);
    printf("unsigned long max = %lu\n\n", (unsigned long)~0);

    return 0;
}