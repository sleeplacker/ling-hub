#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 编写一个创建子进程的程序,然后在子进程中关闭标准输出 (STDOUT_FILENO) 。
如果子进程在关闭描述符后调用 printf()打印输出,会发生什么?

答：子进程关闭标准输出流后，在子进程调用printf不会有输出，但是在主进程中还会有输出，
说明：文件描述符是每个进程私有的，而不是进程间共享的，如CSAPP第636页描述一致
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
        close(STDOUT_FILENO);
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
