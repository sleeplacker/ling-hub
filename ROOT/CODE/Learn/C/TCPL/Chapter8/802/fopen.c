#include "mystdio.h"
#define PERMS 0666 /* 所有者、所有者组和其他成员都可以读写 */

/* fopen函数：打开文件，并返回文件指针 */
FILE *fopen(char *name, char *mode)
{
    int fd;
    FILE *fp;

    if (*mode != 'r' && *mode != 'w' && *mode != 'a')
        return NULL;
    for (fp = _iob; fp < _iob + OPEN_MAX; fp++)
        if (fp->flag.is_read == 0 && fp->flag.is_write == 0)
            break; /* 寻找一个空闲位 */
    if (fp >= _iob + OPEN_MAX)
        return NULL; /* 没有空闲位置 */
    if (*mode == 'w')
        fd = creat(name, PERMS);
    else if (*mode == 'a')
    {
        if ((fd = open(name, O_WRONLY, 0)) == -1)
            fd = creat(name, PERMS);
        lseek(fd, 0L, 2);
    }
    else
        fd = open(name, O_RDONLY, 0);
    if (fd == -1) /* 不能访问名字 */
        return NULL;
    fp->fd = fd;
    fp->cnt = 0;
    fp->base = NULL;
    fp->flag.is_unbuf = 0;
    fp->flag.is_buf = 1;
    fp->flag.is_eof = 0;
    fp->flag.is_err = 0;
    if (*mode == 'r')
    {
        fp->flag.is_read = 1;
        fp->flag.is_write = 0;
    }
    else
    {
        fp->flag.is_read = 0;
        fp->flag.is_write = 1;
    }
    return fp;
}
