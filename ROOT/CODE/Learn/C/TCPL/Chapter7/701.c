#include <stdio.h>
#include <string.h>
#include <ctype.h>

/* 
编写一个程序，根据它自身被调用时存放在argv[0]中的名字，
实现将大写字母转换为小写字母或将小写字母转换为大写字母
的功能。
 */
int main(int argc, char const *argv[])
{

    int c;
    printf("prog = %s\n", argv[0]);
    while ((c = getchar()) != EOF)
        if (!strcmp(argv[0], "./lower"))
            putchar(tolower(c));
        else
            putchar(toupper(c));
    return 0;
}