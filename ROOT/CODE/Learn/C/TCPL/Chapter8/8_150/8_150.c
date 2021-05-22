#include <stdio.h>
#define MAXLINE 100
/* 
I/O重定向 
在 bash 中：< 会重定向标准输入，而 > 会重定向标准输出，而不会重定向到标准错误
因此如果使用命令：./a.out < in.txt > out.txt，那么标准输出会被重定向到文件
，而标准错误却仍然打印在屏幕上。如果想将标准错误也输出到文件中，可以使用这个命令
：./a.out < in.txt > out.txt 2>&1，如果想将标准输出和标准错误输出到不同文件，
可以使用这个命令：./a.out < in.txt 1>out.txt 2>err.txt
*/
/* 运行命令：
./a.out < in.txt > out.txt 
或
./a.out < in.txt > out.txt 2>&1
或
./a.out < in.txt 1>out.txt 2>err.txt
*/
int main()
{
    char line[MAXLINE];
    while (fgets(line, MAXLINE, stdin))
    {
        fputs("out: ", stdout);
        fputs(line, stdout);
        fputs("err: ", stderr);
        fputs(line, stderr);
    }

    return 0;
}