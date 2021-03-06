#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXTOKEN 100

enum
{
    NAME,
    PARENS,
    BRACKETS
};

void undcl(void);

int gettoken(void);
int tokentype;
int pretokentype; /* 保存上一个标记类型 */
char token[MAXTOKEN];
char name[MAXTOKEN];
char datatype[MAXTOKEN];
char out[1000];

/* 修改undcl程序，使它在把文字描述转换为声明的过程中
不会生成多余的圆括号。 */
/* 编译命令：gcc undcl.c getch.c */
/* 运行示例：
    函数含义：x is a function returning a pointer
    to an array of pointers to functions returning
    char
    运行输入内容：x () * [] * () char 
*/
int main()
{
    undcl();
    return 0;
}

int gettoken(void)
{
    int c, getch(void);
    void ungetch(int);
    char *p = token;

    pretokentype = tokentype; /* 保存上次标记类型 */

    while ((c = getch()) == ' ' || c == '\t')
        ;
    if (c == '(')
    {
        if ((c = getch()) == ')')
        {
            strcpy(token, "()");
            return tokentype = PARENS;
        }
        else
        {
            ungetch(c);
            return tokentype = '(';
        }
    }
    else if (c == '[')
    {
        for (*p++ = c; (*p++ = getch()) != ']';)
            ;
        *p = '\0';
        return tokentype = BRACKETS;
    }
    else if (isalpha(c))
    {
        for (*p++ = c; isalnum(c = getch());)
            *p++ = c;
        *p = '\0';
        ungetch(c);
        return tokentype = NAME;
    }
    else
        return tokentype = c;
}

void undcl(void)
{
    int type;
    char temp[MAXTOKEN];

    while (gettoken() != EOF)
    {
        strcpy(out, token);
        while ((type = gettoken()) != '\n')
            if (type == PARENS || type == BRACKETS)
            {
                /* 
                如果圆括号或方括号(即函数或数组，这两种运算符都比
                指针运算符优先级高)的上一个标记类型时指针，才需要
                在指针标记左右加圆括号 
                */
                if (pretokentype == '*')
                {
                    sprintf(temp, "(%s)", out);
                    strcpy(out, temp);
                }
                strcat(out, token);
            }
            else if (type == '*')
            {

                sprintf(temp, "*%s", out);
                strcpy(out, temp);
            }
            else if (type == NAME)
            {
                sprintf(temp, "%s %s", token, out);
                strcpy(out, temp);
            }
            else
                printf("invalid input at %s\n", token);
        printf("%s\n", out);
    }
}