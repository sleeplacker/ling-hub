#include <stdio.h>
#include <ctype.h>

#define MAXLINE 100
#define OCTLEN 6

/* 
编写一个程序，以合理的方式打印任何输入。该程序至少能够
根据用户的习惯已八进制或十六进制打印非图形字符，并截断
长文本。
 */
int main()
{
    int c, pos;
    int inc(int pos, int n);
    pos = 0;
    while ((c = getchar()) != EOF)
        if (iscntrl(c) || c == ' ')
        {
            pos = inc(pos, OCTLEN);
            printf(" \\%03o ", c);
            if (c == '\n')
            {
                pos = 0;
                putchar('\n');
            }
        }
        else
        {
            pos = inc(pos, 1);
            putchar(c);
        }
    return 0;
}

int inc(int pos, int n)
{
    if (pos + n < MAXLINE)
        return pos + n;
    else
    {
        putchar('\n');
        return n;
    }
}