
#include <stdio.h>
#define MAXLINE 100 //允许的输入行打最大长度

int getaline(char line[], int maxline);
void reverse(char s[]);

/* 编写函数reverse(s)，将字符串s中的字符顺序颠倒过来。使用该函数编写一个程序，每次颠倒一个输入行中的字符顺序 */
int main()
{
    char line[MAXLINE];
    while (getaline(line, MAXLINE) > 0)
    {
        reverse(line);
        printf("%s", line);
    }
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

/*  
标准答案中，只传入了字符数组，
而没有数组长度，所以只能通过遍历
数组查找结束符'\0'来找到数组的最
后一个位置 
*/
void reverse(char s[])
{
    int i, j;
    char temp;
    i = 0;
    // 通过遍历数组查找结束符'\0'来找到数组的最后一个位置
    while (s[i] != '\0')
        ++i;
    --i;
    if (s[i] == '\n')
        --i;
    j = 0;
    while (j < i)
    {
        temp = s[j];
        s[j] = s[i];
        s[i] = temp;
        --i;
        ++j;
    }
}