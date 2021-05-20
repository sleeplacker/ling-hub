#include <stdio.h>
#include <stdlib.h> /* 为了使用atof(函数) */
#include <ctype.h>

/* 
注意：在使用scanf读取单字符时，要使用char类型变量
如果使用int类型变量来接收输入，那么int类型的值是不
确定的，比如：本来'1'为49，但是却得到了32561，虽然
isdigit(32561)也是true，但是有些时候得到的值，比如
817，isdigit(817)是false
*/
int main()
{
    // int c;
    char c;
    printf("%d\n", '1');
    scanf("%c", &c);
    printf("%d\n", c);
    printf("%d\n", isdigit(c));
    return 0;
}
