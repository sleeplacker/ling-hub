
#include <stdio.h>
#define MAXLINE 100 //允许的输入行打最大长度
#define PRINTLEN 80 //需要打印打行打长度

int getaline(char line[], int maxline);
void copy(char to[], char from[]);

/* 编写一个程序，打印长度大于80个字符打所有输入行。 */
int main()
{
    int len;            //当前行长度
    int max;            // 目前为止发现的最长行的长度
    char line[MAXLINE]; //当前的输入行

    max = 0;
    while ((len = getaline(line, MAXLINE)) > 0)
        if (len > PRINTLEN)
            printf("%s", line); //注意，这里打len可能会超过MAXLINE
    return 0;
}

/* getline函数：将一行读入s中并返回其长度 */
int getaline(char s[], int lim)
{
    int c, i, j;
    j = 0;
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