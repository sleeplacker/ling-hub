#include "dirent.h"
// #include <sys/dir.h>

int fstat(int fd, struct stat *);

/* opendir函數：打开目录供函数readdir使用 */
DIR *opendir(char *dirname)
{
    int fd;
    struct stat stbuf;
    DIR *dp;
    if ((fd = open(dirname, O_RDONLY, 0)) == -1 || fstat(fd, &stbuf) == -1 || (stbuf.st_mode & S_IFMT) != S_IFDIR || (dp = (DIR *)malloc(sizeof(DIR))) == NULL)
        return NULL;
    dp->fd = fd;
    return dp;
}

/* closedir函数：关闭opendir打开的目录 */
void closedir(DIR *dp)
{
    if (dp)
    {
        close(dp->fd);
        free(dp);
    }
}

/* readdir函数：按顺序读取目录项 */
Dirent *readdir(DIR *dp)
{
    struct direct dirbuf; /* 本地目录结构 */
    static Dirent d;      /* 返回：可移植的结构 */

    while (read(dp->fd, (char *)&dirbuf, sizeof(dirbuf)) == sizeof(dirbuf))
    {
        if (dirbuf.d_ino == 0) /* 目录位置未使用 */
            continue;
        d.ino = dirbuf.d_ino;
        strncpy(d.name, dirbuf.d_name, DIRSIZ);
        d.name[DIRSIZ] = '\0'; /* 添加终止符 */
        return &d;
    }
    return NULL;
}