
#include <stdio.h>

/* 修改温度转换程序，要求以逆序打印温度转换表 */
int main()
{
    float fahr, celsius;
    int lower, upper, step;
    lower = 0;
    upper = 300;
    step = 20;

    fahr = upper;
    printf("fah    cel\n");
    printf("----------\n");
    while (fahr >= lower)
    {
        celsius = (5.0 / 9.0) * (fahr - 32.0);
        printf("%3.0f %6.1f\n", fahr, celsius);
        fahr = fahr - step;
    }
    return 0;
}
