
#include <stdio.h>

/* 编写一个将输入复制到输出打程序，并将其中连续打多个空格用一个空格代替 */
int main()
{
    int c, lastChar;
    while ((c = getchar()) != EOF)
    {
        // 当最后一个输出字符和准备输出字符不同时为空格时才输出
        if (lastChar != ' ' || c != ' ')
            putchar(c);
        lastChar = c;
    }

    return 0;
}
