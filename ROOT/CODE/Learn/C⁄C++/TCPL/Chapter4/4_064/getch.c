#include <stdio.h>

#define BUFSIZE 100

char buf[BUFSIZE];
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
    for (len = 0; (c = s[len]) != '\0'; ++len)
        ;
    if (len > (BUFSIZ - bufp))
        printf("ungets: buffer is not enough\n");
    while (--len >= 0)
    {
        ungetch(s[len]);
    }
}