
#include <stdio.h>

void escape(char s[], char t[]);

/* 
编写一个函数excape(s,t),将字符串t复制到字符串s中，并在
复制过程中将换行符、制表符等不可见字符分别转换为\n、\t等
相应的可见的转移字符序列。要求使用switch语句。再编写一个
具有反向功能的函数，在复制过程中将转义字符序列转换为实际字符
*/
int main()
{
    char s1[] = "a b \t  c \n defg";
    char s2[20];
    escape(s2, s1);
    printf("s1 = %s\n", s1);
    printf("s2 = %s\n", s2);
    return 0;
}

void escape(char s[], char t[])
{
    int i, j, c;
    for (i = j = 0; (c = t[i]) != '\0'; ++i)
    {
        switch (c)
        {
        case '\n':
            s[j++] = '\\';
            s[j++] = 'n';
            break;
        case '\t':
            s[j++] = '\\';
            s[j++] = 't';
            break;

        default:
            s[j++] = c;
            break;
        }
    }
    s[j] = '\0';
}