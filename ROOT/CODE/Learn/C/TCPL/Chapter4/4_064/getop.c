#include <stdio.h>
#include <ctype.h>
#include "calc.h"
#define MAXLINE 100

int li = 0;
char line[MAXLINE];

/* getop函数：获取下一个运算符或数值操作符 */
int getop(char s[])
{
    int i, c, c1;

    while ((s[0] = c = getch()) == ' ' || c == '\t')
        ;
    s[1] = '\0';
    if (!isdigit(c) && c != '.' && c != '-' && c != '+')
        return c; /* 不是数 */

    i = 0;

    /* 处理正负号 */
    if (c == '-' || c == '+')
    {
        c1 = getch();     /* 取后一个字符 */
        if (!isdigit(c1)) /* +-不是正负号,而是加减号 */
        {
            ungetch(c1);
            return c;
        }
        else /* +-是正负号 */
        {
            s[i] = c;
            c = c1;
            s[++i] = c;
        }
    }

    if (isdigit(c)) /* 收集整数部分 */
        while (isdigit(s[++i] = c = getch()))
            ;
    if (c == '.') /* 收集小数部分 */
        while (isdigit(s[++i] = c = getch()))
            ;
    s[i] = '\0';
    if (c != EOF)
        ungetch(c);
    return NUMBER;
}

/* getopline函数：获取下一个运算符或数值操作符-使用getline取代getch和ungetch */
int getopline(char s[])
{
    int i, c, c1;

    if (line[li] == '\0')
        if (getaline(line, MAXLINE) == 0)
            return EOF;
        else
            li = 0;

    while ((s[0] = c = line[li++]) == ' ' || c == '\t')
        ;
    s[1] = '\0';
    if (!isdigit(c) && c != '.' && c != '-' && c != '+')
        return c; /* 不是数 */

    i = 0;

    /* 处理正负号 */
    if (c == '-' || c == '+')
    {
        c1 = line[li++];  /* 取后一个字符 */
        if (!isdigit(c1)) /* +-不是正负号,而是加减号 */
        {
            --li;
            return c;
        }
        else /* +-是正负号 */
        {
            s[i] = c;
            c = c1;
            s[++i] = c;
        }
    }

    if (isdigit(c)) /* 收集整数部分 */
        while (isdigit(s[++i] = c = line[li++]))
            ;
    if (c == '.') /* 收集小数部分 */
        while (isdigit(s[++i] = c = line[li++]))
            ;
    s[i] = '\0';
    if (c != EOF)
        --li;
    return NUMBER;
}