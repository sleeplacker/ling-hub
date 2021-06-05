#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/*  编写 一 个程序,创建两个子进程,并使用 pipe()系统调用,将 一 个子进程的标准输
出连接到另一个子进程的标准输入 。
 */
int main(int argc, char *argv[])
{
    printf("hello world (pid:%d)\n", (int)getpid());
    int ps[2];
    pipe(ps);
    int rc2;
    int rc = fork(); /* 创建进程1 */
    if (rc < 0)
    {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        // 进程1
        dup2(ps[1], STDOUT_FILENO);/* dup2方法专门用于复制文件描述符 */
        printf("hello, I am child (pid:%d)\n", (int)getpid());
    }
    else
    {
        rc2 = fork(); /* 创建进程2 */
        if (rc2 < 0)
        {
            // fork failed; exit
            fprintf(stderr, "fork failed\n");
            exit(1);
        }
        else if (rc2 == 0)
        {
            /* 进程2 */
            dup2(ps[0], STDIN_FILENO);
            printf("hello, I am child (pid:%d)\n", (int)getpid());
            char buf[256];
            fgets(buf, 256, stdin);
            printf("[print in process:%d] %s", (int)getpid(), buf);
            close(ps[0]);
            close(ps[1]);
        }
        else
        {
            printf("hello, I am parent of %d and %d (pid:%d)\n", rc, rc2, (int)getpid());
        }
    }
    return 0;
}
