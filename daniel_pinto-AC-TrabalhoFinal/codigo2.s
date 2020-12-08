.intel syntax
.file "aula.c"
.section .text$getchar,"x"
.linkonce discard
.globl _getchar
.globl _main
.def _getchar; .scl 2; .type 32; .endef
_getchar:
LFB2:
.cfi_startproc
pushl %ebp
.cfi_def_cfa_offset 8
.cfi_offset 5, -8
movl %esp, %ebp
.cfi_def_cfa_register 5
subl $24, %esp
movl __imp___iob, %eax
movl 4(%eax), %eax
leal -1(%eax), %edx
movl __imp___iob, %eax
movl %edx, 4(%eax)
movl __imp___iob, %eax
movl 4(%eax), %eax
testl %eax, %eax
js L2
movl __imp___iob, %eax
movl (%eax), %eax
leal 1(%eax), %ecx
movl __imp___iob, %edx
movl %ecx, (%edx)
movzbl (%eax), %eax
movzbl %al, %eax
jmp L4
L2:
movl __imp___iob, %eax
movl %eax, (%esp)
call __filbuf
L4:
leave
.cfi_restore 5
.cfi_def_cfa 4, 4
ret
.cfi_endproc
LFE2:
.section .text$putchar,"x"
.linkonce discard
.globl _putchar
.def _putchar; .scl 2; .type 32; .endef
_putchar:
LFB3:
.cfi_startproc
pushl %ebp
.cfi_def_cfa_offset 8
.cfi_offset 5, -8
movl %esp, %ebp
.cfi_def_cfa_register 5
subl $24, %esp
movl __imp___iob, %eax
movl 36(%eax), %eax
leal -1(%eax), %edx
movl __imp___iob, %eax
movl %edx, 36(%eax)
movl __imp___iob, %eax
movl 36(%eax), %eax
testl %eax, %eax
js L6
movl __imp___iob, %eax
movl 32(%eax), %eax
leal 1(%eax), %ecx
movl __imp___iob, %edx
movl %ecx, 32(%edx)
movl 8(%ebp), %edx
movb %dl, (%eax)
movzbl (%eax), %eax
movzbl %al, %eax
jmp L8
L6:
movl __imp___iob, %eax
addl $32, %eax
movl %eax, 4(%esp)
movl 8(%ebp), %eax
movl %eax, (%esp)
call __flsbuf
L8:
leave
.cfi_restore 5
.cfi_def_cfa 4, 4
ret
.cfi_endproc
LFE3:
.globl _passok
.data
_passok:
.byte 7
.text
.globl __Z10teste_passPc
.def __Z10teste_passPc; .scl 2; .type 32; .endef
__Z10teste_passPc:
LFB12:
.cfi_startproc
pushl %ebp
.cfi_def_cfa_offset 8
.cfi_offset 5, -8
movl %esp, %ebp
.cfi_def_cfa_register 5
pushl %edi
subl $208, %esp
.cfi_offset 7, -12
movl $589571425, -208(%ebp)
movl $707162688, -204(%ebp)
movl $628172070, -200(%ebp)
movl $1967137572, -196(%ebp)
movl $1076045092, -192(%ebp)
movl $623125609, -188(%ebp)
movl $623146048, -184(%ebp)
movl $560276260, -180(%ebp)
movl $1948460096, -176(%ebp)
movl $1076044837, -172(%ebp)
movl $1076110709, -168(%ebp)
movl $673804841, -164(%ebp)
movl $6366762, -160(%ebp)
leal -156(%ebp), %edx
movl $0, %eax
movl $37, %ecx
movl %edx, %edi
rep stosl
movl $0, -8(%ebp)
L13:
cmpl $10, -8(%ebp)
jg L10
movl -8(%ebp), %edx
movl 8(%ebp), %eax
addl %edx, %eax
movzbl (%eax), %ecx
movl -8(%ebp), %edx
movl %edx, %eax
sall $2, %eax
addl %edx, %eax
movzbl -208(%ebp,%eax), %eax
cmpb %al, %cl
jne L11
addl $1, -8(%ebp)
jmp L13
L11:
movl $22, -8(%ebp)
jmp L13
L10:
cmpl $11, -8(%ebp)
jne L14
movl $65, %eax
jmp L15
L14:
movl $7, %eax
L15:
movb %al, _passok
nop
addl $208, %esp
popl %edi
.cfi_restore 7
popl %ebp
.cfi_restore 5
.cfi_def_cfa 4, 4
ret
.cfi_endproc
LFE12:
.def ___main; .scl 2; .type 32; .endef
.globl _main
.def _main; .scl 2; .type 32; .endef
_main:
LFB13:
.cfi_startproc
pushl %ebp
.cfi_def_cfa_offset 8
.cfi_offset 5, -8
movl %esp, %ebp
.cfi_def_cfa_register 5
andl $-16, %esp
subl $48, %esp
call ___main
movl $0, 44(%esp)
call _getchar
movb %al, 43(%esp)
L18:
cmpb $10, 43(%esp)
je L17
cmpl $19, 44(%esp)
jg L17
movl 44(%esp), %eax
leal 1(%eax), %edx
movl %edx, 44(%esp)
movzbl 43(%esp), %edx
movb %dl, 23(%esp,%eax)
call _getchar
movb %al, 43(%esp)
jmp L18
L17:
leal 23(%esp), %eax
movl %eax, (%esp)
call __Z10teste_passPc
movzbl _passok, %eax
movsbl %al, %eax
movl %eax, (%esp)
call _putchar
movl $1, %eax
leave
.cfi_restore 5
.cfi_def_cfa 4, 4
ret
.cfi_endproc
LFE13:
.def __filbuf; .scl 2; .type 32; .endef
.def __flsbuf; .scl 2; .type 32; .endef