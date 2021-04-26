
#include <stdio.h>
int globeVal;
void fun();
/* 变量声明初始值 */
int main()
{
    int mainLocalVal;
    const int mainConLocalVal;
    fun();
    printf("主函数中自动变量初始值=%d\n", mainLocalVal);
    printf("主函数中限定的自动变量初始值=%d\n", mainConLocalVal);
    return 0;
}

void fun()
{
    int static staticVal;
    int localVal;
    const int conLocalVal;

    printf("全局变量初始值=%d\n", globeVal);
    printf("静态变量初始值=%d\n", staticVal);
    printf("自动变量初始值=%d\n", localVal);
    printf("限定的自动变量初始值=%d\n", conLocalVal);
}