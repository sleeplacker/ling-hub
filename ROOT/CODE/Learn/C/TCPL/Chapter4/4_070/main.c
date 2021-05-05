#include <stdio.h>

static int sa;
static int sfun1();
/* 其他文件定义的static变量和函数 */
// extern int sb;/* 编译报错 */
// extern int sfun2(); /* 编译报错 */

/* 静态变量和静态函数 */
/* 编译命令：gcc main.c call.c */
int main()
{
    printf("sa = %d\n", sa);
    printf("sfun1() = %d\n", sfun1());
    // printf("sb = %d\n", sb);/* 编译报错 */
    // printf("sfun2() = %d\n", sfun2());/* 编译报错 */
    return 0;
}

static int sa = 1;
static int sfun1()
{
    return 2;
}
