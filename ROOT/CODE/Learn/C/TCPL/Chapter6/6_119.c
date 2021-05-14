#include <stdio.h>

/* 指向结构的指针
注意：在函数调用时传递大型结构会有很大开销，
因为函数会复制一份结构副本，如果传递结构指针
会大大减小开销。
 */
int main()
{
    struct point 
    {
        int x;
        int y;
    } p1;
    struct point *ptr = &p1;
    ptr->x = 1;
    ptr->y = 2;
    printf("p1.x=%d p1.y=%d\n", p1.x, p1.y);
    return 0;
}