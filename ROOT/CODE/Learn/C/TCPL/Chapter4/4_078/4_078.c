/* 条件包含，可以在预处理中选择包含不同的头文件 */
#include <stdio.h>

#define SYSTEM 2

/* 之所以下面的判断是真，是因为SYSTEM和SYSV都是未定义的，所以两者相等 */
// #if SYSTEM==SYSV
#if SYSTEM == 1
#define HDR "sysv.h"
#elif SYSTEM == 2
#define HDR "bsd.h"
#elif SYSTEM == 3
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