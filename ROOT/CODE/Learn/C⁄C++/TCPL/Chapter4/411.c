#include <stdio.h>
#include <ctype.h>

#define NUMBER '0'

/* 
修改getop函数，使其不必使用ungetch函数。
提示：可以使用一个static类型的内部变量解决

标准答案：在getop函数中使用一个static int
lastc来保存缓冲字符，然后每次getop操作取第
一个字符时，先检查该lastc是否为0，如果为0则
调用getch函数来取，否则将lastc赋值给第一个
字符。当要压回一个字符时，将要压回字符赋值给
lastc即可
*/

int getop(char s[])
{
    int c, i;
    static int lastc = 0; /* static变量用于保存缓冲字符，该变量在函数结束后仍然存在 */
    if (lastc == 0)
        c = getch();
    else
    {
        c = lastc; /* 取缓冲字符 */
        lastc = 0;
    }
    while ((s[0] = c) == ' ' || c == '\t')
        c = getch();
    s[1] = '\0';
    if (!isdigit(c) && c != '.')
        return c;
    i = 0;
    if (isdigit(c))
        while (isdigit(s[++i] = c = getch()))
            ;
    if (c == '.')
        while (isdigit(s[++i] = c = getch()))
            ;
    s[i] = '\0';
    if (c != EOF)
        lastc = c; /* 压回字符c，存放在lastc中 */
    return NUMBER;
}