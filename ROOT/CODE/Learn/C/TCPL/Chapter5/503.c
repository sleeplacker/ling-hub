#include <stdio.h>

void strcat(char s[], char t[]);

/* 
用指针方式实现第2章中的函数strcat。 
函数strcat(s,t)将t指向的字符串复制
到s指向的字符串的尾部。
*/
/* 编译命令：gcc 503.c -fno-builtin-strcat */
int main()
{
    char s[20] = "ab";
    char t[20] = "cd";
    strcat(s, t);
    printf("ab + cd = %s\n", s);
    return 0;
}

void strcat(char s[], char t[])
{
    int i, j;
    i = j = 0;
    while (s[i] != '\0')
        i++;
    while ((s[i++] = t[j++]) != '\0')
        ;
}