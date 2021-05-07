#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define DEFLINES 10 /* 默认打印行数 */
#define LINES 100   /* 最大打印行数 */

size_t MAXLEN = 100; /* 每行最大长度 */
void error(char *);
/* 
编写程序tail，将其输入中的最后n行打印出来你。默认
情况下，n的值为10，单可以通过一个可算参数改变n的值，
因此，命令
tail -n
将打印其输入的最后n行。无论输入或n的值是否合理，该
程序都应该能正常运行。编写程序要充分地利用存储空间，
输入的存储方式应该同5.6节中排序程序的存储方式一样，
而不是采用固定长度的二维数组。
*/
int main(int argc, char *argv[])
{
    char *p;
    char *buf;
    char *bufend;
    char *line = malloc(MAXLEN);
    char *lineptr[LINES];
    int first, i, last, len, n, nlines;

    /* 打印行数 */
    if (argc == 1)
        n = DEFLINES;
    else if (argc == 2 && (*++argv)[0] == '-')
        n = atoi(argv[0] + 1);
    else
        error("usage: tail [-n]");

    /* 处理不合法的行参数 */
    if (n < 1 || n > LINES)
        n = LINES;

    /* 初始化行指针数组 */
    for (i = 0; i < LINES; ++i)
        lineptr[i] = NULL;

    /* 分配最大需要的空间 */
    if ((p = buf = malloc(LINES * MAXLEN)) == NULL)
        error("tail: cannot allocate buf");

    /* 分配空间的尾部指针 */
    bufend = buf + LINES * MAXLEN;
    last = 0;
    nlines = 0;

    /* 开始读取行 */
    while ((len = getline(&line, &MAXLEN, stdin)) > 0)
    {
        /* 如果缓冲区满，则从缓冲区头部重新开始 */
        if (p + len + 1 >= bufend)
            p = buf;

        /* 将每行第一个字符位置赋值给行指针数组 */
        lineptr[last] = p;

        /* 将行内容复制到缓冲区 */
        strcpy(lineptr[last], line);
        if (++last >= LINES)
            last = 0;
        /* 增加指针的值 */
        p += len + 1;
        /* 增加行数 */
        nlines++;
    }

    /* 如果输入内容的行数比-n选项的n要少，则实际打印输入内容的行数 */
    if (n > nlines)
        n = nlines;
    /* 计算出要打印的第一行的位置 */
    first = last - n;
    /* 第一行的行序号小于0，表明输入内容超过了最大行数，而重新记录到了行指针开头，这时要加上最大行数 */
    if (first < 0)
        first += LINES;
    /* 开始打印，注意：取模运算也是因为输入内容可能会超过最大行数 */
    for (i = first; n-- > 0; i = (i + 1) % LINES)
        printf("%s", lineptr[i]);
    return 0;
}

void error(char *s)
{
    printf("%s\n", s);
    exit(1);
}