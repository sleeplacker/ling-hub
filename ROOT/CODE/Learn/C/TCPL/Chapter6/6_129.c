#include <stdio.h>
#include <stdlib.h>

struct
{
    char *name; /* 8 byte */
    int flags;  /* 4 byte */
    int utype;  /* 4 byte */
    char c;     /* 1 byte I add */
    union       /* 8 byte */
    {
        int ival;
        float fval;
        char *sval;
    } u;
} symtab[100];

union un
{
    int ival;
    float fval;
    char *sval;
};

/* 地址对齐测试 */
typedef struct ch *cp;
typedef struct
{
    char c;
    // int i, j;
    char d;
    char e;
    char f;
    char g;
} cs;

int main()
{
    /* 类型字节数之和 = 24，然而占用了 32 字节，说明机器是8字节对齐 */
    printf("sizeof struct = %d, address = %d\n", sizeof symtab[0], symtab);

    /* 联合引用和结构引用一样 */
    symtab[0].u.ival = 1;

    /* 联合只能用其第一个成员类型的值进行初始化 */
    union un u1 = {123.1};
    printf("un = %d\n", u1);
    printf("un = %f\n", u1);

    struct ch *c1 = (cp)malloc(sizeof(cs));
    struct ch *c2 = (cp)malloc(sizeof(cs));

    /* 结论：
    1.如果结构中全是char类型，那么整个结构占用的字节数为char类型成员的个数
    2.如果结构中有char以外的类型，例如int，那么结构占用字节数为4的倍数
    3.地址总是4的倍数
     */
    printf("sizeof ch = %d\n", sizeof(cs));
    printf("address of c1 = %d\n", c1);
    printf("address of c2 = %d\n", c2);

    return 0;
}