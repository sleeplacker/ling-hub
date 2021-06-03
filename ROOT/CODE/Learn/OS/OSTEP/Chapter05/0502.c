#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 编写 一 个打开文件的程序(使用 open()系统调用),然后调用 fork()创建 一 个新进程。
子进程和父进程都可 以访问 open()返回的文件描述符吗? 当它 们并发(即同时) 写 入文件时,
会发生什么?

答：都可以访问，并发写入时子进程和父进程都能成功写入
 */
int main(int argc, char *argv[])
{
    int fd = open("./test.txt", O_WRONLY);
    int rc = fork();
    if (rc < 0)
    {
        // fork failed
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        printf("in child, fd=%d\n", fd);
        char buf[] = "child\n";
        write(fd, buf, strlen(buf));
    }
    else
    {
        printf("in parent, fd=%d\n", fd);
        char buf[] = "parent\n";
        write(fd, buf, strlen(buf));
    }
    return 0;
}
