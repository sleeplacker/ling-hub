
#include <stdio.h>

void squeeze(char s1[], char s2[]);
/* 
重新编写函数squeeze(s1,s2)，将字符串s1中中任何与字符串s2中的字符匹配的字符删除。 
*/
int main()
{
    char s1[] = "abcdefg";
    char s2[] = "cf";
    squeeze(s1, s2);
    printf("\"abcdefg\" remove \"cf\" = %s", s1);
    return 0;
}

void squeeze(char s1[], char s2[])
{
    int i, j, k;
    for (i = k = 0; s1[i] != '\0'; ++i)
    {
        for (j = 0; s2[j] != '\0'; ++j)
            if (s1[i] == s2[j])
                break;
        if (s2[j] == '\0') //表示找遍s2都没找到匹配的字符
            s1[k++] = s1[i];
    }
    s1[k] = '\0';
}