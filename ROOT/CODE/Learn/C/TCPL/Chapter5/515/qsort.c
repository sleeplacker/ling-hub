#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXLINES 5000 /* 待排序的最大行数 */

char *lineptr[MAXLINES]; /* 指向文本行的指针数组 */

int readlines(char *lineptr[], int nlines);
void writelines(char *lineptr[], int nlines);

void qsort(void *lineptr[], int left, int right, int (*comp)(void *, void *), int reverse);
int numcmp(char *, char *);
int strcmpNoCase(char *, char *);

/* 
增加选项 -f，使得排序过程不考虑字母大小写之间
的区别。例如，比较a和A时认为它们相等。
*/
/* 编译命令：gcc qsort.c lines.c numcmp.c*/
int main(int argc, char *argv[])
{
    int nlines;
    int numeric = 0;
    int reverse = 1;
    int format = 0; /* 不区分大小写标志 */
    int optionlen;  /* 命令行选项的长度，例如 -am 的长度为 3 */

    while (argc-- > 1)
    {
        argv++;
        if ((*argv)[0] == '-')
        {
            optionlen = strlen(*argv);
            while (optionlen-- >= 1)
                switch ((*argv)[optionlen])
                {
                case 'n':
                    numeric = 1;
                    break;
                case 'r':
                    reverse = -1;
                    break;
                case 'f':
                    format = 1;
                    break;

                default:
                    printf("invalid option\n");
                    break;
                }
        }
    }
    if ((nlines = readlines(lineptr, MAXLINES)) >= 0)
    {
        qsort((void **)lineptr, 0, nlines - 1, (int (*)(void *, void *))(numeric ? numcmp : strcmp), reverse);
        writelines(lineptr, nlines);
        return 0;
    }
    else
    {
        printf("input too big to sort\n");
        return 1;
    }
}

void qsort(void *v[], int left, int right, int (*comp)(void *, void *), int reverse)
{
    int i, last;
    void swap(void *v[], int, int);

    if (left >= right) /* 无需排序 */
        return;
    swap(v, left, (left + right) / 2);
    last = left;
    for (i = left + 1; i <= right; i++)
        if (reverse * (*comp)(v[i], v[left]) < 0)
            swap(v, ++last, i);
    swap(v, left, last);
    qsort(v, left, last - 1, comp, reverse);
    qsort(v, last + 1, right, comp, reverse);
}

void swap(void *v[], int i, int j)
{
    void *temp;
    temp = v[i];
    v[i] = v[j];
    v[j] = temp;
}

int strcmpNoCase(char *x, char *y)
{
    for()
    return strcmp(&(toupper(*x)), &(toupper(*y)))
}