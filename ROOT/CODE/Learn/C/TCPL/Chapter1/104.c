
#include <stdio.h>

/* 编写一个程序打印摄氏温度转换为华氏温度打转换表 */
int main()
{
    float fahr, celsius;
    int lower, upper, step;
    lower = -20;
    upper = 150;
    step = 10;

    celsius = lower;
    printf("   cel fah\n");
    printf("----------\n");
    while (celsius <= upper)
    {
        fahr = (9.0 / 5.0) * celsius + 32.0;
        printf("%3.0f %6.1f\n", celsius, fahr);
        celsius = celsius + step;
    }
    return 0;
}
