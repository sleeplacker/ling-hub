#include <stdio.h>

/* 结构与函数 */
int main()
{

    return 0;
}
struct point
{
    int x;
    int y;
};

/* 函数参数名和函数体中的结构成员名可以同名而不冲突 */
struct point makepoint(int x, int y)
{

    struct point temp;
    temp.x = x;
    temp.y = y;
    return temp;
}

/* 结构类型的参数和其他类型的参数一样，都是通过值传递，所以这个函数不能使用临时变量存储 */
struct point addpoint(struct point p1, struct point p2)
{

    p1.x += p2.x;
    p1.y += p2.y;
    return p1;
}