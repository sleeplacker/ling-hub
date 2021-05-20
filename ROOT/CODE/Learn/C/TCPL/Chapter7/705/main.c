#include <stdio.h>
#include <stdlib.h> /* 为了使用atof(函数) */
#include <math.h>
#include "calc.h"

#define MAXOP 100 /* 操作数或运算符的最大长度 */
#define PI 3.14159265

/* 
改写第4章中的后缀计算器程序，用scanf函数和（或）sscanf函数
实现输入以及数的转换。 
*/
/* 编译命令：gcc main.c getch.c getop.c stack.c -lm -g */
int main()
{
    int i, type, var = 0;
    double op1, op2, v;
    char s[MAXOP];
    double variable[26];

    for (i = 0; i < 26; i++)
        variable[i] = 0.0;

    // while ((type = getop(s)) != EOF)
    while ((type = getop1(s)) != EOF)
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
                push(fmod(pop(), op2));
            else
                printf("error: zero divisor\n");
            break;
        case '?': /* 查看栈顶元素 */
            op2 = pop();
            printf("\t%.8g\n", op2);
            push(op2);
            break;
        case 'c': /* 清空栈 */
            clear();
            break;
        case 'd': /* 复制栈顶元素 */
            op2 = pop();
            push(op2);
            push(op2);
            break;
        case 'w': /* 交换栈顶两个元素 */
            op1 = pop();
            op2 = pop();
            push(op1);
            push(op2);
            break;
        case 's': /* sin 操作 */
            push(sin(pop() * PI / 180));
            break;
        case 'e': /* exp 操作 */
            push(exp(pop()));
            break;
        case 'p': /* pow 操作 */
            op2 = pop();
            push(pow(pop(), op2));
            break;
        case '=':
            pop();
            if (var >= 'A' && var <= 'Z')
                variable[var - 'A'] = pop();
            else
                printf("error: no variable name\n");
            break;
        case '\n':
            v = pop();
            printf("\t%.8g\n", v);
            break;
        default:
            if (type >= 'A' && type <= 'Z')
                push(variable[type - 'A']);
            else if (type == 'v')
                push(v);
            else
                printf("error: unknown command %s\n", s);
            break;
        }
        var = type;
    }

    return 0;
}
