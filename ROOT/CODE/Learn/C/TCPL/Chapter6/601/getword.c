#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAXWORDLEN 100

int getword(char *word, int lim);
int getch(void);
void ungetch(int);

/* 上述的getword函数不能正确处理下划线，字符串
常亮、注释及预处理器控制指令。请编写一个更完善的
getword函数 */
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
    int c, d, comment(void);
    char *w = word;

    while (isspace(c = getch()))
        ;
    if (c != EOF)
        *w++ = c;
    if (isalpha(c) || c == '_' || c == '#')
    {
        for (; --lim > 0; w++)
            if (!isalnum(*w = getch()) && *w != '_')
            {
                ungetch(*w);
                break;
            }
    }
    else if (c == '\'' || c == '"')
    {
        for (; --lim > 0; w++)
            if ((*w = getch()) == '\\')
                *++w = getch();
            else if (*w == c)
            {
                w++;
                break;
            }
            else if (*w == EOF)
                break;
    }
    else if (c == '/')
        if ((d = getch()) == '*')
            c = comment();
        else
            ungetch(d);
    *w = '\0';
    return c;
}

int comment(void)
{
    int c;
    while ((c = getch()) != EOF)
        if (c == '*')
            if ((c = getch()) == '/')
                break;
            else
                ungetch(c);
    return c;
}