#include <stdio.h>
#include <ctype.h>

#define MAXWORDLEN 100

int getword(char *word, int lim);

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