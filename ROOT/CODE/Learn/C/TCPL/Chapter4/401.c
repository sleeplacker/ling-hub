
#include <stdio.h>
#include <string.h>

int strrindex(char s[], char t[]);
/* 
编写strrindex(s,t),它返回字符串t在s中最右边
出现的位置。如果s中不包含t，则返回-1
*/
int main()
{

    char s1[] = "And then Re-mould it nearer to the Heart's Besire!";
    char s2[] = "ear";
    char s3[] = "sha";
    printf("find \"ear\" in s1 at index : %d\n", strrindex(s1, s2));
    printf("find \"sha\" in s1 at index : %d\n", strrindex(s1, s3));
    return 0;
}
int strrindex(char s[], char t[])
{
    int i, j, k;

    for (i = strlen(s) - strlen(t); i >= 0; --i)
    {
        for (j = i, k = 0; t[k] != '\0' && s[j] == t[k]; j++, k++)
            ;
        if (k > 0 && t[k] == '\0')
            return i;
    }
    return -1;
}