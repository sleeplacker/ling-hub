
#include <stdio.h>
#define IN 1  /* 在单词内 */
#define OUT 2 /* 在单词外*/

/* 本程序为：单词计数程序 */

/* 问题为：你准备如何测试单词计数程序？
如果程序中存在某种错误，
那么什么样的输入是可能发现这类错误呢？ */

/* 答案：
需要包含下面这些边界测试：
——没有输入
——没有单词，只有换行符
——没有单词，只有空格、制表符和换行符
——每个单词各占一行打情况，没有空格和制表符
——单词出现于文本行行首的情况
——单词出现于于一串空格之后的情况 */
int main()
{
    int c, nl, nw, nc, state;
    state=OUT;//初始时在单词外
    nl = nw = nc = 0;
    while ((c = getchar()) != EOF)
    {
        ++nc;
        if (c == '\n')
            ++nl;
        if (c == ' ' || c == '\n' || c == '\t')
            state = OUT;
        else if (state == OUT)
        {
            state = IN;
            ++nw;
        }
    }
    printf("%d %d %d\n", nl, nw, nc);
    return 0;
}
