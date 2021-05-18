#include <stdio.h>

/* 行输入和行输出 */
int main()
{
    char line[20];
    FILE *in = fopen("./in.txt", "r");
    FILE *out = fopen("./out.txt", "w");
    while (fgets(line, 20, in)) /* fgets函数读取行 */
        printf("%s", line);
    fputs("aa\nbb\ncc\n", out); /* fputs函数向文件写入行，且字符串中换行符会对文件进行换行 */
    return 0;
}