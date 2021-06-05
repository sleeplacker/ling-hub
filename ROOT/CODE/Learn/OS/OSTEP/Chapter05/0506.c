#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 对前一个程序稍作修改,这次使用 waitpid()而不是 wait() 。什么时候 waitpid()会
有用?

答：依赖特定子进程时会有用
 */
int main(int argc, char *argv[])
{
    printf("hello world (pid:%d)\n", (int)getpid());
    int rc = fork();
    int stat;
    if (rc < 0)
    {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        // child (new process)
        // int rc_wait = waitpid(NULL);
        printf("hello, I am child (pid:%d)\n", (int)getpid());
    }
    else
    {
        // parent goes down this path (main)
        int rc_wait = waitpid(-1, &stat, 0);
        printf("hello, I am parent of %d (rc_wait:%d) (stat:%d)(pid:%d)\n",
               rc, rc_wait, stat, (int)getpid());
    }
    return 0;
}
