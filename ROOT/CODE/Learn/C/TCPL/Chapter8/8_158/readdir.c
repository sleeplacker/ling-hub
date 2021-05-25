#include <sys/dir.h>/* 本地目录结构 */
#include "dirent.h"

/* readdir函数：按顺序读取目录项 */
Dirent *readdir(DIR *dp)
{
    struct direct dirbuf;/* 本地目录结构 */
    static Dirent d;/* 返回：可移植的结构 */

    while (read(dp->fd, (char *)&dirbuf, sizeof(dirbuf)) == sizeof(dirbuf))
    {
        if (dirbuf.d_ino == 0)/* 目录位置未使用 */
            continue;
        d.ino = dirbuf.d_ino;
        strncpy(d.name, dirbuf.d_name, DIRSIZ);
        d.name[DIRSIZ] = '\0';/* 添加终止符 */
        return &d;
    }
    return NULL;
}