#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLINE 100

/* 
bash 通配符问题
 */
/* 运行命令：./a.out *.c ../*.c */
/* 运行结果：
argc = 14
./a.out
707.c
test.c
../701.c
../702.c
../703.c
../704.c
../7_135.c
../7_137.c
../7_138.c
../7_140.c
../7_147_1.c
../7_147_2.c
../7_148.c
 */
int main(int argc, char *argv[])
{
    printf("argc = %d\n", argc);/* argc = 14，虽然只输入了两个参数，但是argc=14，因为bash会将通配符转换为多个参数*/
    while (argc--)
    {
        printf("%s\n", *argv++);
    }
    return 0;
}
