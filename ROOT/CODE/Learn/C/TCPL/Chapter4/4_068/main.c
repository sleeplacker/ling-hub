#include <stdio.h>

/* 变量或函数的extern声明 */
/* 编译命令：gcc main.c call.c */
int main()
{
    /* 对于变量或函数，先使用后定义时，要加上extern声明 */
    // int a; /* 如果不加上前面的extern，则a是新定义的一个变量，而不是下面定义的全局变量 */
    extern int a;
    extern double getDouble();
    /* 声明其他源文件中定义的变量和函数，缺少声明会有编译错误 */
    extern int b, getInt();

    printf("a = %d\n", a);
    printf("getDouble() = %f\n", getDouble());
    printf("b = %d\n", b);
    printf("getInt = %d\n", getInt());
    return 0;
}

int a = 1;
double getDouble()
{
    return 1.1;
}