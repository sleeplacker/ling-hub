#include <stdio.h>
#include <stdlib.h> /* 为了使用atof(函数) */
#include <math.h>
#include <string.h>
#include <ctype.h>

#define MAXOP 100  /* 操作数或运算符的最大长度 */
#define MAXVAL 100 /* 栈val的最大深度 */
#define NUMBER '\0'
int sp = 0;         /* 下一个空闲的位置 */
double val[MAXVAL]; /* 值栈 */

void push(double f);
double pop(void);

/* 
编写程序expr，以计算从命令行输入的逆波兰表达式的值，
其中每个运算符或操作数用一个单独的参数表示。例如，命令
expr 2 3 4 + *
将计算表达式2x(3+4)的值
 */
/* 编译命令：gcc 510.c -lm*/
/* 运行命令：./a.out 33 1 2 + /*/
int main(int argc, char *argv[])
{
    int type;
    double op2;
    char s[MAXOP];

    // while ((type = getop(s)) != EOF)
    while (--argc > 0)
    {
        if (isdigit((*++argv)[0]))
        {
            type = NUMBER;
            strcpy(s, *argv);
        }
        else
            type = (*argv)[0];

        switch (type)
        {
        case NUMBER:
            push(atof(s));
            break;
        case '+':
            push(pop() + pop());
            break;
        case '*':
            push(pop() * pop());
            break;
        case '-':
            op2 = pop();
            push(pop() - op2);
            break;
        case '/':
            op2 = pop();
            if (op2 != 0.0)
                push(pop() / op2);
            else
                printf("error: zero divisor\n");
            break;
        case '%':
            op2 = pop();
            if (op2 != 0.0)
                push(fmod(pop(), op2));
            else
                printf("error: zero divisor\n");
            break;
        default:
            printf("error: unknown command %s\n", s);
            break;
        }
    }
    printf("\t%.8g\n", pop());
    return 0;
}

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
