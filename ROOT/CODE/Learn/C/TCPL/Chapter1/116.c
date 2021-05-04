
#include <stdio.h>
#define MAXLINE 5 //允许打输入行打最大长度

int getaline(char line[], int maxline);
void copy(char to[], char from[]);

/* 修改打印最长文本行打程序打主程序main，使之可以打印任意长度的输入行的长度，并尽可能多地打印文本 */
int main()
{
    int len;               //当前行长度
    int max;               // 目前为止发现的最长行的长度
    char line[MAXLINE];    //当前的输入行
    char longest[MAXLINE]; //用于保存最长的行

    max = 0;
    while ((len = getaline(line, MAXLINE)) > 0)
    {
        printf("%d, %s", len, line); //注意，这里打len可能会超过MAXLINE
        if (len > max)
        {
            max = len;
            copy(longest, line);
        }
    }

    if (max > 0) //存在这样的行
        printf("%s", longest);
    return 0;
}

/* getline函数：将一行读入s中并返回其长度 */
int getaline(char s[], int lim)
{
    int c, i, j;
    j=0;
    for (i = 0; (c = getchar()) != EOF && c != '\n'; ++i)
        if (i < lim - 2)
        {
            s[j] = c;
            ++j;
        }
    if (c == '\n')
    {
        s[j] = c;
        ++j;
        ++i;
    }
    s[j] = '\0';
    return i;
}

/* copy函数：将from复制到to，这里假定to足够大 */
void copy(char to[], char from[])
{
    int i;
    i = 0;
    while ((to[i] = from[i]) != '\0')
        ++i;
}