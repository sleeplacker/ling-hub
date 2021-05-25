#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>     /* 读写标志 */
#include <sys/types.h> /* 类型定义 */
#include <sys/stat.h>  /* stat返回的结构 */

#define NAME_MAX 14

typedef struct /* 可移植的目录项 */
{
    long ino;                /* i结点编号 */
    char name[NAME_MAX + 1]; /* 文件名加结束符'\0' */
} Dirent;

typedef struct /* 最小的DIR：无缓冲等特性 */
{
    int fd;   /* 目录的文件描述符 */
    Dirent d; /* 目录项 */
} DIR;

DIR *opendir(char *dirname);
Dirent *readdir(DIR *dfd);
void closedir(DIR *dfd);

#ifndef DIRSIZ
#define DIRSIZ 14
#endif
struct direct /* 目录项 */
{
    ino_t d_ino;         /* i结点编号 */
    char d_name[DIRSIZ]; /* 长文件名不包含'\0' */
};

/* 下面内容在stat.h中也有定义，只需要看其中的注释 */
// struct stat /* 由stat返回的i结点信息 */
// {
//     dev_t st_dev;    /* i结点设备 */
//     ino_t st_ino;    /* i结点编号 */
//     short st_mode;   /* 模式位 */
//     short st_nlink;  /* 文件的总的链接数 */
//     short st_uid;    /* 所有者的用户id */
//     short st_gid;    /* 所有者的组id */
//     dev_t st_rdev;   /* 用于特殊的文件 */
//     off_t st_size;   /* 用字符数表示的文件长度 */
//     time_t st_atime; /* 上一次访问的时间 */
//     time_t st_mtime; /* 上一次修改的时间 */
//     time_t st_ctime; /* 上一次改变i结点的时间 */
// };

// #define S_IFMT 0160000  /* 文件的类型 */
// #define S_IFDIR 0040000 /* 目录 */
// #define S_IFCHR 0020000 /* 特殊字符 */
// #define S_IFBLK 0060000 /* 特殊块 */
// #define S_IFREG 0100000 /* 普通 */