
#include <stdio.h>

/*
下面一行声明的函数在另一个文件中定义为 double addoneI(double d) 
但在本文件中可以声明为其他的返回类型和参数类型，而且编译也不会报错和
警告，但是调用这个函数时会有错误的行为
*/
int addoneI(int i);
/* 声明正确的函数返回和参数类型 */
double addoneD(double d);
/* 下面一行声明编译时会报错，因为函数定义在同一个文件中 */
// float addoneC(float f);

/* 编译命令：gcc 4_061_2-1.c 4_061_2-2.c */
int main()
{
    printf("addoneI 返回类型占字节数为 = %ld，类型为 int，但 addoneI 函数定义的返回类型为 double\n", sizeof(addoneI(1)));
    printf("1+1=%d\n", addoneI(1)); // 0，声明类型和定义类型不同时，可能得到错误的结果
    printf("1+1=%f\n", addoneD(1)); // 2.0，声明类型和定义类型相同时，得到正确的结果

    /* 
    addoneL在其他文件定义，且在该文件没有声明就直接调用，
    这种情况会被隐式声明，返回类型为int，且不检查参数，
    因为addoneL定义时只有1个参数，但是这里传了两个参数
    编译也能通过
    */
    printf("隐式声明函数 addoneL 返回类型占字节数为 = %ld，类型为 int，但 addoneL 函数定义的返回类型为 long\n", sizeof(addoneL(1, 1)));
    printf("1+1=%d\n", addoneL(1, 1)); // 0，隐式声明类型和定义类型不同时，可能得到错误的结果

    return 0;
}

char addoneC(char c)
{
    return c + 1;
}