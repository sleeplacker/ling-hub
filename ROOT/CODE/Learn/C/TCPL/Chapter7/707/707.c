#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLINE 100

/* 
修改第5章的模式查找程序，使它从一个命令文件的集合中读取输
入（有文件参数时），如果没有文件名参数，则从标准输入中读取
输入。当发现一个匹配行时，是否应该将相应的文件名打印出来？
 */
/* 运行命令：./a.out -n printf ./707.c ../*.c ../../Chapter6/*.c */
int main(int argc, char *argv[])
{
    char pattern[MAXLINE];
    int c, except = 0, number = 0;
    FILE *fp;
    void fpat(FILE * fp, char *fname, char *pattern, int except, int number);
    while (--argc > 0 && (*++argv)[0] == '-')
        while (c = *++argv[0])
            switch (c)
            {
            case 'x':
                except = 1;
                break;
            case 'n':
                number = 1;
                break;

            default:
                printf("find: illegal option %c\n", c);
                argc = 0;
                break;
            }
    if (argc >= 1)
        strcpy(pattern, *argv);
    else
    {
        printf("Usage: find [-x] [-n] pattern [file ...]\n");
        exit(1);
    }
    if (argc == 1)
        fpat(stdin, "", pattern, except, number);
    else
        while (--argc > 0)
            if ((fp = fopen(*++argv, "r")) == NULL)
            {
                fprintf(stderr, "fiind: can't open %s\n", *argv);
                exit(1);
            }
            else
            {
                printf("%s\n",*argv);
                fpat(fp, *argv, pattern, except, number);
                fclose(fp);
            }
    return 0;
}

void fpat(FILE *fp, char *fname, char *pattern, int except, int number)
{
    char line[MAXLINE];
    long lineno = 0;

    while (fgets(line, MAXLINE, fp) != NULL)
    {
        ++lineno;
        if ((strstr(line, pattern) != NULL) != except)
        {
            if (*fname)
                printf("%s - ", fname);
            if (number)
                printf("%ld: ", lineno);
            printf("%s", line);
        }
    }
}
