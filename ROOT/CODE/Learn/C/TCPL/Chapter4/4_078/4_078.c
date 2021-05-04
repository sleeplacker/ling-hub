/* 条件包含，可以在预处理中选择包含不同的头文件 */
#include <stdio.h>

#if SYSTEM == SYSV
#define HDR "sysv.h"
#elif SYSTEM == BSD
#define HDR "bsd.h"
#elif SYSTEM == MSDOS
#define HDR "msdos.h"
#else
#define HDR "default.h"
#endif

#include HDR /* 使用宏定义来包含头文件 */
int main()
{
    printf("Max int = %d\n", INT_MAX);
    return 0;
}