#include "mystdio.h"

/* fseek函数：通过文件指针进行随机访问 */
int fseek(FILE *fp, long offset, int origin)
{
    unsigned nc; /* 刷新的字符数 */
    long rc = 0; /* 返回码 */
    if (fp->flag & _READ)
    {
        if (origin == 1)       /* 是否从当前位置开始读取 */
            offset -= fp->cnt; /* 记住缓冲区中的内容 */
        rc = lseek(fp->fd, offset, origin);
        fp->cnt = 0; /* 没有字符在缓冲区 */
    }
    else if (fp->flag & _WRITE)
    {
        if ((nc = fp->ptr - fp->base) > 0)
            if (write(fp->fd, fp->base, nc) != nc)
                rc = -1;
        if (rc != -1) /* 未发生错误 */
            rc = lseek(fp->fd, offset, origin);
    }
    return (rc == -1) ? -1 : 0;
}