#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

typedef unsigned char *byte_pointer;

// 打印对象的字节序列-按字节位置序列由小到大
void show_bytes(byte_pointer start, size_t len) {
	size_t i;
	for (i = 0; i < len; i++)
		printf(" %.2x", start[i]);
	printf("\n");
}

//打印整数存储序列
void show_int(int x) {
	show_bytes((byte_pointer) &x, sizeof(int));
}
//打印浮点数存储序列
void show_float(float x) {
	show_bytes((byte_pointer) &x, sizeof(float));
}
//打印长整型数存储序列
void show_long(long x) {
	show_bytes((byte_pointer) &x, sizeof(long));
}
//打印64位整型数存储序列
void show_int64(int64_t x) {
	show_bytes((byte_pointer) &x, sizeof(int64_t));
}
//打印双精度数存储序列
void show_double(double x) {
	show_bytes((byte_pointer) &x, sizeof(double));
}
//打印指针存储序列
void show_pointer(void *x) {
	show_bytes((byte_pointer) &x, sizeof(void *));
}
//打印整型数组存储序列
void show_array(int x[], int len) {
	show_bytes((byte_pointer) x, len);
}
//打印字符串存储序列
void show_string(char *x) {
	show_bytes((byte_pointer) x, strlen(x));
}

int main(void) {
	/*下面测试使用64位win10进行，使用MinGW编译为64位程序*/
	/*int 类型，4字节，按小端法存储*/
	int iVal = 0x012345;
	show_int(iVal); //  45 23 01 00

	/*float 类型，4字节，按小端法存储*/
	float fVal = 7.0f; //	0x40E00000
	show_float(fVal); // 00 00 e0 40

	/*long 类型，4字节，按小端法存储，
	 * 尽管是编译为64位程序，但是在
	 * windows系统中仍然认为 long 为 4字节，
	 * 结果是截取低4字节按小端法存储*/
	long lVal = 0x0123456789112233;
	show_long(lVal); // 33 22 11 89

	/*int64_t 类型，8字节，按小端法存储*/
	int64_t i64Val = 0x0123456789112233;
	show_int64(i64Val);//33 22 11 89 67 45 23 01

	/*double 类型，4字节，按小端法存储*/
	double dVal = 7.5;//	0x401E000000000000
	show_double(dVal);// 00 00 00 00 00 00 1e 40

	/*指针类型，8字节，注意，虽然指针不是数字类型，
	 * 但是指针的地址是值也是按小端法存储的*/
	int * p = &iVal;
	printf("pointer value = %p\n", p);//000000000064FDEC
	show_pointer(p);// ec fd 64 00 00 00 00 00

	/*数组 类型，元素占用字节*元素个数个字节，
	 * 每个元素之间按照数组中的顺序存储，
	 * 但是数组中的元素按照小端法存储*/
	int iArr[6] = { 406, 407, 408, 409, 410, 411 };	//0x00000196, ...
	show_array(iArr, sizeof(iArr));// 96 01 00 00 97 01 00 00 98 01 00 00 99 01 00 00 9a 01 00 00 9b 01 00 00

	/*字符串类型，类似数组类型，
	 * 因为本身字符串就是字符的数组，
	 * 每个元素之间也是按照数组中的顺序存储*/
	char *s = "abcdef";
	show_string(s);// 61 62 63 64 65 66

	/* 在 64位 Windows 10 中，编译为64位的程序中的 long 类型也是4字节，
	 * 但是在 Unix 和 Linux 中，编译为 32位程序的long 类型是4字节，
	 * 编译为 64 位程序的long 类型是8字节，在 AIX 上亲测过，注意 AIX 上
	 * 编译为 64 位的命令是 gcc -maix64 proc.c，而不是 gcc -m64 proc.c。
	 * 因此 long 类型的大小不仅与编译器有关，还与操作系统有关
	 */
	printf("size of long = %I64d \n", sizeof(long)); //size of long = 4
	/* 64 位程序中的指针类型都是全字长，即8字节，不管是什么操作系统 */
	printf("size of pointer = %I64d \n", sizeof(p));//size of pointer = 8
	/* 数组的大小 = 元素大小 * 元素个数 */
	printf("size of array = %I64d \n", sizeof(iArr));//size of array = 24
}
