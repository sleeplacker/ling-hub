#include <stdio.h>

/* ungetc函数，如果在读文件之前写回，那么只能写回一个字符，
但是不会改变文件内容，只是改变文件流的内容，如果已经从文件
流读取了n个字符，那么总共可以写回n个字符 */
int main()
{
    FILE *f = fopen("./in.txt", "r");
    int a, b, c;

    fscanf(f, "%d %d", &a, &b);
    printf("%d %d\n", a, b);
    printf("%d\n", ungetc('x', f));
    printf("%d\n", ungetc('y', f));
    while ((c = getc(f)) != EOF)
        putchar(c);

    return 0;
}