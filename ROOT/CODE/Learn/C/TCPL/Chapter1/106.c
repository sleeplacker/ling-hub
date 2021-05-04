
#include <stdio.h>

/* 验证表达式getchar() != EOF 的 值是0还是1 */
int main()
{
    if (getchar() != EOF)
        printf("true\n");// 输入非文件结束符打印true
    else
        printf("false\n");// 输入文件结束符(UNIX/Linux中是 ctrl+d)会打印false
    return 0;
}
