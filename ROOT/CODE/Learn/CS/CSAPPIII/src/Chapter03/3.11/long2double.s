	.file	"long2double.c"
	.text
	.globl	castl2d
	.def	castl2d;	.scl	2;	.type	32;	.endef
	.seh_proc	castl2d
castl2d:
	.seh_endprologue
	pxor	%xmm0, %xmm0
	cvtsi2sdl	%ecx, %xmm0
	addsd	.LC0(%rip), %xmm0
	ret
	.seh_endproc
	.section .rdata,"dr"
	.align 8
.LC0:
	.long	858993459
	.long	1072902963
	.ident	"GCC: (GNU) 9.2.0"
