#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 使用 fork()编写另一个程序。子进程应打印 "hello", 父进程应打印 "goodbye" 。你
应该尝试确保子进程始终先打 印 。你能否不在父进程调用 wait()而做到这 一点呢?

答：实现方案，子进程先打印hello，然后创建文件；父进程不断检查文件是否存在，存在才打印world
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
        printf("hello, I am child (pid:%d)\n", (int)getpid());
        int fd = open("./test.txt", O_WRONLY | O_RDONLY | O_CREAT, 0755);
        char buf1[] = "child_finish";
        write(fd, buf1, strlen(buf1));
        close(fd);
    }
    else
    {
        int fd;
        while ((fd = open("./test.txt", O_RDONLY)) < 0)
            ;
        printf("goodbye, I am parent of %d (pid:%d)\n",
               rc, (int)getpid());
        close(fd);
        unlink("./test.txt");
    }

    return 0;
}