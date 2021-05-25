#include "header.h"

/* 
malloc接收对存储空间的请求时，并不检查请求长度的合理性；
而free则认为被释放的块包含了一个有效的长度字段。改进这些
函数，使它们具有错误检查的功能。
*/

/* 编译命令：gcc main.c malloc.c -fno-builtin-malloc -fno-builtin-calloc -g */
int main(int argc, char *argv[])
{
    int i;
    int *ip = (int *)calloc(4096, sizeof(int));
    if (ip != NULL)
    {
        for (i = 0; i < 5; ++i)
            *ip++ = i * 100;
        for (i = 0; i < 5; ++i)
            printf("%d\n", *--ip);
        free(ip);
    }
    return 0;
}
