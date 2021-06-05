#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 现在编写一个程序,在父进程中使用 wait(), 等待子进程完成 。 wait()返回什么?如
果你在子进程中使用 wait()会发生什么?

答：wait返回子进程的进程号，子进程调用wait会直接返回-1
 */
int main(int argc, char *argv[])
{
    printf("hello world (pid:%d)\n", (int)getpid());
    int rc = fork();
    if (rc < 0)
    {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        // child (new process)
        int rc_wait = wait(NULL);
        printf("hello, I am child (rc_wait:%d) (pid:%d)\n", rc_wait, (int)getpid());
    }
    else
    {
        // parent goes down this path (main)
        int rc_wait = wait(NULL);
        printf("hello, I am parent of %d (rc_wait:%d) (pid:%d)\n",
               rc, rc_wait, (int)getpid());
    }
    return 0;
}
