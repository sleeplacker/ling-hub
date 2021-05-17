#include <stdio.h>

/* 格式化输出 */
int main()
{
    double d;
    char s[10];
    /* 各种输出格式 */
    printf("123的十进形式：%d,%i\n", 123, 123);              /* 十进制数 */
    printf("123的八进制形式：%o\n", 123);                    /* 八进制数 */
    printf("123的十六进制形式：%x,%X\n", 123, 123);          /* 十六进制，%X表示大写十六进制 */
    printf("-1的无符号形式：%u\n", -1);                      /* 无符号数 */
    printf("单个字符：%c\n", 'a');                           /* 单字符 */
    printf("字符串：%s\n", "abc");                           /* 字符串 */
    printf("浮点数：%f\n", 123.456);                         /* 浮点数 */
    printf("浮点数科学计数法：%e,%E\n", 123.456, 123.456);   /* 浮点数科学计数法 */
    printf("浮点数自动选择：%g,%G\n", 1000000.456, 0.00001); /* 指数小于-4或大于等于精度，用%e或%E格式 */
    printf("浮点数自动选择：%g,%G\n", 100000.456, 0.0001);   /* 否则用%f格式 */
    printf("指针类型：%p\n", &d);                            /* 指针类型 */
    printf("输出百分号：%%\n\n");                            /* 百分号% */

    /* 在参数中指定宽度 */
    printf("\n使用*以在参数中指定格式宽度：%.*s\n", 2, "Hello");

    /* 字符串精度 */
    printf("\n字符串精度\n");
    printf("%%s\t\t\t:%s:\n", "Hello world");
    printf("%%10s\t\t:%10s:\n", "Hello world");
    printf("%%.10s\t\t:%.10s:\n", "Hello world");
    printf("%%-10s\t\t:%-10s:\n", "Hello world");
    printf("%%.15s\t\t:%.15s:\n", "Hello world");
    printf("%%-15s\t\t:%-15s:\n", "Hello world");
    printf("%%15.10s\t\t:%15.10s:\n", "Hello world");
    printf("%%-15.10s\t:%-15.10s:\n\n", "Hello world");

    /* 打印字符串时要小心，如果直接打印而不使用格式化，那么字符串中不能出现% */
    char str1[10] = "abc%%123\n";
    char str2[10] = "abc%def\n";
    /* 字符串中有%时，可能将百分号和后面的字符识别为格式字符串，从而打印出奇怪内容 */
    printf(str1);
    printf(str2);
    /* 比较保险的方法是使用%s格式来打印字符串 */
    printf("%s", str1);
    printf("%s", str2);

    /* 将格式化输内容赋值给字符串 */
    printf("\nsprintf将格式化输内容赋值给字符串\n");
    sprintf(s, "%d", 666);
    printf("%s\n", s);
    return 0;
}