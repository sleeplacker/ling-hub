#include <stdio.h>

/* 取地址运算符&能作用于的变量类型 */
#define MAXLINE 100
int maxsize = 100;
const int hundry = 100;
int main()
{
    static int i = 0;
    int j = 0;
    register int k = 0;
    int *ptr;
    printf("%ld\n", ptr);
    // ptr = &MAXLINE;/* 宏不能使用取地址运算符& */
    // ptr = &hundry; /* 对常量使用&会有编译报警 */
    ptr = &maxsize; /* 全局变量可以使用& */
    ptr = &i;       /* 静态变量可以使用& */
    ptr = &j;       /* 局部变量可以使用& */
    // ptr = &k;       /* 寄存器变量不能使用& */
    // ptr = &(i + j); /* 表达式不能使用& */
    printf("%ld\n", ptr);
    return 0;
}