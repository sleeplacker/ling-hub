
#include <stdio.h>

int lower(int c);
/* 
重新编写将大写字母转换为小写字母的函数lower，
并用条件表达式替代其中的if-else结构。
*/
int main()
{

    printf("%c 转换成小写字母 = %c\n", '\x45', lower(0x45));
    return 0;
}
int lower(int c)
{
    return (c >= 'A' && c <= 'Z' ? c + 'a' - 'A' : c);
}