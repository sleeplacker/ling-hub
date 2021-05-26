#include "header.h"

char out[128]; /* 外部数组，将把它加入空闲链表 */

/* 
编写函数bfree(p,n)，释放一个包含n个字符的任意块p，并将它
放入有malloc和free维护的空闲块链表中。通过使用bfree，用户
可以在任意时刻向空闲链表中添加一个静态或外部数组。
*/

/* 编译命令：gcc main.c malloc.c -fno-builtin-malloc -fno-builtin-calloc -g */
int main(int argc, char *argv[])
{
    int i, f;
    char *s = (char *)malloc(1); /* 初始化空闲链表 */
    for (i = 0; i < 128; ++i)
        out[i] = 'A';
    printf("外部数组原内容：%s\n", out);
    printFreeList();
    Header *hp = (Header *)out;
    f = bfree((char *)out, 128);
    printFreeList();
    printf("加入空闲链表的块长度：%d\n", f);
    printf("新的空闲块的大小(单位：%d 字节)：%d\n", sizeof(Header), hp->s.size);
    s = (char *)malloc(100); /* 使用空闲链表 */
    for (i = 0; i < 100; ++i)
        *s++ = 'B';
    printf("外部数组现内容：%s\n", out + sizeof(Header));/* out现在只想mallco分配的块，所以需要跳过头部才能看到内容 */
    return 0;
}
