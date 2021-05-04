
#include <stdio.h>

int any(char s1[], char s2[]);
/* 
编写函数any(s1,s2)，将字符串s2中的任一字符在字符串s1中第一次出现的位置(从1开始)作
为结果返回。如果s1中不包含s2中的字符，则返回-1。(标准库函数strpbrk具有同样的功能，
但它返回的是指向该位置的指针。) 
*/
int main()
{
    char s1[] = "Hello world";
    char s2[] = "def";
    char s3[] = "abc";
    printf("\"def\"出现在\"Hello world\"中的位置为 %d\n", any(s1, s2));
    printf("\"abc\"出现在\"Hello world\"中的位置为 %d\n", any(s1, s3));
    return 0;
}
int any(char s1[], char s2[])
{
    int i, j;
    for (i = 0; s1[i] != '\0'; ++i)
        for (j = 0; s2[j] != '\0'; ++j)
            if (s1[i] == s2[j])
                return ++i;
    return -1;
}