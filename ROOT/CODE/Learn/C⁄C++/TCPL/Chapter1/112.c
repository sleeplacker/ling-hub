
#include <stdio.h>
#define IN 1  /* 在单词内 */
#define OUT 2 /* 在单词外*/

/* 编写一个程序，以每行一个单词打形式打印其输入 */
int main()
{
    int c, state;
    state = OUT; //初始时在单词外
    while ((c = getchar()) != EOF)
    {
        if (c == ' ' || c == '\n' || c == '\t')
        {
            if (state == IN)
            {
                putchar('\n'); //从单词内变为单词外，说明一个单词结束，进行换行
            }
            state = OUT;
        }
        else
        {
            if (state == OUT)
            {
                state = IN;
            }
            putchar(c);
        }
    }
    return 0;
}
