#include <stdio.h>

/* 结构定义方式 */
int main()
{
    /* 1.先定义标记，再定义变量 */
    struct point /* 定义标记，不会分配存储空间 */
    {
        int x;
        int y;
    };
    struct point a;   /* 定义变量，需要分配存储空间 */
    a.x = 1, a.y = 1; /* 结构成员赋值 */

    /* 2.定义结构的同事声明变量 */
    struct
    {
        int x; /* 结构中的成员名可以和其他结构中的成员同名，不会发生冲突 */
        int y;
    } p1, p2 = {1, 2}; /* 定义了结构变量，所以会分配存储空间 */

    /* 3.结构成员、结构标记和普通变量可以同名而不会发生冲突 */
    struct x
    {
        int x;
    } x;
    x.x = 1;

    printf("x.x=%d\n", x.x);

    /* 4.结构初始化 */
    struct point p3 = {2, 3};
    printf("p3.x=%d p3.y=%d\n", p3.x, p3.y);

    return 0;
}