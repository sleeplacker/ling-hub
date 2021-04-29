#include <stdio.h>
#include <stdlib.h> /* 为了使用atof(函数) */

#define MAXOP 100  /* 操作数或运算符的最大长度 */
#define NUMBER '0' /* 标识找到一个数 */

int getop(char[]);
void push(double);
double pop(void);

/* 逆波兰计算器 */
/* 编译命令：gcc 4_064_4-1.c 4_065_4-2.c 4_066_4-3.c 4_067_4-4.c */
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
