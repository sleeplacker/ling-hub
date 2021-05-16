#include <stdio.h>

typedef struct
{
    unsigned int is_keyword : 1; /* 这里的1表示只占1位 */
    unsigned int is_extern : 1;
    unsigned int is_static : 1;
    unsigned int is_static1 : 1;
    unsigned int is_static2 : 1;
} bit;

/* 
位字段

注意：在选择外部定义的位字段时，需要注意大端法和小端法的问题
如果定义位字段使用使用的小端法，那么在解析位字段时也应该使用
小端法，如果使用了大端法，则得到的位序列是相反的。
 */
int main()
{
    // bit b1 = {0, 2, 0};/* 错：只能赋值0或1 */
    bit b1 = {0, 1, 0};
    /* 虽然结构中有5个字段，但是结构只占4个字节，实际只占1个字节，4字节是为了补齐 */
    printf("sizef of struct bit = %d\n", sizeof(bit));
    return 0;
}