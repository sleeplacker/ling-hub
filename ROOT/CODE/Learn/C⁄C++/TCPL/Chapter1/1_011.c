
#include <stdio.h>

/* 标准库中的getchar和putchar */
int main()
{
    int c;
    while ((c = getchar()) != EOF)
        putchar(c);
    return 0;
}
