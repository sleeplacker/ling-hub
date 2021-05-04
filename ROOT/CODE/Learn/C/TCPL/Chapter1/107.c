
#include <stdio.h>

/* 编写一个打印 EOF 值打程序  */
int main()
{
    printf("EOF = %d\n", EOF);// 在当前系统中(linux) EOF = -1，但在其他系统中可能为其他值 
    return 0;
}
