#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <sys/wait.h>

/* 编写 一 个调用 fork() 的程序,然后调用某种形式的 exec()来运行程序/bin/ls 。看看是
否可 以 尝试 exec() 的所有变体,包括 execl() 、 execle() 、 execlp() 、 execv() 、 execvp()和 execvpe() 。
为什么同样的基本调用会有这么多变种?
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
        printf("I am child (pid:%d)\n", (int)getpid());
        char *myargs[3];
        myargs[0] = strdup("/bin/ls");
        // program: wc (word count)
        myargs[1] = strdup("-l"); // arg:
        myargs[2] = NULL;
        // execl(myargs[0], "ls", "-l", (char *)NULL);
        // execle(myargs[0], "ls", "-l", (char *)NULL, NULL);/* 带e的函数最后一个参数是环境变量 */
        // execlp(myargs[0], "ls", "-l", (char *)NULL);
        // execv(myargs[0], myargs);
        execvp(myargs[0], myargs);
    }
    else
    {
        printf("I am parent of %d (pid:%d)\n",
               rc, (int)getpid());
    }

    return 0;
}