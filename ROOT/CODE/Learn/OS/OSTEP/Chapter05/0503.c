#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 使用 fork()编写另一个程序。子进程应打印 "hello", 父进程应打印 "goodbye" 。你
应该尝试确保子进程始终先打 印 。你能否不在父进程调用 wait()而做到这 一点呢?

答：
 */

int main(int argc, char *argv[])
{
    int n;
    int rc = fork();
    if (rc < 0)
    {
        // fork failed
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        int fd = open("./test.txt", O_WRONLY | O_CREAT);
        printf("hello, I am child (pid:%d)\n", (int)getpid());
        char buf1[] = "child_finish";
        write(fd, buf1, strlen(buf1));
        close(fd);
    }
    else
    {
        int fd = open("./test.txt", O_WRONLY | O_CREAT);
        char buf2[BUFSIZ];
        while ((n = read(fd, buf2, BUFSIZ)) <= 0)
            ;
        printf("goodbye, I am parent of %d (pid:%d)\n",
               rc, (int)getpid());
        close(fd);
    }
    return 0;
}