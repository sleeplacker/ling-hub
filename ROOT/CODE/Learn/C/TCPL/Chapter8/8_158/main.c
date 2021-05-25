#include "dirent.h"

void fsize(char *);

/* 打印文件长度 */
int main(int argc, char const *argv[])
{
    if (argc == 1)/* 默认为当前目录 */
        fsize(".");
    else
        while (--argc > 0)
            fsize(*++argv);
    return 0;
}

int stat(char *, struct stat *);
void dirwork(char *, void (*fcn)(char *));

/* fszie函数：打印文件name的长度 */
void fsize(char *name)
{
    struct stat stbuf;
    if (stat(name, &stbuf) == -1)
    {
        fprintf(stderr, "fsize: can't access %s\n", name);
        return;
    }
    if ((stbuf.st_mode & S_IFMT) == S_IFDIR)
        dirwork(name, fsize);
    printf("%8ld %s\n", stbuf.st_size, name);
}