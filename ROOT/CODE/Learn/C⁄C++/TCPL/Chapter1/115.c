
#include <stdio.h>
void transfer(int lower, int upper, int step);
/* 重新编写1.2节中打温度转换程序，使用函数实现温度转换计算 */
int main()
{
    transfer(0, 300, 20);
    return 0;
}

void transfer(int lower, int upper, int step)
{
    float fahr, celsius;
    fahr = lower;
    printf("fah    cel\n");
    printf("----------\n");
    while (fahr <= upper)
    {
        celsius = (5.0 / 9.0) * (fahr - 32.0);
        printf("%3.0f %6.1f\n", fahr, celsius);
        fahr = fahr + step;
    }
}
