
#include <stdio.h>
#include <limits.h>

void itoa(int n, char s[], int minLen);
void reverse(char s[]);

/* 
修改itoa函数，使得该函数可以接收三个参数。其中，
第三个参数为最小段宽度。为了保证转换后所得的结果
至少具有第三个参数制定的最小宽度，在必要时应在所
得结果的左边填充一定的空格。
*/
int main()
{
    char s1[50];
    char s2[50];
    char s3[50];
    itoa(100, s1, 20);
    itoa(10000, s2, 20);
    itoa(INT_MAX, s3, 20);
    printf("100     to string is %s\n", s1);
    printf("10000   to string is %s\n", s2);
    printf("INT_MAX to string is %s\n", s3);
    return 0;
}

void itoa(int n, char s[], int minLen)
{
    int i, sign;
    unsigned un = n;
    if ((sign = n) < 0)
        un = -n;
    i = 0;
    do
    {
        s[i++] = un % 10 + '0';
    } while ((un /= 10) > 0);
    if (sign < 0)
        s[i++] = '-';

    // 在s末尾补空格，反转后空格即在最前
    while (i < minLen)
        s[i++] = ' ';

    s[i] = '\0';
    reverse(s);
}

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