#include <stdio.h>
#include <stdlib.h>

/* 
错误处理-原版本
错误信息在输出的末尾才会打印
 */
int main(int argc, char const *argv[])
{
    FILE *fp;
    void filecopy(FILE *, FILE *);

    if (argc == 1)
        filecopy(stdin, stdout);
    else
        while (--argc > 0)
            if ((fp = fopen(*++argv, "r")) == NULL)
            {
                printf("cat: can't open %s\n", *argv);
                return 1;
            }
            else
            {
                filecopy(fp, stdout);
                fclose(fp);
            }
    return 0;
}

void filecopy(FILE *ifp, FILE *ofp)
{
    int c;
    while ((c = getc(ifp)) != EOF)
        putc(c, ofp);
}
