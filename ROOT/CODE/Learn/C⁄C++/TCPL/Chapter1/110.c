
#include <stdio.h>

/* 编写一个将输入复制到输出打程序，
并将其中的制表符替换为\t，
将回退符替换为\b，
将反斜杠替换为\\。
这样可以将制表符和回退符以可见打方式显示出来。 */
int main()
{
    int c;
    while ((c = getchar()) != EOF)
        if (c == '\t')
        {
            putchar('\\');
            putchar('t');
        }
        else if (c == '\b')
        {
            // 在linux命令行下输入回退符打方法是ctrl+h
            putchar('\\');
            putchar('b');
        }
        else if (c == '\\')
        {
            putchar('\\');
            putchar('\\');
        }
        else
        {
            putchar(c);
        }

    return 0;
}
