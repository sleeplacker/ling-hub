#include <stdio.h>

/* 占用多行的宏定义 */
#define MULTILINE "line1 aaa\
line2 bbb\
line3 ccc"

/* 使用宏定义实现函数功能 */
#define max(A, B) ((A) > (B) ? (A) : (B))

/* 使用undef取消宏定义，以让后续的调用是函数调用或者变量引用 */
#define aa "AA"
#undef aa /* 如果不使用undef取消aa宏定义，则会有编译错误 */

/* #前缀将宏定义的参数转换为带双引号的字符串 */
#define dprint(expr) printf(#expr " = %d\n", expr)

/* 使用##连接参数名 */
#define paste(front, back) front##back

/* 
验证：多行宏定义
*/
int main()
{
    int paste(name, 1) = 0;    /* paste(name, 1)得到name1参数名 */
    printf("%s\n", MULTILINE); /* 虽然宏定义占用多行，但是定义的字符串中没有换行符，所以只会打印一行 */
    printf("Max one between 1 2 is %d\n", max(1, 2));
    char aa[20] = "BB";
    printf("%s\n", aa);
    dprint(1 + 1); /* 注意这里的1 + 1中用于格式化的空格也会包含在expr中，所以格式化后的源码和未格式化源码打印的结果会不一样 */
    printf("%d\n", name1);
}
