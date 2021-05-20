#include <stdarg.h>
#include <stdio.h>
#include <ctype.h>

#define LOCALFMT 100
void minprintf(char *fmt, ...);

/* 改写minprinft函数，使它能完成printf函数的更多功能。 */
int main()
{
    minprintf("%d,%i\n", 1, 2);
    minprintf("%u\n", -1);
    minprintf("%x,%X\n", 15, 19);
    minprintf("%o\n", 15);
    minprintf("%f\n", 1.0 / 3);
    minprintf("%s\n", "aabb");
    return 0;
}

void minprintf(char *fmt, ...)
{
    va_list ap; /* 依次指向每个无名参数 */
    char *p, *sval;
    char localfmt[LOCALFMT];
    int i, ival;
    unsigned int uival;
    double dval;

    va_start(ap, fmt); /* 将ap指向第一个无名参数 */
    for (p = fmt; *p; p++)
    {
        if (*p != '%')
        {
            putchar(*p);
            continue;
        }
        i = 0;
        localfmt[i++] = '%';
        while (*(p + 1) && !isalpha(*(p + 1)))
            localfmt[i++] = *++p;
        localfmt[i++] = *(p + 1);
        localfmt[i] = '\0';
        switch (*++p)
        {
        case 'd':
        case 'i':
            ival = va_arg(ap, int);
            printf(localfmt, ival);
            break;
        case 'X':
        case 'x':
        case 'u':
        case 'o':
            uival = va_arg(ap, unsigned);
            printf(localfmt, uival);
            break;
        case 'f':
            dval = va_arg(ap, double);
            printf(localfmt, dval);
            break;
        case 's':
            sval = va_arg(ap, char *);
            printf(localfmt, sval);
            break;

        default:
            break;
        }
    }
    va_end(ap); /* 结束时的清理工作 */
}