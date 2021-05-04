
#include <stdio.h>

int expand(char s1[], char s2[]);

/* 
编写函数expand(s1,s2)，将字符串s1中类似于a-z一类的
速记符号在字符串s2中扩展为等价的完整列表abc...xyz。
该函数可以处理大小写字母和数字，并可以处理a-b-c、a-z0-9
与-a-z等类似的情况。作为前导和尾随的-字符原样排印。
*/
int main()
{
    char s1[] = "-a-zC-K0-4-";
    // char s1[] = "-a-zC-K0-4-]";//格式错误
    char s2[200];
    if (expand(s1, s2) == 0)
        printf("%s\n", s2);
    else
        printf("格式错误\n");
    return 0;
}

int expand(char s1[], char s2[])
{
    int i, j, c, inFlag, start;
    start = -1;
    //首先检查是否存在不合法字符
    for (i = 0; (c = s1[i]) != '\0'; ++i)
        if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '-'))
            return -1;

    for (i = 0, j = 0, inFlag = 0; (c = s1[i]) != '\0'; ++i)
    {

        if (c == '-') //处理-
        {
            if (start == -1 || s1[i + 1] == '\0') //前置/尾随的-
                s2[j++] = c;
        }
        else //处理字母和数字
        {
            if (start == -1 || inFlag == 0)
            {
                start = c;
                s2[j++] = c;
                inFlag = 1; //设置进入标志
            }
            else
            {
                for (start += 1; start <= c; ++start)
                    s2[j++] = start;
                if (s1[i + 1] != '\0' && s1[i + 1] != '-') //如果下一个字符又是一个开始，则设置退出标志
                    inFlag = 0;
            }
        }
    }
    s2[j] = '\0';
    return 0;
}