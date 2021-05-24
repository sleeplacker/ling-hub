#include "mystdio.h"
#include <stdlib.h>

/* _fillbuf函数：分配并填充输入缓冲区 */
int _fillbuf(FILE *fp)
{
    int bufsize;
    if ((fp->flag & (_READ | _EOF | _ERR)) != _READ)
        return EOF;
    bufsize = (fp->flag & _UNBUF) ? 1 : BUFSIZ;
    if (fp->base == NULL) /* 还未分配缓冲区 */
        if ((fp->base = (char *)malloc(bufsize)) == NULL)
            return EOF; /* 不能分配缓冲区 */
    fp->ptr = fp->base;
    fp->cnt = read(fp->fd, fp->ptr, bufsize);
    if (--fp->cnt < 0)
    {
        if (fp->cnt == -1)
            fp->flag |= _EOF;
        else
            fp->flag |= _ERR;
        fp->cnt = 0;
        return EOF;
    }
    return (unsigned char)*fp->ptr++;
}

FILE _iob[OPEN_MAX] = {
    {0, (char *)0, (char *)0, _READ, 0},          /* 标准输入文件-只读 */
    {0, (char *)0, (char *)0, _WRITE, 1},         /* 标准输出文件-普通写 */
    {0, (char *)0, (char *)0, _WRITE | _UNBUF, 2} /* 标准错误文件-无缓冲写 */
};