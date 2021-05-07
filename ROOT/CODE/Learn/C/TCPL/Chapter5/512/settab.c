#include <stdlib.h>

#define MAXLINE 100
#define TABINC 8 //tab 间隔的位置
#define YES 1
#define NO 0

void settab(int argc, char *argv[], char *tab)
{
    int i, pos, n;
    if (argc <= 1)
        for (i = 1; i <= MAXLINE; i++)
            if (i % TABINC == 0)
                tab[i] = YES;
            else
                tab[i] = NO;
    else if (argc == 3 && argv[1][0] == '-' && argv[2][0] == '+')
    {
        for (i = 1; i <= MAXLINE; i++)
            tab[i] = NO;
        pos = atoi(&(argv[1][1]));
        n = atoi(&(argv[2][1]));
        while (pos >= 0 && pos <= MAXLINE)
            tab[pos += n] = YES;
    }
    else
    {
        for (i = 1; i <= MAXLINE; i++)
            tab[i] = NO;
        while (--argc > 0)
        {
            pos = atoi(*++argv);
            if (pos > 0 && pos <= MAXLINE)
                tab[pos] = YES;
        }
    }
}