.LC0:
        .string "%d"
main:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     DWORD PTR [rbp-4], 0
        mov     DWORD PTR [rbp-8], 0
        jmp     .L2
.L3:
        lea     rdx, [rbp-32]
        mov     eax, DWORD PTR [rbp-8]
        cdqe
        sal     rax, 2
        add     rax, rdx
        mov     rsi, rax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    scanf
        add     DWORD PTR [rbp-8], 1
.L2:
        cmp     DWORD PTR [rbp-8], 4
        jle     .L3
        mov     DWORD PTR [rbp-8], 0
        jmp     .L4
.L5:
        mov     eax, DWORD PTR [rbp-8]
        cdqe
        mov     eax, DWORD PTR [rbp-32+rax*4]
        add     DWORD PTR [rbp-4], eax
        add     DWORD PTR [rbp-8], 1
.L4:
        cmp     DWORD PTR [rbp-8], 4
        jle     .L5
        mov     eax, DWORD PTR [rbp-4]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    printf
        mov     eax, 0
        leave
        ret