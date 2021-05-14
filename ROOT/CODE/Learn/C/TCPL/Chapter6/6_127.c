#include <stdio.h>

#define AAA 1 /* 第一次定义 */

int a = AAA; /* a=1 */

#define AAA 2 /* 第二次定义你 */

int b = AAA; /* b=2 */

/* 
宏定义可以多次定义，且后一次定义会覆盖前一次定义
但是两次定义中间的位置还是使用第一次定义的内容
 */
int main()
{
    printf("a=%d\n", a);
    printf("b=%d\n", b);
    return 0;
}
