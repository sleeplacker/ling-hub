#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* free方法只能用于malloc或者calloc方法分配的空间
如果free数组会发生严重的运行时错误，尽管编译能通过 */
int main()
{
    char *str = (int *)malloc(5);
    double *ds = (double *)calloc(5, sizeof(double));
    int iarr[5];
    free(str);
    free(ds);
    free(iarr);
    return 0;
}