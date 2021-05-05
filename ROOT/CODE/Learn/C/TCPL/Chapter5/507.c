#include <stdio.h>
#include <string.h>

#define MAXLEN 1000
#define MAXSTOR 5000
int getline(char *, int);

/* 
重写函数readlines，将输入的文本行存储到由main函数
提供的一个数组中，而不是存储到调用alloc分配的存储空
间中。该函数的运行速度比改前快多少？

标准答案：这个版本的readliines函数要比它的原始版本
执行得稍快一些。
 */
int readlines(char *lineptr[], char *linestor, int maxlines)
{
    int len, nlines;
    char line[MAXLEN];
    char *p = linestor;
    char *linestop = linestor + MAXSTOR;

    nlines = 0;
    while ((len = getline(line, MAXLEN)) > 0)
        if (nlines >= maxlines || p + len > linestop)
            return -1;
        else
        {
            line[len - 1] = '\0';
            strcpy(p, line);
            lineptr[nlines++] = p;
            p += len;
        }
    return nlines;
}