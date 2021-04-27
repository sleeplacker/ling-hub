
#include <stdio.h>

void expand(char s1[], char s2[]);

/* 
编写函数expand(s1,s2)，将字符串s1中类似于a-z一类的
速记符号在字符串s2中扩展为等价的完整列表abc...xyz。
该函数可以处理大小写字母和数字，并可以处理a-b-c、a-z0-9
与-a-z等类似的情况。作为前导和尾随的-字符原样排印。
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

void expand(char s1[], char s2[])
{
    int i, c, inFlag, start, end;
    start = end = -1;
    for (i = 0, inFlag = 0; (c = s1[i]) != '\0'; ++i)
    {

        if (c == '-')
            if (inFlag)

                else
    }
}