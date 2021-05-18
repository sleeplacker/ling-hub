#include <stdio.h>
#include <string.h>
#include <ctype.h>
void transfer(char *s);

/* 为 Python2代码的 print 函数加上圆括号，以支持Python3 */
int main()
{
    char line[4096];
    FILE *src = fopen("/home/lingang/books/操作系统导论/源码/HW-AFS/HW-AFS/afs.py", "r");
    while (fgets(line, 4096, src))
        transfer(line);
    return 0;
}

void transfer(char *s)
{
    int c;
    while (isspace(c = *s++))
        putchar(c);
    --s;
    if (strspn(s, "print") == 5 && isspace(*(s + 5)))
    {
        printf("print(");
        s += 6;
        while ((c = *s++) != '\n' && c != '\0')
            putchar(c);
        putchar(')');
        putchar('\n');
    }

    else
        printf("%s", s);
}
