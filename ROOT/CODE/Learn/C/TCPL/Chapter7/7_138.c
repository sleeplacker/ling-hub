#include <stdio.h>

/* scanf函数 */
/* 标准输入示例：1 2 3 4    a   b */
int main()
{
    int i1, i2, i3, i4, result;
    char c1, c2;
    i1 = i2 = i3 = i4 = result = 0;

    /* 
    输入    返回结果
    1 2     2
    1 a     1
    a 1     0
    空      -1
    */
    result = scanf("%d %d", &i1, &i2);
    printf("result = %d\n", result);
    /* 
    空白字符不影响输入字段定义
    有禁止字符*的字段不赋值
     */
    scanf("%*d\t %d", &i3, &i4); /* 输入的第3个数不会被匹配，然后第4个数赋值给i3 */
    printf("i3 = %d, i4 = %d\n", i3, i4);

    /* %c会匹配空白字符，如果想跳过空白字符，需要使用%ls */
    scanf("%c %ls", &c1, &c2);
    printf("c1 = %c, c2 = %c\n", c1, c2);

    return 0;
}