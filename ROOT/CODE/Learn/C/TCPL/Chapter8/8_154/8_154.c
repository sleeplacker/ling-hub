#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int get(int fd, long pos, char *buf, int n);
/* 随机访问 */
int main(int argc, char const *argv[])
{
    char buf[BUFSIZ];
    int fd = open("./1.txt", O_RDONLY, 0);
    if (get(fd, 22, buf, 10) != -1)
        printf("第22位开始的10位：%s\n", buf);
    else
        fputs("第22位开始的10位：查找失败\n", stderr);
    return 0;
}

int get(int fd, long pos, char *buf, int n)
{
    if (lseek(fd, pos, 0) >= 0)
        return read(fd, buf, n);
    else
        return -1;
}