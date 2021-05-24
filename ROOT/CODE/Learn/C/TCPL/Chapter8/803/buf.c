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

/* _flushbuf函数：分配和刷新输出缓冲区 */
int _flushbuf(int x, FILE *fp)
{
    unsigned nc; /* 缓冲区刷新字符数 */
    int bufsize; /* 缓冲区分配大小 */
    if (fp < _iob || fp >= _iob + OPEN_MAX)
        return EOF;
    if ((fp->flag & (_WRITE | _ERR)) != _WRITE)
        return EOF;
    bufsize = (fp->flag & _UNBUF) ? 1 : BUFSIZ;
    if (fp->base == NULL) /* 还未分配缓冲区 */
    {
        if ((fp->base = (char *)malloc(bufsize)) == NULL)
        {
            fp->flag |= _ERR;
            return EOF; /* 取不到缓冲区 */
        }
    }
    else
    { /* 缓冲区已存在 */
        nc = fp->ptr - fp->base;
        if (write(fp->fd, fp->base, nc) != nc)
        {
            fp->flag |= _ERR;
            return EOF;
        }
    }
    fp->ptr = fp->base;   /* 缓冲区起始位置 */
    *fp->ptr++ = (char)x; /* 保存当前字符 */
    fp->cnt = bufsize - 1;
    return x;
}

FILE _iob[OPEN_MAX] = {
    {0, (char *)0, (char *)0, _READ, 0},          /* 标准输入文件-只读 */
    {0, (char *)0, (char *)0, _WRITE, 1},         /* 标准输出文件-普通写 */
    {0, (char *)0, (char *)0, _WRITE | _UNBUF, 2} /* 标准错误文件-无缓冲写 */
};