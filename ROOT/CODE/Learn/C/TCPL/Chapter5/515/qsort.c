#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXLINES 5000    /* 待排序的最大行数 */
#define NUMERIC 1        /* 数字排序标识 00..0001*/
#define DECR 2           /* 倒序标识 00..0010*/
#define CASEIGNORE 4     /* 不区分大小写标识 00..00100*/
int options = 0;         /* 操作集 */
char *lineptr[MAXLINES]; /* 指向文本行的指针数组 */

int readlines(char *lineptr[], int nlines);
void writelines(char *lineptr[], int nlines);

void qsort(void *lineptr[], int left, int right, int (*comp)(void *, void *));
int numcmp(char *, char *);
int strcmpDecr(char *s1, char *s2);

/* 
增加选项 -f，使得排序过程不考虑字母大小写之间
的区别。例如，比较a和A时认为它们相等。
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
        else if (options & CASEIGNORE)
            qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))strcasecmp);
        else
            qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))strcmp);

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