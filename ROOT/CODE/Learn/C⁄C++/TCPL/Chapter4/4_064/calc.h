#define NUMBER '0' /* 标识找到一个数 */
int getop(char[]);
int getopline(char[]);
void push(double);
double pop(void);
void clear(void);
int getch(void);
void ungetch(int);
int getaline(char s[], int lim);