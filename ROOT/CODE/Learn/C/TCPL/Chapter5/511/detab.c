#include <stdio.h>

#define MAXLINE 100
#define TABINC 8 //tab 间隔的位置
#define YES 1
#define NO 0

void detab(char *tab);
void settab(int argc, char *argv[], char *tab);
int tabpos(int pos, char *tab);

/* 
修改程序entab和detab（第1章练习中编写的函数），
使它们接受一组作为参数的制表符停止位。如果启动程序
时不带参数，则使用默认的制表符停止位设置。
*/
/* 编译命令 gcc detab.c settab.c tabpos.c -o detab */
/* 运行命令：./detab 4 8 16 20 24 */
int main(int argc, char *argv[])
{
    char tab[MAXLINE + 1];
    int i;
    settab(argc, argv, tab);
    detab(tab);
    return 0;
}

void detab(char *tab)
{
    int c, pos = 1;

    while ((c = getchar()) != EOF)
        if (c == '\t')
        {
            do
                putchar(' ');
            while (tabpos(pos++, tab) != YES);
        }
        else if (c == '\n')
        {
            putchar(c);
            pos = 1;
        }
        else
        {
            putchar(c);
            ++pos;
        }
}