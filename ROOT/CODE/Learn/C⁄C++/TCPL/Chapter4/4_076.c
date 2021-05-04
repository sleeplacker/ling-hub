#include <stdio.h>

#define MULTILINE "line1 aaa\
line2 bbb\
line3 ccc"

/* 
验证：多行宏定义
*/
int main()
{
    printf("%s\n", MULTILINE);
}
