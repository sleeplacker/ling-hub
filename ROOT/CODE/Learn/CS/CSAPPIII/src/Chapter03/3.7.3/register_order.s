	.file	"register_order.c"
	.text
	.globl	proc
	.def	proc;	.scl	2;	.type	32;	.endef
	.seh_proc	proc
proc:
	.seh_endprologue
	movl	%ecx, %r10d
	movq	%rdx, %rcx
	movq	48(%rsp), %rdx
	movq	64(%rsp), %rax
	addl	%r10d, (%rcx)
	addl	%r8d, (%r9)
	movl	40(%rsp), %ecx
	addw	%cx, (%rdx)
	movl	56(%rsp), %edx
	addb	%dl, (%rax)
	ret
	.seh_endproc
	.ident	"GCC: (GNU) 9.2.0"
