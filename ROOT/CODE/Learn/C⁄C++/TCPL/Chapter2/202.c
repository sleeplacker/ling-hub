
#include <stdio.h>
/* 在不使用运算符&&或||的条件下编写一个与上面的for循环语句等价的循环语句 */
int main()
{
    int i = 0;
    int lim = 100;
    int c;
    while (i < lim - 1)
    {
        if ((c = getchar()) != '\n')
            if (c != EOF)
            {
                ++i;
                printf("handle %d|%d\n", i, c);
            }
            else
                break;
        else
            break;
    }
}