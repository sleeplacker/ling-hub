
#include <stdio.h>

int htoi(char s[]);
/* 
编写函数 htoi (s)，把十六进制数字组成的字符串（包含可选的
前缀0x或0X）转换为与之等价的整数值。字符串中允许包含的数字
包括：0~9、a~f以及A~F 
*/
int main()
{
    printf("0x11 = %d\n", htoi("0x11"));
    printf("ff = %d\n", htoi("ff"));
    printf("xxyy = %d\n", htoi("xxyy"));
    return 0;
}

int htoi(char s[])
{
    int i, c, result;
    i = 0;
    result = 0;
    // 处理0x或0X开头的情况
    if (s[i] == '0')
    {
        ++i;
        if (s[i] == 'x' || s[i] == 'X')
            ++i;
        else
            return -1; //返回-1表示转换失败
    }
    while ((c = s[i]) != '\0')
    {
        if (c >= '0' && c <= '9')
            result = result * 16 + c - '0';
        else if (c >= 'a' && c <= 'f')
            result = result * 16 + c - 'a' + 10;
        else if (c >= 'A' && c <= 'F')
            result = result * 16 + c - 'A' + 10;
        else
            return -1;
        ++i;
    }
    return result;
}