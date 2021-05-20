#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    int i;
    for (i = 0; i < 5; ++i)
    {
        sleep(1);
        system("chdir");
    }
    printf("DONE.");
    return 0;
}