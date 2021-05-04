
#include <stdio.h>

/* 
条件表达式
*/
int main()
{
    // 类型转换在条件表达式中也适用
    int n = 1;
    float f = 1.0;
    double d = 2.0;
    // 这里虽然条件表达式 n > 0 ? f : d 得到的结果等于变量 f，但是整个表达式最终的类型是double类型
    printf("结果类型占字节数 %d，结果类型为 double\n", sizeof(n > 0 ? f : d));
    return 0;
}