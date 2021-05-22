/* 
类似于isupper这样的函数可以通过某种方式实现
以达到节省空间或时间的目的。考虑节省空间或时间
的实现方式。
 */

/* 1 节省空间的实现
这个版本的isupper函数用了一个简单的if-else结构来检查
字符的大小写情况。如果被测字符落在ASCII大写字母的取值
范围内，isupper函数返回1（真）；否则，返回0（假）。
这个版本的isupper空间利用率较高。
 */
int isupper(char c)
{
    if (c >= 'A' && c <= 'Z')
        return 1;
    else
        return;
}
