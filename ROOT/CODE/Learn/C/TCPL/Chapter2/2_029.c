
#include <stdio.h>

/* 常量 */
int main()
{
    /* 
    long 常量的表示
    当字面量的值超过int类型最大值时，会被当作long类型处理;
    当字面量末尾有l或者L时，直接当long类型处理 
    */
    printf("1.long 常量的表示\n");
    printf("10000000 占字节数：%d，是 int 类型\n", sizeof(10000000));
    printf("10000000l 占字节数：%d，是 long 类型\n", sizeof(10000000l));
    printf("10000000L 占字节数：%d，是 long 类型\n", sizeof(10000000L));
    printf("10000000000 占字节数：%d，是 long 类型\n\n", sizeof(10000000000));

    /* 
    有符号和无符号 
    当字面量的值超过int类型最大值，但未超过unsigned int类型的值，
    会被当作long类型处理;所以如果想表示这个范围的unsigned int类型的值，
    必须加上u或U后缀
    */
    printf("2.有符号和无符号\n");
    printf("2147483649 占字节数：%d，是 long 类型\n", sizeof(2147483649));
    printf("2147483649u 占字节数：%d，是 unsigned int 类型\n", sizeof(2147483649u));
    printf("2147483649U 占字节数：%d，是 unsigned int 类型\n", sizeof(2147483649U));
    printf("2147483649UL 占字节数：%d，是 unsigned long 类型\n\n", sizeof(2147483649UL));

    /*
     浮点类型
     无后缀的小数或者指数被当作dobule类型
     后缀f或F表示float类型
     后缀l或L表示dobule类型
     */
    printf("3.浮点类型\n");
    printf("10^2 占字节数：%d，是 double 类型\n", sizeof(1e2));
    printf("1.1 占字节数：%d，是 double 类型\n", sizeof(1.1));
    printf("1.1f 占字节数：%d，是 float 类型\n", sizeof(1.1f));
    printf("1.1F 占字节数：%d，是 float 类型\n", sizeof(1.1F));
    printf("1.1l 占字节数：%d，是 long double 类型\n", sizeof(1.1l));
    printf("1.1L 占字节数：%d，是 long double 类型\n\n", sizeof(1.1L));

    /*
     符号常量
     表示字符A的方法
     */
    printf("4.符号常量\n");
    printf("4.1 表示字符A的方法\n");
    printf("单引号：%c\n", 'A');
    printf("十进制：%c\n", 65);
    printf("八进制：%c\n", 0101);
    printf("十六进制：%c\n", 0x41);
    printf("转义八进制：%c\n", '\101');
    printf("转义十六进制：%c\n\n", '\x41');

    /*
    ANSI C 中的全部转义字符序列
    */
    printf("4.2 全部转义字符序列\n");
    printf("\\a %d 响铃符%c\n", '\a', '\a');
    printf("\\b %d 回退符%c\n", '\b', '\b');
    printf("\\f %d 换页符%c\n", '\f', '\f');
    printf("\\n %d 换行符%c\n", '\n', '\n');
    printf("\\r %d 回车符%c\n", '\r', '\r');
    printf("\\t %d 横向制表符%c\n", '\t', '\t');
    printf("\\v %d 纵向制表符%c\n", '\v', '\v');
    printf("\\\\ %d 反斜杠%c\n", '\\', '\\');
    printf("\\? %d 问号%c\n", '\?', '\?');
    printf("\\\' %d 单引号%c\n", '\'', '\'');
    printf("\\\" %d 双引号%c\n\n", '\"', '\"');

    /*
    字符串常量
    */
    printf("5.字符串常量\n");
    //字符串拼接
    char s[] = "Hello"
               " World"
               "!";
    printf("字符串常量拼接 %s \n", s);
    printf("字符串长度为 %d \n", strlen(s));
    printf("保存字符串的数组长度为 %d \n\n", sizeof(s));

    /*
    枚举常量
    */
    printf("6.枚举常量\n");
    enum bool
    {
        NO,
        YES
    };
    //默认赋值从0开始：0,1,2,3...
    printf("6.1完全默认赋值：\n");
    printf("NO=%d\n", NO);
    printf("YES=%d\n", YES);
    enum months
    {
        JAN = 1,
        FEB,
        MAR
    };
    //类推赋值从最后一个指定值往后递增，这里最后指定的是1,类推序列为：2,3,4...
    printf("6.2类推赋值：\n");
    printf("JAN=%d\n", JAN);
    printf("FEB=%d\n", FEB);
    printf("MAR=%d\n", MAR);

    return 0;
}