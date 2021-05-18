#include <stdio.h>

/* 
为什么 stderr 会在 stdout 的前面打印 

因为 stdout 会先缓冲再打印，本次实验得到缓冲区为4k，
如果输出到 stdout 的内容超过 4k，那么缓冲区满，会先
打印stdout 的内容，否则先打印 stderr
*/
int main()
{
    // int i = 4096; /* 先打印out */
    int i = 4086; /* 先打印err */
    putc('o', stdout);
    putc('u', stdout);
    putc('t', stdout);
    while (i--)
        putc('1', stdout);
    putc('\n', stdout);

    putc('e', stderr);
    putc('r', stderr);
    putc('r', stderr);
    return 0;
}