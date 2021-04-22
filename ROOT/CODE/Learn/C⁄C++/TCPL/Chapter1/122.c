
#include <stdio.h>
#define MAXCOL 10 //输入行的最大列数
#define TABINC 8  //tab 间隔的位置

char line[MAXCOL]; //输入行

int exptab(int pos);
int findblnk(int pos);
int newpos(int pos);
void printl(int pos);

/*
编写一个程序，把较长打输入行“折”成短一些的两行或多行，
折行的位置在输入行的第n列之的最后一个非空格之后。
要保证程序能够智能地处理输入行很长以及在指定列前没有空格
或制表符的情况
 */
int main()
{
    int c, pos;

    pos = 0;

    while ((c = getchar()) != EOF)
    {
        line[pos] = c;
        if (c == '\t')
            pos = exptab(pos);
        else if (c == '\n')
        {
            printl(pos);
            pos = 0;
        }
        else if (++pos >= MAXCOL)
        {
            pos = findblnk(pos);
            printl(pos);
            pos = newpos(pos);
        }
    }
    return 0;
}

void printl(int pos)
{
    int i;
    for (i = 0; i < pos; ++i)
        putchar(line[i]);
    if (pos > 0)
        putchar('\n');
}

int exptab(int pos)
{
    line[pos] = ' ';
    for (++pos; pos < MAXCOL && pos % TABINC != 0; ++pos)
        line[pos] = ' ';
    if (pos < MAXCOL)
        return pos;
    else
    {
        printl(pos);
        return 0;
    }
}

int findblnk(int pos)
{
    while (pos > 0 && line[pos] != ' ')
        --pos;
    if (pos == 0)
        return MAXCOL;
    else
        return pos + 1;
}

int newpos(int pos)
{
    int i, j;
    if (pos <= 0 || pos >= MAXCOL)
        return 0;
    else
    {
        i = 0;
        for (j = pos; j < MAXCOL; ++j)
        {
            line[i] = line[j];
            ++i;
        }
        return i;
    }
}