/* 
采用指针而非数组索引方式是改写前面章节和练习中的某些程序，
例如getline（第1、4章），atoi、itoa以及它们的变体形式
（第2、3、4章），reverse（第3章），strindex、getop
（第4章）等等。
 */

#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define NUMBER '0'

void reverse(char *);
int getop(char *s);
void ungetch(int);

int getline(char *s, int lim)
{
    int c;
    char *t = s;
    while (--lim > 0 && (c = getchar()) != EOF && c != '\n')
        *s++ = c;
    if (c == '\n')
        *s++ = c;
    *s = '\0';
    return s - t;
}

int atoi(char *s)
{
    int n, sign;

    for (; isspace(*s); s++)
        ;
    sign = (*s == '-') ? -1 : 1;
    if (*s == '+' || *s == '-')
        s++;
    for (n = 0; isdigit(*s); s++)
        n = 10 * n + *s - '0';
    return sign * n;
}

void itoa(int n, char *s)
{
    int sign;
    char *t = s;
    if ((sign = n) < 0)
        n = -n;
    do
    {
        *s++ = n % 10 + '0';
    } while ((n /= 10) > 0);
    if (sign < 0)
        *s++ = '-';
    *s = '\0';
    reverse(t);
}

void reverse(char *s)
{
    int c;
    char *t;

    for (t = s + (strlen(s) - 1); s < t; s++, t--)
    {
        c = *s;
        *s = *t;
        *t = c;
    }
}

int strindex(char *s, char *t)
{
    char *b = s;
    char *p, *r;

    for (; *s != '\0'; s++)
    {
        for (p = s, r = t; r != '\0' && *p == *r; p++, r++)
            ;
        if (r > t && *r == '\0')
            return s - b;
    }
    return -1;
}

double atof(char *s)
{
    double val, power;
    int sign;

    for (; isspace(*s); s++)
        ;
    sign = (*s == '-') ? -1 : 1;
    if (*s == '+' || *s == '-')
        s++;
    for (val = 0.0; isdigit(*s); s++)
        val = 10.0 * val + (*s - '0');
    if (*s == '.')
        s++;
    for (power = 1.0; isdigit(*s); s++)
    {
        val = 10.0 * val + (*s - '0');
        power *= 10.0;
    }
    return sign * val / power;
}

int getop(char *s)
{
    int c;
    while ((*s = c = getch()) == ' ' || c == '\t')
        ;
    *(s + 1) = '\0';
    if (!isdigit(c) && c != '.')
        return c;
    if (isdigit(c))
        while (isdigit(*++s = c = getch()))
            ;
    if (c == '.')
        while (isdigit(*++s = c = getch()))
            ;
    *s = '\0';
    if (c != EOF)
        ungetch(c);
    return NUMBER;
}