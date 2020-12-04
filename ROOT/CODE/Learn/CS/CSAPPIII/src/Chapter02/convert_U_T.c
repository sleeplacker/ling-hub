// 有符号数和无符号数之间的转换

#include <stdio.h>

int main(void) {
	//	有符号转无符号
	short int v = -12345;
	unsigned short uv = (unsigned short) v;
	printf("v = %d, uv = %u\n", v, uv);
	//无符号转有符号
	unsigned u = 4294967295u; // UMax
	int tu = (int) u;
	printf("u=  %u, tu = %d\n", u, tu);

	//	表达式中的隐式转换
	int v1 = -3;
	unsigned int uv1 = 2;
	if (v1 + uv1 > 0)
		printf("true\n");// v1 被隐式转换为无符号数，所以结果虽然是-1，但是是一个很大的无符号数
	else
		printf("false\n");

	if (v1 + (int) uv1 > 0)
		printf("true\n");
	else
		printf("false\n");// 强制将 uv1 转换为有符号数，所以结果也是有符号数 -1

	return 0;
}
