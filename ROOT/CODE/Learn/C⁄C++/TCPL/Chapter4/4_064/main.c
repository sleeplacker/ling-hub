#include <stdio.h>
#include <stdlib.h> /* 为了使用atof(函数) */
#include <math.h>
#include "calc.h"

#define MAXOP 100 /* 操作数或运算符的最大长度 */
#define PI 3.14159265

/* 逆波兰计算器 */
/* 编译命令：gcc main.c getch.c getop.c stack.c -lm */
int main()
{
    int type;
    double op2;
    char s[MAXOP];

    while ((type = getop(s)) != EOF)
    {
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
                push((int)pop() % (int)op2);
            else
                printf("error: zero divisor\n");
            break;
        case 's': /* sin 操作 */
            push(sin(pop()*PI/180));
            break;
        case 'e': /* exp 操作 */
            push(exp(pop()));
            break;
        case 'p': /* pow 操作 */
            op2 = pop();
            push(pow(pop(), op2));
            break;
        case '\n':
            printf("\t%.8g\n", pop());
            break;
        default:
            printf("error: unknown command %s\n", s);
            break;
        }
    }

    return 0;
}
