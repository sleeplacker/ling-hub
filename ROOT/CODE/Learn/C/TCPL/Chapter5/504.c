#include <stdio.h>

int strend(char *s, char *t);

/* 
编写函数strend(s,t)。如果字符串t出现在
字符串s的尾部，该函数返回1；分钟返回0.
*/
int main()
{
    char s[20] = "abcdef";
    char t[20] = "cdef";
    printf("abcdef end with cdef ？ %s\n", (strend(s, t) ? "YES" : "NO"));
    return 0;
}

int strend(char *s, char *t)
{
    int lent = 0;
    while (*s++)
        ;
    while (*t++)
        ++lent;
    --s;
    --t;
    while (*t-- == *s-- && lent > 0)
        --lent;
    if (lent == 0)
        return 1;
    return 0;
}