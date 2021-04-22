
#include <stdio.h>
#define MAXHIST 15  /* 直方图最大长度 */
#define MINCHAR 'a' /* 最小字符 */
#define MAXCHAR 'z' /* 最大字符 */

/* 编写一个程序，打印输入中各个字符出现频度打直方图 */
int main()
{
    int c, i, j;
    int maxvalue; // wl[] 的最大值
    int overflow; // 超出字符范围打字符数量
    int len = MAXCHAR - MINCHAR + 1;
    int wl[len]; // 字符频度计数器
    overflow = 0;
    for (i = 0; i < len; ++i)
        wl[i] = 0;
    while ((c = getchar()) != EOF)
    {
        if (c >= MINCHAR && c <= MAXCHAR)
            ++wl[c - 'a'];
        else
            ++overflow;
    }
    maxvalue = 0;
    for (i = 1; i < len; ++i)
        if (wl[i] > maxvalue)
            maxvalue = wl[i];
    for (i = MAXHIST; i > 0; --i)
    {
        for (j = 0; j < len; ++j)
            if (wl[j] * MAXHIST / maxvalue >= i)
                printf("   * ");
            else
                printf("     ");
        putchar('\n');
    }
    for (i = 0; i < len; ++i)
        printf("%4c ", i+'a');
    putchar('\n');
    for (i = 0; i < len; ++i)
        printf("%4d ", wl[i]);
    putchar('\n');
    if (overflow > 0)
        printf("There are %d other chars\n", overflow);
    return 0;
}
