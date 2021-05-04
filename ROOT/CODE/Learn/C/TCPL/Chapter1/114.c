
#include <stdio.h>
#include <ctype.h>
#define MAXHIST 15  /* 直方图最大长度 */
#define MAXCHAR 128 /* 最大字符 */

/* 编写一个程序，打印输入中各个字符出现频度打直方图 */
/*
注意：包含在<ctype.h>中打isprint函数用于判断字符是否可显示
*/
int main()
{
    int c, i;
    int len;
    int maxvalue;    //cc[]中打最大值
    int cc[MAXCHAR]; //字符计数器
    for (i = 0; i < MAXCHAR; ++i)
        cc[i] = 0;
    while ((c = getchar()) != EOF)
        if (c < MAXCHAR)
            ++cc[c];
    maxvalue = 0;
    for (i = 1; i < MAXCHAR; ++i)
        if (cc[i] > maxvalue)
            maxvalue = cc[i];
    for (i = 1; i < MAXCHAR; ++i)
    {
        if (isprint(i))
            printf("%5d - %c - %5d : ", i, i, cc[i]);
        else
            printf("%5d -   - %5d : ", i, cc[i]);
        if (cc[i] > 0)
        {
            if ((len = cc[i] * MAXHIST / maxvalue) <= 0)
                len = 1;
        }
        else
            len = 0;
        while (len > 0)
        {
            putchar('*');
            --len;
        }
        putchar('\n');
    }
    return 0;
}
