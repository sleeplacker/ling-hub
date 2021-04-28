
#include <stdio.h>
#include <limits.h>

#define abs(x) ((x) < 0 ? -(x) : (x))

void itoa(int n, char s[]);
void reverse(char s[]);

/* 
在数的二进制补码表示中，我们编写的itoa函数不能处理最大的负数，
即n等于-(2^(字长-1))的情况，请解释原因。修改该函数，使它在任
何机器上运行时都能打印出正确的值。

解释：因为补码表示的有符号数中，最小的负数没有对应的正数
*/
int main()
{
    char s1[50];
    char s2[50];
    itoa(INT_MAX, s1);
    itoa(INT_MIN, s2);
    printf("INT_MAX to string is %s\n", s1);
    printf("INT_MIN to string is %s\n", s2);
    return 0;
}

/* 标准答案思路：对于负数，直接用负数对10取模，然后将结果转整数 */
void itoa(int n, char s[])
{
    int i, sign;

    sign = n;
    i = 0;
    do
    {
        s[i++] = abs(n % 10) + '0';
    } while ((n /= 10) != 0);
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