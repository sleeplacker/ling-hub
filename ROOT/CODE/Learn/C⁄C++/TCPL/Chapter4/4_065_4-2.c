#define MAXVAL 100 /* 栈val的最大深度 */
int sp = 0;     /* 下一个空闲的位置 */
double val[MAXVAL];/* 值栈 */

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