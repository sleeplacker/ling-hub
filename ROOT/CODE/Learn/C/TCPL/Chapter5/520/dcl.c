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
enum
{
    NO,
    YES
};

void dcl(void);
void dirdcl(void);
void errmsg(char *);
int gettoken(void);
int tokentype;           /* 最后一个记号的类型 */
char token[MAXTOKEN];    /* 最后一个记号字符串 */
char name[MAXTOKEN];     /* 标识符名 */
char datatype[MAXTOKEN]; /* 数据类型为char、int 等 */
char out[1000];          /* 输出串 */
int prevtoken = NO;
/* 修改dcl程序的功能，使它能够处理包含其他成分的声明，
例如带有函数参数类型的声明、带有类似于const限定符的声明等。 */
/* 编译命令：gcc dcl.c getch.c parmdcl.c */
int main()
{
    while (gettoken() != EOF) /* 该行的第一个记号是数据类型 */
    {
        strcpy(datatype, token);
        out[0] = '\0';
        dcl(); /* 分析该行的其余部分 */
        if (tokentype != '\n')
            printf("syntax error\n");
        printf("%s: %s %s\n", name, out, datatype);
    }
    return 0;
}
/* 返回下一个标记 */
int gettoken(void)
{
    int c, getch(void);
    void ungetch(int);
    char *p = token;

    if (prevtoken == YES)
    {
        prevtoken = NO;
        return tokentype;
    }

    while ((c = getch()) == ' ' || c == '\t') /* 跳过空格字符 */
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
    else if (isalpha(c)) /* 处理关键字和变量名或函数名 */
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

/* 对一个声明符进行语法分析 */
void dcl(void)
{
    int ns;
    for (ns = 0; gettoken() == '*';) /* 统计字符 * 的个数 */
        ns++;
    dirdcl();
    while (ns-- > 0)
        strcat(out, " pointer to");
}

/* 分析一个直接声明 */
void dirdcl(void)
{
    int type;
    void parmdcl(void);

    if (tokentype == '(') /* 形式为(dcl) */
    {
        dcl();
        if (tokentype != ')')
            errmsg("error: missing )\n");
    }
    else if (tokentype == NAME) /* 变量名 */
    {
        if (name[0] == '\0')
            strcpy(name, token);
    }
    else
        prevtoken = YES;
    while ((type = gettoken()) == PARENS || type == BRACKETS || type == '(')
        if (type == PARENS)
            strcat(out, " function returning");
        else if (type == '(')
        {
            strcat(out, " fuction expecting");
            parmdcl();
            strcat(out, " and returning");
        }
        else
        {
            strcat(out, " array");
            strcat(out, token);
            strcat(out, " of");
        }
}

void errmsg(char *msg)
{
    printf("%s", msg);
    prevtoken = YES;
}
