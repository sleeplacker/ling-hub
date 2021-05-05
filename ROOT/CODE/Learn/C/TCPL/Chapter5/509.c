#include <stdio.h>

int day_of_year(int year, int month, int day);
void month_day(int year, int yearday, int *pmonth, int *pday);

/* 
用指针方式替代数组下标方式改写函数day_of_year和month_day。
 */
int main()
{
    int month;
    int day;
    printf("2020-3-8 是年中第%d天\n", day_of_year(2020, 3, 8));
    month_day(2020, 200, &month, &day);
    printf("2020年的第200天是 %d月%d日\n", month, day);
    return 0;
}

static char daytab[2][13] = {

    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}

};

/* m月d日转换为年中第n天 */
int day_of_year(int year, int month, int day)
{
    int leap;
    char *p;
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    p = daytab[leap];
    while (--month)
        day += *++p;
    return day;
}

/* 年中第n天转换为m月d日 */
void month_day(int year, int yearday, int *pmonth, int *pday)
{
    int leap;
    char *p;
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    p = daytab[leap];
    while (yearday > *++p)
        yearday -= *p;
    *pmonth = p - *(daytab + leap);
    *pday = yearday;
}