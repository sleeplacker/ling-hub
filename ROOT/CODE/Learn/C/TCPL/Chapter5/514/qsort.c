#include <stdio.h>
#include <string.h>

#define MAXLINES 5000 /* 待排序的最大行数 */

char *lineptr[MAXLINES]; /* 指向文本行的指针数组 */

int readlines(char *lineptr[], int nlines);
void writelines(char *lineptr[], int nlines);

void qsort(void *lineptr[], int left, int right, int (*comp)(void *, void *), int reverse);
int numcmp(char *, char *);

/* 
修改排序程序，使它能处理-r标记。该标记表明，
以逆序（递减）方式排序。要保证-r和-n能够组合
在一起使用
*/
/* 编译命令：gcc qsort.c lines.c numcmp.c*/
int main(int argc, char *argv[])
{
    int nlines;
    int numeric = 0;
    int reverse = 1;

    while (argc-- > 1)
    {
        argv++;
        if ((*argv)[0] == '-')
            if ((*argv)[1] == 'n')
                numeric = 1;
            else if ((*argv)[1] == 'r')
                reverse = -1;
        if (strlen(*argv) >= 3)
            if ((*argv)[2] == 'n')
                numeric = 1;
            else if ((*argv)[2] == 'r')
                reverse = -1;
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