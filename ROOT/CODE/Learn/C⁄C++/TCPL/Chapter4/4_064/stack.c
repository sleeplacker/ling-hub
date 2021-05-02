#include <stdio.h>

#define MAXVAL 100  /* 栈val的最大深度 */
int sp = 0;         /* 下一个空闲的位置 */
double val[MAXVAL]; /* 值栈 */

/* push函数：把f压入值栈中 */
void push(double f)
{
    if (sp < MAXVAL)
        val[sp++] = f;
    else
        printf("error: stack full, can't push %g\n", f);
}

/* pop函数：弹出并返回栈顶的值 */
double pop(void)
{
    if (sp > 0)
        return val[--sp];
    else
    {
        printf("error: stack empty\n");
        return 0.0;
    }
}

/* top函数：打印栈顶的值 */
double top(void)
{
    if (sp > 0)
        return val[sp];
    else
    {
        printf("error: stack empty\n");
        return 0.0;
    }
}

/* cptop函数：复制栈顶的值 */
void cptop(void)
{
    if (sp > 0)
        if (sp < MAXVAL)
            val[++sp] = val[sp - 1];
        else
            printf("error: stack full, can't copy %g\n");
    else
        printf("error: stack empty\n");
}

/* swaptop函数：交换两个栈顶元素的值 */
void swaptop(void)
{
    if (sp > 2)
    {
        double temp = val[sp];
        val[sp] = val[--sp];
        val[sp] = temp;
    }
    else
        printf("error: stack elements less than 2\n");
}

/* clear函数：清空栈 */
void clear(void)
{
    sp = 0;
}