
#include <stdio.h>

void recomment(int c);
void in_comment(void);
void echo_quote(int c);
/*
编写一个程序，删除C语言程序中所有的注释语句。
要正确处理带引号的字符串与字符常量。
在C语言中，注释不允许嵌套。
 */
int main()
{
    int c, d;
    while ((c = getchar()) != EOF)
        recomment(c);
    return 0;
}

void recomment(int c)
{
    int d;
    if (c == '/')
        if ((d = getchar()) == '*')
            in_comment();
        else if (d == '/')
        {
            putchar(c);
            recomment(d);
        }
        else
        {
            putchar(c);
            putchar(d);
        }
    else if (c == '\'' || c == '"')
        echo_quote(c);
    else
        putchar(c);
}

void in_comment(void)
{
    int c, d;
    c = getchar();
    d = getchar();
    while (c != '*' || d != '/')
    {
        c = d;
        d = getchar();
    }
}

void echo_quote(int c)
{
    int d;
    putchar(c);
    while ((d = getchar()) != c)
    {
        putchar(d);
        if (d == '\\')
            putchar(getchar());
    }
    putchar(d);
}