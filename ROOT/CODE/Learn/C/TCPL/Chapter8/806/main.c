#include "header.h"

/* 
标准库函数calloc(n,size) 返回一个指针，它指向n个长度为size的
对象，且所分配的存储空间都被初始化为0。通过调用或修改malloc函数
来实现calloc函数。
*/

/* 编译命令：gcc main.c malloc.c -fno-builtin-malloc -fno-builtin-calloc */
int main(int argc, char *argv[])
{
    int i;
    int *ip = (int *)calloc(5, sizeof(int));
    for (i = 0; i < 5; ++i)
        *ip++ = i * 100;
    for (i = 0; i < 5; ++i)
        printf("%d\n", *--ip);
    free(ip);
    return 0;
}
