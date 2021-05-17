#include <stdio.h>
#include <stdlib.h>

/* 文件操作 */
int main()
{
    FILE *fp;
    char c;
    /* 以写入(w)或追加(a)方式打开文件 test.txt，如果该文件不存在，会创建该文件 */
    fp = fopen("./test.txt", "w");
    putc('a', fp); /* putc方法向文件写入单个字符 */
    putc('b', fp);
    fclose(fp); /* 关闭文件 */

    /* 以写入方式打开文件时，写入会覆盖文件原有内容 */
    fp = fopen("./test.txt", "w");
    putc('c', fp);
    putc('d', fp);
    fclose(fp);

    /* 以追加模式打开文件时，写入会追加在文件原有内容末尾 */
    fp = fopen("./test.txt", "a");
    putc('e', fp);
    putc('f', fp);
    fclose(fp);

    /* 以只读模式打开文件时，不会创建文件 */
    fp = fopen("./test.txt", "r");
    while ((c = getc(fp)) != EOF)
        printf("%c\n", c);
    fclose(fp);
    return 0;
}