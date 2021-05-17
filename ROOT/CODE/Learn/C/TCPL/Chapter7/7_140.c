#include <stdio.h>
#include <stdlib.h>

/* 输入格式不固定的输入 */
/* 输入示例：12 Feb 2020 */
int main()
{
    char *line = (char *)malloc(sizeof(char) * 100);
    ssize_t len = 100;
    char monthname[10];
    int day, year, month;
    while (getline(&line, &len, stdin) > 0)
    {
        if (sscanf(line, "%d %s %d", &day, monthname, &year) == 3)
            printf("valid: %s\n", line);
        else if (sscanf(line, "%d%d%d", &month, &day, &year) == 3)
            printf("valid: %s\n", line);
        else
            printf("invalid: %s\n", line);
    }
    return 0;
}