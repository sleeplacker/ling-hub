#include <stdio.h>
#include <string.h>

void reverse(char s[]);

/* 
编写一个递归版本的reverse(s)函数，以将字符串s倒置。
*/
int main()
{
    char s[50] = "abc de";
    reverse(s);
    printf("%s\n", s);
}

void reverse(char s[])
{
    void reversein(char s[], int i, int j);
    int i, j;
    i = strlen(s);
    --i;
    if (s[i] == '\n')
        --i;
    j = 0;
    reversein(s, i, j);
}

void reversein(char s[], int i, int j)
{
    if (j < i)
    {
        char temp = s[j];
        s[j] = s[i];
        s[i] = temp;
        reversein(s, --i, ++j);
    }
}