#include <stdio.h>
#include <ctype.h>

int getch(void);
void ungetch(int);
int getint(int *pn);

/* 
在上面的例子中，如果符号+和-的后面紧跟的不是数字，
getint函数将把符号视为数字0的有效表达方式。修改该
函数，将这种形式的+或-符号重新写会到输入流中。
 */
/* 编译命令：gcc 501.c getch.c */
int main()
{

    int i;
    int res;
    while ((res = getint(&i)) > 0 && res != '+' && res != '-')
        printf("input integer is %d\n", i);
    if (res == EOF)
        printf("end of file\n");
    else
        printf("not integer\n");

    return 0;
}

int getint(int *pn)
{
    int c, d, sign;
    while (isspace(c = getch()))
        ;
    if (!isdigit(c) && c != EOF && c != '+' && c != '-')
    {
        ungetch(c);
        return 0;
    }
    sign = (c == '-') ? -1 : 1;
    if (c == '+' || c == '-')
    {
        d = c;
        c = getch();
        if (!isdigit(c))
        {
            if (c != EOF)
                ungetch(c);
            ungetch(d);
            return d;
        }
    }

    for (*pn = 0; isdigit(c); c = getch())
        *pn = 10 * *pn + (c - '0');
    *pn *= sign;
    if (c != EOF)
        ungetch(c);
    return c;
}