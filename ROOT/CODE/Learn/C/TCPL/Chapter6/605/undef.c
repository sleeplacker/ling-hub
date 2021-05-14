#include <stdio.h>

#define AAA 1

int a = AAA;

#define AAA 2

int b = AAA;

/* 
编写函数undef，它将由lookup和install维护的表中
删除一个变量名及其定义。
 */
int main()
{
    printf("a=%d\n", a);
    printf("b=%d\n", b);
    return 0;
}
