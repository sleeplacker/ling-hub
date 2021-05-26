#include <stddef.h>
#include <wchar.h>
#include <locale.h>

/* 
打印中文单字符 
注意：printf 和 wprintf不能混用，putchar和putwchar也不能混用
*/
int main()
{
    int i;
    char c[10] = "好的";
    setlocale(LC_ALL, "");
    wchar_t wc[10] = L"好的";
    wchar_t w = L'宽';
    for (i = 0; i < 10; ++i)
    {
        if (c[i] != '\0')
            putwchar(c[i]); /* 不关心内容，因为这里本来应该用putchar，但是不能和后面的putwchar混用 */
        else
        {
            break;
        }
    }
    wprintf(L"\nchar个数：%d\n", i);
    for (i = 0; i < 10; ++i)
    {
        if (wc[i] != '\0')
            putwchar(wc[i]);
        else
        {
            break;
        }
    }
    wprintf(L"\nwchar个数：%d\n", i);
    putwchar(w);
    return 0;
}