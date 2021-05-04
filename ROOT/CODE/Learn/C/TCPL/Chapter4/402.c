#include <ctype.h>
#include <stdio.h>
/* atof: convert string s to double */
double atof(char s[]);
int main()
{

    printf("1.23 = %f\n", atof("1.23"));
    printf("1.23E15 %f\n", atof("1.23E15"));
    return 0;
}

double atof(char s[])
{
    double val, power;
    int i, sign, sign1, times;

    for (i = 0; isspace(s[i]); i++)
        ;
    sign = (s[i] == '-') ? -1 : 1;
    if (s[i] == '+' || s[i] == '-')
        i++;
    for (val = 0.0; isdigit(s[i]); i++)
        val = 10.0 * val + (s[i] - '0');
    if (s[i] == '.')
        i++;
    for (power = 1.0; isdigit(s[i]); i++)
    {
        val = 10.0 * val + (s[i] - '0');
        power *= 10.0;
    }
    if (s[i] == 'e' || s[i] == 'E')
    {
        ++i;
        sign1 = s[i] == '-' ? -1 : 1;
        if (s[i] == '+' || s[i] == '-')
            ++i;
        for (times = 0; isdigit(s[i]); ++i)
            times = times * 10 + s[i] - '0';
        while (times-- > 0)
        {
            if (sign1 == 1)
                val *= 10.0;
            else
                power *= 10.0;
        }
    }

    return sign * val / power;
}