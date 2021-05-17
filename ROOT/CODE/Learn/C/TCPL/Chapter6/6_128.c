#include <stdio.h>
#include <stdlib.h>

/* typedef定义的类型不需要在前面声明 */
typedef struct tnode *Treeptr; /* typedef 中声明的类型在变量名的位置出现，而不是紧接在关键字typedef之后。 */
typedef struct tnode
{
    char *word;
    int count;
    Treeptr left;
    Treeptr right;
} Treenode;

/* 
typedef 类似乎#define语境，但由于typedef是由编译器解释的，
因此它的文本替换功能要超过预处理器的能力。
 */
int main()
{
    Treeptr tp = (Treeptr)malloc(sizeof(Treenode));
    tp->word = "Hello";
    tp->count = 0;
    tp->right = NULL;
    tp->right = NULL;
    printf("%s\n", tp->word);
    return 0;
}