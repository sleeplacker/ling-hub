#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAXWORDLEN 100

int getword(char *word, int lim);

/* 编译命令：gcc getword.c getch.c */
/* 执行命令：./a.out < text.txt */
int main()
{
    char word[MAXWORDLEN];
    int ret;
    while ((ret = getword(word, MAXWORDLEN)) && ret != EOF)
    {
        if (strlen(word) > 1)
            printf("%s\n", word);
    }

    return 0;
}

int getword(char *word, int lim)
{
    int c, getch(void);
    void ungetch(int);
    char *w = word;

    while (isspace(c = getch()))
        ;
    if (c != EOF)
        *w++ = c;
    if (!isalpha(c))
    {
        *w = '\0';
        return c;
    }
    for (; --lim > 0; w++)
        if (!isalnum(*w = getch()))
        {
            ungetch(*w);
            break;
        }
    *w = '\0';
    return word[0];
}