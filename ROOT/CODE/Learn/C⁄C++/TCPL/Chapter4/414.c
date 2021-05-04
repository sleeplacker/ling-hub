#include <stdio.h>

#define swap(t, x, y) \
    {                 \
        t _temp = x;  \
        x = y;        \
        y = _temp;    \
    }

/* 
定义宏swap(t,x,y)以交换t类型的两个参数。（使用
程序块结构会对你有所帮助。）

发现：swap宏和swap函数是不一样的，调用swap宏相对于
将宏中定义的语句在main函数中跑了一遍，因此能实现真正
的交换。而调用swap函数需要将参数以值传递方式传给函数，
因此函数中只是交换了值传递参数的副本，不能交换实参。

标准答案补充：正因为宏操作是相对于在main函数中执行的
所以宏中定义的变量不能与main函数中的变量同名，否则会
影响宏的行为，因此宏中使用带下划线的变量名 _temp，
并且约定调用宏的上下文中不能定义下划线开头的变量名
*/
int main()
{
    double d1 = 1.1;
    double d2 = 2.2;
    double d3 = 3.3;
    double d4 = 4.4;
    swap(double, d1, d2); /* 使用宏进行交换，能交换成功 */
    swapfun(d3, d4);      /* 使用函数进行交换，不能交换成功 */
    printf("d1 = %.2f，d2 = %.2f\n", d1, d2);
    printf("d3 = %.2f，d4 = %.2f\n", d3, d4);
}

void swapfun(double x, double y)
{
    double temp = x;
    x = y;
    y = temp;
}
