#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/* system 函数指向命令 */
int main()
{
    int i;
    for (i = 0; i < 5; ++i)
    {
        sleep(1);
        system("date");
    }
    printf("DONE.");
    return 0;
}