// 产生汇编代码：gcc -Og -S long2double.c
// 产生二进制目标文件：gcc -Og -c long2double.c
// 使用反汇编器对二进制目标文件生成汇编代码：objdump -d long2double.o

double castl2d(long l) {
	double d = (double) l;
	d = 1.2+d;
	return d;
}
