#include <stdio.h>

#define MAXLINE 100
#define TABINC 8 //tab 间隔的位置
#define YES 1
#define NO 0

void entab(char *tab);
void settab(int argc, char *argv[], char *tab);
int tabpos(int pos, char *tab);

/* 
修改程序entab和detab（第1章练习中编写的函数），
使它们接受一组作为参数的制表符停止位。如果启动程序
时不带参数，则使用默认的制表符停止位设置。
*/
/* 编译命令：gcc entab.c settab.c tabpos.c -o entab */
/* 运行命令：./entab 4 8 16 20 */
int main(int argc, char *argv[])
{
    char tab[MAXLINE + 1];/* tab 中值为1的元素标明了哪些位置应出现\t */

    settab(argc, argv, tab);
    entab(tab);
    return 0;
}

void entab(char *tab)
{
    int c, pos;
    int nb = 0;
    int nt = 0;
    for (pos = 1; (c = getchar()) != EOF; pos++)
        if (c == ' ')
        {
            if (tabpos(pos, tab) == NO)
                ++nb;
            else
            {
                nb = 0;
                ++nt;
            }
        }
        else
        {
            for (; nt > 0; nt--)
                putchar('\t');
            if (c == '\t')
                nb = 0;
            else
                for (; nb > 0; nb--)
                    putchar(' ');
            putchar(c);
            if (c == '\n')
                pos = 0;
            else if (c == '\t')
                while (tabpos(pos, tab) != YES)
                    ++pos;
        }
}
