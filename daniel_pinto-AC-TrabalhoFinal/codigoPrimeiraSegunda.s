passok:
        .zero   1
.LC0:
        .string "a%$F@r&L&%qA$#@uM%#@iE$%@tN%$#eG@$#t0$#@u%$@)r)(*&a"
        .zero   148
teste_pass:
        push    %rbp
        mov     %rbp, %rsp
        sub     %rsp, 224
        mov     QWORD PTR [%rbp-216], %rdi
        mov     %rax, QWORD PTR .LC0(%rip)
        mov     QWORD PTR [%rbp-208], %rax
        mov     %rax, QWORD PTR .LC0+8(%rip)
        mov     QWORD PTR [%rbp-200], %rax
        mov     %rax, QWORD PTR .LC0+16(%rip)
        mov     QWORD PTR [%rbp-192], %rax
        mov     %rax, QWORD PTR .LC0+24(%rip)
        mov     QWORD PTR [%rbp-184], %rax
        mov     %rax, QWORD PTR .LC0+32(%rip)
        mov     QWORD PTR [%rbp-176], %rax
        mov     %rax, QWORD PTR .LC0+40(%rip)
        mov     QWORD PTR [%rbp-168], %rax
        mov     %eax, DWORD PTR .LC0+48(%rip)
        mov     DWORD PTR [%rbp-160], %eax
        lea     %rdi, [%rbp-156]
        mov     %edx, 148
        mov     %esi, 0
        call    memset
        mov     DWORD PTR [%rbp-4], 0
        jmp     .L8
.L3:
        mov     %eax, DWORD PTR [%rbp-4]
        cdqe
        add     %rax, QWORD PTR [%rbp-216]
        movzx   %ecx, BYTE PTR [%rax]
        mov     %edx, DWORD PTR [%rbp-4]
        mov     %eax, %edx
        sal     %eax, 2
        add     %eax, %edx
        cdqe
        movzx   %eax, BYTE PTR [%rbp-208+%rax]
        cmp     %cl, %al
        jne     .L4
        inc     DWORD PTR [%rbp-4]
        jmp     .L2
.L4:
        mov     DWORD PTR [%rbp-4], 22
.L2:
.L8:
        cmp     DWORD PTR [%rbp-4], 10
        jle     .L3
        cmp     DWORD PTR [%rbp-4], 11
        sete    %al
        mov     BYTE PTR passok(%rip), %al
        leave
        ret
.LC1:
        .string "(%d)"
main:
        push    %rbp
        mov     %rbp, %rsp
        sub     %rsp, 48
        mov     DWORD PTR [%rbp-16], 0
        mov     DWORD PTR [%rbp-8], 20122020
        call    getchar
        mov     BYTE PTR [%rbp-1], %al
        jmp     .L10
.L11:
        mov     %eax, DWORD PTR [%rbp-16]
        movsx   %rdx, %eax
        movzx   %eax, BYTE PTR [%rbp-1]
        mov     BYTE PTR [%rbp-48+%rdx], %al
        inc     DWORD PTR [%rbp-16]
        call    getchar
        mov     BYTE PTR [%rbp-1], %al
.L10:
        cmp     BYTE PTR [%rbp-1], 13
        je      .L12
        cmp     DWORD PTR [%rbp-16], 19
        jle     .L11
.L12:
        lea     %rdi, [%rbp-48] 
        call    teste_pass 
        movzx   %eax, BYTE PTR passok(%rip) # deletar
        cmp     %al, 1 # deletar
        jne     .L14 # deletar
        mov     %eax, DWORD PTR [%rbp-8]
        mov     %esi, %eax
        sar     %esi, 2
        mov     %edi, OFFSET FLAT:.LC1
        mov     %eax, 0
        call    printf
.L14:
        mov     %eax, 1
        leave
        ret