#include <unistd.h>
#include <stdio.h>

int main()
{
    char buf[444];
    int n;

    while ((n = read(0, buf, 444)) > 0)
        write(1, buf, n);
    return 0;
}