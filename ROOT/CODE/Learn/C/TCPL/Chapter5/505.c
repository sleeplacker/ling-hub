/* 
实现函数strncpy、strncat和strncmp，它们最多
对参数字符串中的前n个字符进行操作。例如，函数strncpy(s,t,n)
将t中最多前n个字符复制到s中。更详细说明请参见附录B。 
*/

/* 从t复制n个字符到s */
void strncpy(char *s, char *t, int n)
{
    while (*t && n-- > 0)
        *s++ = *t++;

    while (n-- > 0)
        *s++ = '\0';
}

/* 在s末尾添加t中的前n个字符 */
void strncat(char *s, char *t, int n)
{
    /* 函数声明 */
    void strncpy(char *s, char *t, int n);
    int strlen(char *);

    strncpy(s + strlen(s), t, n);
}

/* 比较s和t的前n个字符是否相同 */
int strncmp(char *s, char *t, int n)
{
    for (; *s == *t; s++, t++)
        if (*s == '\0' || --n <= 0)
            return 0;
    return *s - *t;
}