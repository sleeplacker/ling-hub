#include <stdio.h>
#include <string.h>

#define BUFSIZE 1

int buf[BUFSIZE];
int bufp = 0;

int getch(void)
{
    return (bufp > 0) ? buf[--bufp] : getchar();
}
void ungetch(int c)
{
    if (bufp >= BUFSIZE)
        printf("ungetch: too many characters\n");
    else
        buf[bufp++] = c;
}
void ungets(char s[])
{
    int c, len;
    len = strlen(s);
    if (len > (BUFSIZ - bufp))
        printf("ungets: buffer is not enough\n");
    while (--len >= 0)
    {
        ungetch(s[len]);
    }
}

/* getline函数：将一行读入s中并返回其长度 */
int getaline(char s[], int lim)
{
    int c, i;
    for (i = 0; i < lim - 1 && (c = getchar()) != EOF && c != '\n'; ++i)
        s[i] = c;
    s[i++] = '\n'; /* 这里需要加上换行符，main函数中要等待这个字符来打印信息 */
    s[i] = '\0';
    return i;
}