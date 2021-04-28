
#include <stdio.h>
#include <limits.h>

void itob(int n, char s[], int b);
void reverse(char s[]);

/* 
编写函数itob(n,s,b)，将正数n转换为以b为底的数，
并将转换结果以字符的形式保存到字符串s中。例如，
itob(n,s,16)把整数n格式化为十六进制整数保存在s中。
*/
int main()
{
    char s1[50];
    char s2[50];
    char s3[50];
    char s4[50];
    char s5[50];
    itob(INT_MAX, s1, 2);
    itob(INT_MAX, s2, 4);
    itob(INT_MAX, s3, 8);
    itob(INT_MAX, s4, 16);
    itob(INT_MAX, s5, 20);
    printf("2进制 INT_MAX to string is %s\n", s1);
    printf("4进制 INT_MIN to string is %s\n", s2);
    printf("8进制 INT_MIN to string is %s\n", s3);
    printf("16进制 INT_MIN to string is %s\n", s4);
    printf("20进制 INT_MIN to string is %s\n", s5);
    return 0;
}

void itob(int n, char s[], int b)
{
    int i, sign;
    unsigned un = n;
    if ((sign = n) < 0)
        un = -n;
    i = 0;
    do
    {
        s[i++] = un % b < 10 ? un % b + '0' : un % b + 'a' - 10;
    } while ((un /= b) > 0);
    if (sign < 0)
        s[i++] = '-';
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