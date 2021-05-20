#include <stdarg.h>
#include <stdio.h>
#include <ctype.h>

#define LOCALFMT 100
void minscanf(char *fmt, ...);
/* 类似于上一节中的函数minprintf，编写scanf函数的一个简化版本。 */
/* 输入：1 2 1.23 0xff 011 aaa bbb ccc */
/* 输出：1-2-1.230000-255-9-aaa ，注意字符串读取到空格字符会停止*/
int main()
{
    int i, j, k, l;
    float d;
    // double d;/* 这里必须时float类型，不能用double类型，否则读不到小数 */
    char s[10];
    minscanf("%d %i %f %x %o %s", &i, &j, &d, &k, &l, s);
    printf("%d-%i-%f-%d-%d-%s\n", i, j, d, k, l, s);
    return 0;
}

void minscanf(char *fmt, ...)
{
    va_list ap; /* 依次指向每个无名参数 */
    char *p, *sval;
    char localfmt[LOCALFMT];
    int c, i, *ival;
    unsigned int *uival;
    double *dval;
    i = 0;
    va_start(ap, fmt); /* 将ap指向第一个无名参数 */
    for (p = fmt; *p; p++)
    {
        if (*p != '%')
        {
            localfmt[i++] = *p;
            continue;
        }
        localfmt[i++] = '%';
        while (*(p + 1) && !isalpha(*(p + 1)))
            localfmt[i++] = *++p;
        localfmt[i++] = *(p + 1);
        localfmt[i] = '\0';
        switch (*++p)
        {
        case 'd':
        case 'i':
            ival = va_arg(ap, int *);
            scanf(localfmt, ival);
            break;
        case 'X':
        case 'x':
        case 'u':
        case 'o':
            uival = va_arg(ap, unsigned *);
            scanf(localfmt, uival);
            break;
        case 'f':
            dval = va_arg(ap, double *);
            scanf(localfmt, dval);
            break;
        case 's':
            sval = va_arg(ap, char *);
            scanf(localfmt, sval);
            break;

        default:
            scanf(localfmt);
            break;
        }
        i = 0;
    }
    va_end(ap); /* 结束时的清理工作 */
}