
#include <stdio.h>
#define MAXLINE 100 //允许的输入行打最大长度

int getaline(char line[], int maxline);
void copy(char to[], char from[]);

/* 编写一个程序，删除每个输入行末尾打空格及制表符，并删除完全是空格的行 */
int main()
{
    int len;            //当前行长度
    int max;            // 目前为止发现的最长行的长度
    char line[MAXLINE]; //当前的输入行

    max = 0;
    while ((len = getaline(line, MAXLINE)) > 0)
        printf("%s|", line); //"|"表示行结束
    return 0;
}

/* getline函数：将一行读入s中并返回其长度 */
int getaline(char s[], int lim)
{
    int c, i;
    for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
        s[i] = c;
    --i; //将i移动到上一个位置，即已读取到数组中的最后一个位置
    //去掉最后的空格和制表符
    while (i >= 0 && (s[i] == ' ' || s[i] == '\t'))
        --i;
    ++i; //将i移动到下一个位置
    if (c == '\n')
    {
        s[i] = c;
        ++i;
    }
    s[i] = '\0';
    return i;
}
