	.file	"reverse.c"
	.text
	.globl	fun_b
	.def	fun_b;	.scl	2;	.type	32;	.endef
	.seh_proc	fun_b
fun_b:
	.seh_endprologue
	movl	$64, %edx
	movl	$0, %eax
	jmp	.L2
.L3:
	addq	%rax, %rax
	movq	%rcx, %r8
	andl	$1, %r8d
	orq	%r8, %rax
	shrq	%rcx
	decq	%rdx
.L2:
	testq	%rdx, %rdx
	jne	.L3
	ret
	.seh_endproc
	.ident	"GCC: (GNU) 9.2.0"
