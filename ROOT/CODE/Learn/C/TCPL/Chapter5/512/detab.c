#include <stdio.h>

#define MAXLINE 100
#define TABINC 8 //tab 间隔的位置
#define YES 1
#define NO 0

void detab(char *tab);
void settab(int argc, char *argv[], char *tab);
int tabpos(int pos, char *tab);

/* 编译命令 gcc detab.c settab.c tabpos.c -o detab */
/* 运行命令：./detab -1 +4 */
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
    int i;
    for (i = 0; i <= MAXLINE; ++i)
        printf("%c", tab[i] + '0');
    printf("\n");
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