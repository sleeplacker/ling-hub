/* 条件包含，可以在预处理中选择包含不同的头文件 */
#include <stdio.h>

#define XXX MSDOS

#if SYSTEM==1
#define HDR "sysv.h"
#elif SYSTEM==BSD
#define HDR "bsd.h"
#elif SYSTEM==MSDOS
#define HDR "msdos.h"
#else
#define HDR "default.h"
#endif
#include HDR /* 使用宏定义来包含头文件 */
int main()
{
    printf("Current system is %s\n", CUR_SYS);
    return 0;
}