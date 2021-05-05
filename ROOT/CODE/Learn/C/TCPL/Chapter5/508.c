#include <stdio.h>

int day_of_year(int year, int month, int day);
int month_day(int year, int yearday, int *pmonth, int *pday);

/* 
函数day_of_year和month_day中没有进
行错误检查，请解决该问题。
 */
int main()
{
    int month;
    int day;
    printf("2020-3-8 是年中第%d天\n", day_of_year(2020, 3, 8));
    printf("2021-2-30 是年中第%d天\n", day_of_year(2021, 2, 30));
    if (month_day(2020, 200, &month, &day) != -1)
        printf("2020年的第200天是 %d月%d日\n", month, day);
    else
        printf("格式错误\n");
    if (month_day(2021, 400, &month, &day) != -1)
        printf("2021年的第400天是 %d月%d日\n", month, day);
    else
        printf("格式错误\n");
    return 0;
}

static char daytab[2][13] = {
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};

/* m月d日转换为年中第n天 */
int day_of_year(int year, int month, int day)
{
    if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) /* 格式错误 */
        return -1;
    int i, leap;
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    if (day > daytab[leap][month])
        return -1; /* 格式错误：当前月不包含第day天 */
    for (i = 1; i < month; i++)
        day += daytab[leap][i];
    return day;
}

/* 年中第n天转换为m月d日 */
int month_day(int year, int yearday, int *pmonth, int *pday)
{
    if (year < 0 || yearday < 1) /* 格式错误 */
        return -1;
    int i, leap;
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    for (i = 1; yearday > daytab[leap][i]; i++)
        yearday -= daytab[leap][i];
    if (i > 12 || yearday > daytab[leap][i])
        return -1; /* 格式错误：当前月不包含第day天 */
    *pmonth = i;
    *pday = yearday;
    return 0;
}