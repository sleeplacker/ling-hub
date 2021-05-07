#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAXSTOR 5000

size_t MAXLEN = 1000;

int readlines(char *lineptr[], int maxlines)
{
    int len, nlines;
    char *p, *line = malloc(MAXLEN);

    nlines = 0;
    while ((len = getline(&line, &MAXLEN, stdin)) > 0)
        if (nlines >= maxlines || (p = malloc(len)) == NULL)
            return -1;
        else
        {
            line[len - 1] = '\0';
            strcpy(p, line);
            lineptr[nlines++] = p;
        }
    return nlines;
}

void writelines(char *lineptr[], int nlines)
{
    while (nlines-- > 0)
    {
        printf("%s\n", *lineptr++);
    }
}