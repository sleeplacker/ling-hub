#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

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
	show_bytes((byte_pointer) &x, 8);
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

int main(void) {
	int iVal = 0x012345;
	show_int(iVal);
	// 0 10000001 11000000000000000000000
	//	0x40E00000
	float fVal = 7.0f;
	show_float(fVal);
	long lVal = 0x0123456789112233;
	int64_t i64Val = 0x0123456789112233;
	show_long(lVal);
	show_int64(i64Val);
	//	0 10000000001 1110000000000000000000000000000000000000000000000000
	//	0x401E000000000000
	double dVal = 7.5;
	show_double(dVal);
	int * p = &iVal;
	show_pointer(p);

	printf("size of long = %d\n",sizeof(long));
	printf("size of pointer = %d\n",sizeof(p));
}
