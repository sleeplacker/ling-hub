
#include <stdio.h>
#define MAXHIST 15 /* 直方图最大长度 */
#define MAXWORD 11 /* 单词最大长度 */
#define IN 1       /* 在单词内 */
#define OUT 2      /* 在单词外*/

/* 编写一个程序，打印输入中单词长度打直方图。水平方向打直方图比较容易绘制，垂直方向打直方图要困难写 */
int main()
{
    int c, i, j, nc, state;
    int maxvalue;    // wl[] 的最大值
    int overflow;    // 超出长度的单词数量
    int wl[MAXWORD]; // 单词长度计数器
    state = OUT;
    nc = 0;       //单词字符数
    overflow = 0; //长度长度限制的单词数
    for (i = 0; i < MAXWORD; ++i)
        wl[i] = 0;
    while ((c = getchar()) != EOF)
    {
        if (c == ' ' || c == '\n' || c == '\t')
        {
            state = OUT;
            if (nc > 0)
                if (nc < MAXWORD)
                    ++wl[nc];
                else
                    ++overflow;
            nc = 0;
        }
        else if (state == OUT)
        {
            state = IN;
            nc = 1; //进入新的单词
        }
        else
            ++nc; //插入新单词
    }
    maxvalue = 0;
    for (i = 1; i < MAXWORD; ++i)
        if (wl[i] > maxvalue)
            maxvalue = wl[i];
    for (i = MAXHIST; i > 0; --i)
    {
        for (j = 1; j < MAXWORD; ++j)
            if (wl[j] * MAXHIST / maxvalue >= i)
                printf("   * ");
            else
                printf("     ");
        putchar('\n');
    }
    for (i = 1; i < MAXWORD; ++i)
        printf("%4d ", i);
    putchar('\n');
    for (i = 1; i < MAXWORD; ++i)
        printf("%4d ", wl[i]);
    putchar('\n');
    if (overflow > 0)
        printf("There are %d words >= %d\n", overflow, MAXWORD);
    return 0;
}
