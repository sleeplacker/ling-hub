
#include <stdio.h>

int binsearch(int x, int v[], int n);

/* 在上面有关折半查找的例子中，while循环语句内执行了
两次测试，其实只要一次就足够（代价是将更多的测试在循环
外执行）。重新写该函数，使得在循环内部只执行一次测试。
比较这两种版本的函数的运行时间。 

改进后的方案在执行时间上几乎没有差异，而且失去了代码可读性
*/
int main()
{
    int v[] = {1, 2, 4, 5, 8, 11, 18, 112};
    int search1 = binsearch(10, v, 8);
    int search2 = binsearch(11, v, 8);
    printf("search 10 index = %d\n", search1);
    printf("search 11 index = %d\n", search2);
    return 0;
}

int binsearch(int x, int v[], int n)
{
    int low, high, mid;
    low = 0;
    high = n - 1;
    while (low <= high && x != v[mid])
    {
        mid = (low + high) / 2;
        if (x < v[mid])
            high = mid - 1;
        else
            low = mid + 1;
    }
    if (v[mid] == x)
        return mid;
    return -1;
}