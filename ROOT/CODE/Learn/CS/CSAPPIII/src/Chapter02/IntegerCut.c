// 整数扩展

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start, size_t len) {
	size_t i;
	for (i = 0; i < len; i++)
		printf(" %.2x", start[i]);
	printf("\n");
}

int main(void) {
	int x = 53191;
	short sx = (short) x; // 截断
	int y = sx; // 再扩展，这时已经无法恢复截断前的值了

	printf("x = %d:\t", x);
	show_bytes((byte_pointer) &x, sizeof(int));
	printf("sx = %d:\t", sx);
	show_bytes((byte_pointer) &sx, sizeof(short));
	printf("y = %d:\t", y);
	show_bytes((byte_pointer) &y, sizeof(int));

}
