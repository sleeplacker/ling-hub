#include <stdio.h>

struct point
{
    int x;
    long y;
} ps[] = {1, 1, 2, 2, 3, 3}; /* 不指定数组大小地初始化，定义了3个结构 {1, 1},{2, 2},{3, 3} */

/* 结构数组长度计算 */
int main()
{
    /* 1.计算结构大小，需要注意的是，结构的大小不一定等于，
    所有成员大小之和，因为还需要满足对齐要求，所以计算结构
    大小应该使用 sizeof 来确定 */
    printf("%u\n", sizeof(struct point)); /* int 4 字节 + long 8 字节 = 12 字节，但是这个结构占用了 16 字节 */

    /* 2.计算结构数组长度 */
    printf("%u\n", sizeof ps / sizeof(struct point)); /* 方式一 */
    printf("%u\n", sizeof ps / sizeof ps[0]);         /* 方式二，更好，结构类型改变了也不需要改代码 */
    return 0;
}
