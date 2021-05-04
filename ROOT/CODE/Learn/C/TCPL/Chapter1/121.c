
#include <stdio.h>
#define TABINC 8 //tab 间隔的位置

/*
编写程序entab，将空格串替换为最少数量打制表符和空格，但要保持单词之间的间隔不变。
假设制表符终止位与练习1-20打detab程序的情况相同。当使用一个制表符或者一个空格
都可以达到下一个制表符终止位时，选用哪种替换字符比较好？
 */
int main()
{
    int c, nb, nt, pos;
    nb = 0; //需要的空格数量
    nt = 0; //需要的tab数量
    for (pos = 1; (c = getchar()) != EOF; ++pos)
        if (c == ' ') // 遇到空格时不需要打印，只需要记录需要打印打空格和tab的数量
        {
            if (pos % TABINC != 0)
                ++nb;
            else
            {
                nb = 0;
                ++nt;
            }
        }
        else
        {
            for (; nt > 0; --nt) //打印tab
                putchar('\t');
            if (c == '\t') // 遇到tab，则不用打印多余的空格
                nb = 0;
            else //否则打印多余的空格
                for (; nb > 0; --nb)
                    putchar(' ');
            putchar(c);    // 打印当前字符
            if (c == '\n') // 遇到换行符，pos重置为0
                pos = 0;
            else if (c == '\t') // 遇到tab时，需要重新计算pos位置
                pos = pos + (TABINC - (pos - 1) % TABINC) - 1;
        }
    return 0;
}