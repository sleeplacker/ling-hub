#include "header.h"

/* 编译命令：gcc main.c malloc.c -fno-builtin-malloc*/
int main(int argc, char *argv[])
{
    char *s = (char *)malloc(20);
    strcpy(s, "abc");
    printf("%s\n", s);
    free(s);
    return 0;
}
