#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXLINES 100     /* 待排序的最大行数 */
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
void readargs(int argc, char *argv[]);
void error(char *s);
void exit(int);

int pos1 = 0; /* 字段开始位置 */
int pos2 = 0; /* 字段结束位置 */
/* 
增加字段处理功能，以使得排序程序可以根据行内的不同
字段进行排序，每个字段按照一个单独的选项集合进行排
序。（在对本书索引进行排序时，索引条目使用了-df选项
，而对页码排序使用了-n选项。）
*/
/* 编译命令：gcc qsort.c lines.c numcmp.c substr.c -fno-builtin-qsort*/
int main(int argc, char *argv[])
{
    char *lineptr[MAXLINES];
    int nlines;
    int rc = 0;

    readargs(argc, argv);

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
    int i, j, endpos;
    extern char option;
    extern int pos1, pos2;

    int fold = (options & CASEIGNORE) ? 1 : 0;
    int dir = (options & DIR) ? 1 : 0;

    i = j = pos1;
    if (pos2 > 0)
        endpos = pos2;
    else if ((endpos = strlen(s)) > strlen(t))
        endpos = strlen(t);

    do
    {
        if (dir)
        {
            while (i < endpos && !isalnum(s[i]) && s[i] != ' ' && s[i] != '\0')
                i++;
            while (j < endpos && !isalnum(t[j]) && t[j] != ' ' && t[j] != '\0')
                j++;
        }
        if (i < endpos && j < endpos)
        {
            a = fold ? tolower(s[i]) : s[i];
            i++;
            b = fold ? tolower(t[j]) : t[j];
            j++;
            if (a == b && a == '\0')
                return 0;
        }

    } while (a == b && i < endpos && j < endpos);
    return a - b;
}

void readargs(int argc, char *argv[])
{
    int c;
    int atoi(char *);

    while (--argc > 0 && (c = (*++argv)[0]) == '-' || c == '+')
    {
        if (c == '-' && !isdigit(*(argv[0] + 1)))
            while (c = *++argv[0])
                switch (c)
                {
                case 'd':
                    options |= DIR;
                    break;
                case 'f':
                    options |= CASEIGNORE;
                    break;
                case 'n':
                    options |= NUMERIC;
                    break;
                case 'r':
                    options |= DECR;
                    break;

                default:
                    break;
                }
        else if (c == '-')
            pos2 = atoi(argv[0] + 1);
        else if ((pos1 = atoi(argv[0] + 1)) < 0)
            error("Usage: sort -dfnr [+pos1] [-pos2]");
    }
    if (argc || pos1 > pos2)
        error("Usage: sort -dfnr [+pos1] [-pos2]");
}

void error(char *s)
{
    printf("%s\n", s);
    exit(1);
}
