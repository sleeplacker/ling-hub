#include <stdlib.h>
#define MAXSTR 100
void substr(char *s, char *str);
int numcmp(char *s1, char *s2)
{
    double v1, v2;

    char str[MAXSTR];

    substr(s1, str);

    v1 = atof(str);
    substr(s1, str);
    v2 = atof(str);
    if (v1 < v2)
        return -1;
    else if (v1 > v2)
        return 1;
    else
        return 0;
}