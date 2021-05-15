#include <stdio.h>
#include <string.h>
#include <stdlib.h>
struct nlist
{
    struct nlist *next;
    char *name;
    char *defn;
};
#define HASHSIZE 101

static struct nlist *hashtable[HASHSIZE];
unsigned hash(char *);

/* 
编写函数undef，它将由lookup和install维护的表中
删除一个变量名及其定义。
 */
void undef(char *s)
{
    int h;
    struct nlist *prev, *np;

    prev = NULL;
    h = hash(s); /* 计算字符串s的哈希值 */
    for (np = hashtable[h]; np != NULL; np = np->next)
    {
        if (strcmp(s, np->name) == 0)
            break;
        prev = np; /* 记住上一个链表元素 */
    }
    if (np != NULL) /* 找到了名字定义 */
    {
        if (prev == NULL)            /* 名字在链表第一项 */
            hashtable[h] = np->next; /* 直接将hash表这个位置的链表头指向np的next指针指向的位置 */
        else                         /* 名字在链表第二个元素或以后 */
            prev->next = np->next;   /* 将上一个元素的next指针指向np的next指针指向的位置 */
        free((void *)np->name);      /* 清理空间 */
        free((void *)np->defn);
        free((void *)np);
    }
}