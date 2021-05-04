
#include <stdio.h>
/* 类型转换 */
int main()
{
    long double ld = 1;
    double d = 1;
    float f = 1;
    int i = 1;
    short s = 1;
    char c = 1;
    printf("1.有符号类型数的转换\n");
    printf("表达式中包含 long double 所有操作数被转换为 long double 类型，占用字节数： %d\n", sizeof(ld + d + f + i + s + c));
    printf("否则，表达式中包含 double 所有操作数被转换为 double 类型，占用字节数： %d\n", sizeof(d + f + i + s + c));
    printf("否则，表达式中包含 float 所有操作数被转换为 float 类型，占用字节数： %d\n", sizeof(f + i + s + c));
    printf("否则，表达式中包含 int 所有操作数被转换为 int 类型，占用字节数： %d\n", sizeof(i + s + c));
    printf("否则，表达式中包含 short 或 char 所有操作数被转换为 int 类型，占用字节数： %d\n\n", sizeof(s + c));

    printf("2.无符号类型数的转换\n");
    if (-1L < 1U)
        printf("-1L<1U，因为 unsigned int 类型的 1U 被提升为 signed long 类型。\n");
    if (-1L > 1UL)
        printf("-1L>1UL，因为 signed long 类型的 -1L 被提升为 unsigned long 类型的最大值。也就是说 long 类型相对于 unsigned long 类型也是一种提升\n\n");

    printf("3.字符扩展\n");
    char c1 = 0x0f;
    char c2 = 0xf0;
    unsigned char uc1 = 0x0f;
    unsigned char uc2 = 0xf0;
    printf("符号扩展 %d\n", c1 + c2);
    printf("无符号扩展 %d\n", uc1 + uc2);
    printf("符号扩展+无符号扩展 %d\n", c1 + uc2);
    printf("无符号扩展+符号扩展 %d\n", uc1 + c2);
    printf("结论：当表达式中既有 char也有 unsigned char 时，\n\t不会像上面将 signed long 转换为 unsigned long 一样\n\t将 char 转换为 unsigned char，而是直接\n\t将 char 和 unsigned char 都转换为 int 类型\n\t再进行计算 \n");
    return 0;
}