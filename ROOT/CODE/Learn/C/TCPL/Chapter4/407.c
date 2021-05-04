int main()
{
    /* 
    编写一个函数ungets(s)，将整个字符串s压
    回到输入中。ungets函数需要使用buf和bufp
    吗？它能否仅使用ungetch函数?

    自己答案：需要使用bufp，不能仅使用ungetch函数，
    因为还需要使用BUFSIZE和bufp来判断字符串
    长度是否超出缓冲区剩余空间

    标准答案：不需要直接对buf和bufp进行操作，检查
    由getch和ungetch函数来做（个人倾向自己答案）
    
    看 
    ./4_064/getch.c 
    */
    return 0;
}
