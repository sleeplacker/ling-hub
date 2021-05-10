#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXLINES 5000    /* 待排序的最大行数 */
#define NUMERIC 1        /* 数字排序标识 00..0001*/
#define DECR 2           /* 倒序标识 00..0010*/
#define CASEIGNORE 4     /* 不区分大小写标识 00..00100*/
#define DIR 8            /* 目录排序标识 00..001000*/
int options = 0;         /* 操作集 */
char *lineptr[MAXLINES]; /* 指向文本行的指针数组 */

int readlines(char *lineptr[], int nlines);
void writelines(char *lineptr[], int nlines);

void qsort(void *lineptr[], int left, int right, int (*comp)(void *, void *));
int numcmp(char *, char *);
int strcmpDecr(char *s1, char *s2);
int charcmp(char *s, char *t);
/* 
增加选项 -d（代表目录顺序）。该选择表明，只对字母、数字和
空格进行比较。要保证选项可以和-f组合在一起使用。
*/
/* 编译命令：gcc qsort.c lines.c numcmp.c*/
int main(int argc, char *argv[])
{
    int nlines;
    while (argc-- > 1)
    {
        argv++;
        if ((*argv)[0] == '-')
        {
            while (*(++argv[0]))
                switch (*argv[0])
                {
                case 'n':
                    options |= NUMERIC;
                    break;
                case 'r':
                    options |= DECR;
                    break;
                case 'f':
                    options |= CASEIGNORE;
                    break;
                case 'd':
                    options |= DIR;
                    break;
                default:
                    printf("invalid option\n");
                    break;
                }
        }
    }
    if ((nlines = readlines(lineptr, MAXLINES)) >= 0)
    {

        if (options & NUMERIC)
            qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))numcmp);
        else if (options & DECR)
            qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))strcmpDecr);
        else
            qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))charcmp);

        writelines(lineptr, nlines);
        return 0;
    }
    else
    {
        printf("input too big to sort\n");
        return 1;
    }
}

void qsort(void *v[], int left, int right, int (*comp)(void *, void *))
{
    int i, last;
    void swap(void *v[], int, int);

    if (left >= right) /* 无需排序 */
        return;
    swap(v, left, (left + right) / 2);
    last = left;
    for (i = left + 1; i <= right; i++)
        if ((*comp)(v[i], v[left]) < 0)
            swap(v, ++last, i);
    swap(v, left, last);
    qsort(v, left, last - 1, comp);
    qsort(v, last + 1, right, comp);
}

void swap(void *v[], int i, int j)
{
    void *temp;
    temp = v[i];
    v[i] = v[j];
    v[j] = temp;
}

/* 字符串逆序排序 */
int strcmpDecr(char *s1, char *s2)
{
    return -strcmp(s1, s2);
}

int charcmp(char *s, char *t)
{
    char a, b;
    int fold = (options & CASEIGNORE) ? 1 : 0;
    int dir = (options & DIR) ? 1 : 0;

    do
    {
        if (dir)
        {
            /* 跳过非目录字符 */
            while (!isalnum(*s) && *s != ' ' && *s != '\0')
                s++;
            while (!isalnum(*t) && *t != ' ' && *t != '\0')
                t++;
        }
        a = fold ? tolower(*s) : *s;
        s++;
        b = fold ? tolower(*t) : *t;
        t++;
        if (a == b && a == '\0')
            return 0;
    } while (a == b);
    return a - b;
}