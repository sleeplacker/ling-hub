
#include <stdio.h>
#define TABINC 8 //tab 间隔的位置

/*
请编写程序detab，将输入中的制表符替换成适当数目打空格，使空格充满到下一个制表符终止位的地方。
假设制表符终止位置是固定的，比如每隔n列就会出现一个制表符终止位。n应该作为变量还是符号变量呢？

答案：这里将n作为符号变量，但有些场景作为变量比较合适
 */
int main()
{
    int c, nb, pos;
    nb = 0;  //需要填充的空格数量
    pos = 1; //当前字符在当前行中的位置
    while ((c = getchar()) != EOF)
    {
        if (c == '\t')
        {
            nb = TABINC - (pos - 1) % TABINC;
            while (nb > 0)
            {
                // 填充空格
                putchar(' ');
                ++pos;
                --nb;
            }
        }
        else if (c == '\n')
        {
            putchar(c);
            pos = 1;
        }
        else
        {
            putchar(c);
            ++pos;
        }
    }
    return 0;
}