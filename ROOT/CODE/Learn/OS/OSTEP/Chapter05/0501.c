#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/* 编写 一个调用 fork() 的程序。在调用 fork()之前,让主进程访问 一 个变 量( 例如 x)
并将其值设置为某个值(例如 100) 。子进程中的变量有什么值?当子进程和父进程都改变
x 的值时,变量会发生什么?

答：x在子进程和父进程中有各自副本，所以x在两个进程中被修改都不会影响另一个进程
 */
int main(int argc, char *argv[])
{
    int i = 100;

    printf("hello world (pid:%d)\n", (int)getpid());
    int rc = fork();
    if (rc < 0)
    {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    { // child (new process)
        i++;
        printf("hello, I am child (pid:%d) i=%d\n", (int)getpid(), i);
    }
    else
    {
        // parent goes down this path (main)
        i--;
        int rc_wait = wait(NULL);
        printf("hello, I am parent of %d (rc_wait:%d) (pid:%d) i=%d\n",
               rc, rc_wait, (int)getpid(), i);
    }
    return 0;
}
