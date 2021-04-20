
#include <stdio.h>

/* 编写一个统计空格、制表符与换行符个数打程序 */
int main()
{
    int c, n1, n2, n3;
    n1 = 0, n2 = 0, n3 = 0;
    c = getchar();
    while (c != EOF)
    {

        if (c == ' ')
            ++n1;
        else if (c == '\t')
            ++n2;
        else if (c == '\n')
            ++n3;
        c = getchar();
    }

    printf("space cnt = %d\n", n1);
    printf("table cnt = %d\n", n2);
    printf("newline cnt = %d\n", n3);
    return 0;
}
