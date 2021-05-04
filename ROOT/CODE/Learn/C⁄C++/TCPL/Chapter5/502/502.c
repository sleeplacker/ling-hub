#include <stdio.h>
#include <ctype.h>

int getch(void);
void ungetch(int);
int getfloat(double *pn);

/* 
模仿函数getint的实现方法，编写一个读取浮点数的函数
getfloat。getfloat函数的返回值应该是什么类型？

答：返回值也是int类型
 */
/* 编译命令：gcc 502.c getch.c */
int main()
{

    double d;
    int res;
    while ((res = getfloat(&d)) > 0 && res != '+' && res != '-')
        printf("input float is %f\n", d);
    if (res == EOF)
        printf("end of file\n");
    else
        printf("not float\n");

    return 0;
}

int getfloat(double *pn)
{
    int c, d, sign;
    double power;
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
    if (c == '.')
        c = getch();
    for (power = 1.0; isdigit(c); c = getch())
    {
        *pn = 10.0 * *pn + (c - '0');
        power *= 10.0;
    }
    *pn *= sign / power;
    if (c != EOF)
        ungetch(c);
    return c;
}

// double atof(char s[])
// {
//     double val, power;
//     int i, sign, sign1, times;

//     for (i = 0; isspace(s[i]); i++)
//         ;
//     sign = (s[i] == '-') ? -1 : 1;
//     if (s[i] == '+' || s[i] == '-')
//         i++;
//     for (val = 0.0; isdigit(s[i]); i++)
//         val = 10.0 * val + (s[i] - '0');
//     if (s[i] == '.')
//         i++;
//     for (power = 1.0; isdigit(s[i]); i++)
//     {
//         val = 10.0 * val + (s[i] - '0');
//         power *= 10.0;
//     }
//     if (s[i] == 'e' || s[i] == 'E')
//     {
//         ++i;
//         sign1 = s[i] == '-' ? -1 : 1;
//         if (s[i] == '+' || s[i] == '-')
//             ++i;
//         for (times = 0; isdigit(s[i]); ++i)
//             times = times * 10 + s[i] - '0';
//         while (times-- > 0)
//         {
//             if (sign1 == 1)
//                 val *= 10.0;
//             else
//                 power *= 10.0;
//         }
//     }

//     return sign * val / power;
// }