#include <fcntl.h>
#include <unistd.h>

// #define NULL 0
#define EOF (-1)
#define BUFSIZ 8192
#define OPEN_MAX 20 /* 一次最多可打开的文件数 */

typedef struct _iobuf
{
    int cnt;    /* 剩余的字符数 */
    char *ptr;  /* 下一个字符的位置 */
    char *base; /* 缓冲区的位置 */
    int flag;   /* 文件访问模式 */
    int fd;     /* 文件描述符 */
} FILE;
extern FILE _iob[OPEN_MAX];

#define stdin (&_iob[0])
#define stdout (&_iob[1])
#define stderr (&_iob[2])

enum _flags
{
    _READ = 01,  /* 以读方式打开文件 */
    _WRITE = 02, /* 以写方式打开文件 */
    _UNBUF = 04, /* 不对文件进行缓冲 */
    _EOF = 010,  /* 已到文件的末尾 */
    _ERR = 020   /* 该文件发生错误 */
};

int _fillbuf(FILE *);
// int _flushbuf(int, FILE *);
FILE *fopen(char *name, char *mode);

#define feof(p) (((p)->flag & _EOF) != 0)
#define ferror(p) (((p)->flag & _ERR) != 0)
#define fileno(p) ((p)->fd)

#define getc(p) (--(p)->cnt >= 0                  \
                     ? (unsigned char)*(p)->ptr++ \
                     : _fillbuf(p))
// #define putc(x, p) (--(p)->cnt >= 0         \
//                         ? *(p)->ptr++ = (x) \
//                         : _flushbuf((x), p))

#define getchar() getc(stdin)
// #define putchar putc((x), stdout)