
#include <stdio.h>
#define MAXLINE 100 //允许的输入行打最大长度

int getaline(char line[], int maxline);
void reverse(char s[], int len);

/* 编写函数reverse(s)，将字符串s中的字符顺序颠倒过来。使用该函数编写一个程序，每次颠倒一个输入行中的字符顺序 */
int main()
{
    int len;            //当前行长度
    int max;            // 目前为止发现的最长行的长度
    char line[MAXLINE]; //当前的输入行

    max = 0;
    while ((len = getaline(line, MAXLINE)) > 0)
    {
        reverse(line, len + 1);
        printf("%s\n", line);
    }

    return 0;
}

/* getline函数：将一行读入s中并返回其长度 */
int getaline(char s[], int lim)
{
    int c, i;
    for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
        s[i] = c;
    s[i] = '\0';
    return i;
}

void reverse(char s[], int len)
{
    int i;
    char temp;
    if (len <= 2)
        return; //字符串长度不超过1时(字符数组不超过2，因为有最后打'\0'),无需做颠倒操作
    for (i = 0; i < len / 2; ++i)
    {
        temp = s[i];
        s[i] = s[len - i - 2];
        s[len - i - 2] = temp;
    }
}