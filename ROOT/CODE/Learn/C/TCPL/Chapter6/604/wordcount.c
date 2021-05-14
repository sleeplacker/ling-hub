#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

#define MAXWORD 100

struct tnode
{
    char *word;
    int count;
    struct tnode *left;
    struct tnode *right;
};

struct tnode *addtreex(struct tnode *, char *);
int getword(char *, int);
void treexprint(struct tnode *);
void sort(struct tnode *root, struct tnode *add);
struct tnode *talloc(void);

/* 
编写一个程序，根据单词出现频率按降序打印输入的各个
不用的单词，并在每个单词前面标上它的出现次数。
 */
/* 编译命令 gcc wordcount.c getword.c getch.c */
/* 执行命令：./a.out < wordcount.c */
int main()
{
    struct tnode *root;
    struct tnode *newroot = talloc(); /* 构造一个新的根节点用于进行排序 */
    char word[MAXWORD];
    int linenum = 1;

    root = NULL;
    while (getword(word, MAXWORD) != EOF)
        if (isalpha(word[0]))
            root = addtreex(root, word);

    newroot->count = 0;
    newroot->word = "";
    sort(newroot, root); /* 将按字符串排序的树进行遍历，然后在遍历过程中将访问节点按单词频率倒序插入新树 */
    treexprint(newroot);
    return 0;
}

struct tnode *talloc(void)
{
    return (struct tnode *)malloc(sizeof(struct tnode));
}

struct tnode *addtreex(struct tnode *p, char *w)
{
    int cond;
    if (p == NULL)
    {
        p = talloc();
        p->count = 1;
        p->word = strdup(w);
        p->left = p->right = NULL;
    }
    else if ((cond = strcmp(w, p->word)) == 0)
        p->count++;
    else if (cond < 0)
        p->left = addtreex(p->left, w);
    else
        p->right = addtreex(p->right, w);
    return p;
}

struct tnode *insert(struct tnode *p, struct tnode *q)
{
    int cond = p->count < q->count;
    if (cond)
        if (p->left == NULL)
            p->left = q;
        else
            p->left = insert(p->left, q);
    else
    {
        if (p->right == NULL)
            p->right = q;
        else
            p->right = insert(p->right, q);
    }
    return p;
}

void sort(struct tnode *root, struct tnode *add)
{
    if (add == NULL)
        return;
    struct tnode *temp = add->right;
    sort(root, add->left);
    add->left = add->right = NULL;
    insert(root, add);
    sort(root, temp);
}

void treexprint(struct tnode *p)
{
    if (p != NULL)
    {
        treexprint(p->left);
        if (p->count > 0)
            printf("%5d %-10s\n", p->count, p->word);
        treexprint(p->right);
    }
}
