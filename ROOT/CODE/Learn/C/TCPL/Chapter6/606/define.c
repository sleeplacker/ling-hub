#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define MAXWORD 100
#define HASHSIZE 101

struct nlist
{
    struct nlist *next;
    char *name;
    char *defn;
};

static struct nlist *hashtable[HASHSIZE];
unsigned hash(char *);
void error(int, char *);
int getch(void);
void getdef(void);
int getword(char *, int);
struct nlist *install(char *, char *);
struct nlist *lookup(char *);
void skipblanks(void);
void undef(char *);
void ungetch(int);
void ungets(char *);

/* 
以本节介绍的函数为基础，编写一个适合C语言程序使用
的#define处理器的简单版本(即无参数的情况)。你会
发现getch和ungetch函数非常有用。
 */
/* 编译命令：gcc define.c getch.c getword.c */
/* 执行命令：./a.out < define.c >xxx.c */
int main()
{
    char w[MAXWORD];
    struct nlist *p;

    while (getword(w, MAXWORD) != EOF)
        if (strcmp(w, "#") == 0)
            getdef();
        else if (!isalpha(w[0]))
            printf("%s", w);
        else if ((p = lookup(w)) == NULL)
            printf("%s", w);
        else
            ungets(p->defn);
    return 0;
}

unsigned hash(char *s)
{
    unsigned hashval;
    for (hashval = 0; *s != '\0'; s++)
        hashval = *s + 31 * hashval;
    return hashval % HASHSIZE;
}

struct nlist *lookup(char *s)
{
    struct nlist *np;
    for (np = hashtable[hash(s)]; np != NULL; np = np->next)
        if (strcmp(s, np->name) == 0)
            return np;
    return NULL;
}

struct nlist *install(char *name, char *defn)
{
    struct nlist *np;
    unsigned hashval;

    if ((np = lookup(name)) == NULL)
    {
        np = (struct nlist *)malloc(sizeof(*np));
        if (np == NULL || (np->name = strdup(name)) == NULL)
            return NULL;
        hashval = hash(name);
        np->next = hashtable[hashval];
        hashtable[hashval] = np;
    }
    else
        free((void *)np->defn);            /* 新的同名宏定义会覆盖老的定义内容 */
    if ((np->defn = strdup(defn)) == NULL) /* 加入/修改hash定义 */
        return NULL;
    return np;
}

void getdef(void)
{
    int c, i;
    char def[MAXWORD], dir[MAXWORD], name[MAXWORD];

    skipblanks();
    if (!isalpha(getword(dir, MAXWORD)))
        error(dir[0], "getdef: expecting a directiveafter #");
    else if (strcmp(dir, "define") == 0)
    {
        skipblanks();
        if (!isalpha(getword(name, MAXWORD)))
            error(name[0], "getdef: non-alpha - name expected");
        else
        {
            skipblanks();
            for (i = 0; i < MAXWORD - 1; i++)
                if ((def[i] = getch()) == EOF || def[i] == '\n')
                    break;
            def[i] = '\0';
            if (i <= 0)
                error('\n', "getdef; incomplete define");
            else
                install(name, def);
        }
    }
    else if (strcmp(dir, "undef") == 0)
    {
        skipblanks();
        if (!isalpha(getword(name, MAXWORD)))
            error(name[0], "getdef: non-alpha in undef");
        else
            undef(name);
    }
    else if (strcmp(dir, "include") == 0)
        printf("#%s", dir);
    else
        error(dir[0], "getdef: expecting a directive after #");
}

void error(int c, char *s)
{
    printf("error: %s\n", s);
    while (c != EOF && c != '\n')
        c = getch();
}

void skipblanks(void)
{
    int c;
    while ((c = getch()) == ' ' || c == '\t')
        ;
    ungetch(c);
    {
        /* code */
    }
}

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