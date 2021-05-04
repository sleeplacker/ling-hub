#include <stdio.h>
#include <ctype.h>

#define NUMBER '0'

void itoa(int n, char s[]);

/* 
运用printd函数的设计思想编写一个递归版本的
itoa函数，即通过递归调用把整数转换为字符串。
*/
int main()
{
    char s[50];
    itoa(-2048, s);
    printf("%s\n", s);
}

void itoa(int n, char s[])
{
    static int i = 0;
    unsigned un = n;
    if (n < 0)
    {
        s[i++] = '-';
        un = -n;
    }

    if ((un / 10) != 0) // 208 20 2
        itoa(un / 10, s);
    s[i++] = un % 10 + '0';
    s[i] = '\0';
}