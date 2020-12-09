.LC0:
        .string "%d %d"
.LC1:
        .string "%d\n"
main:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     DWORD PTR [rbp-12], 20
        lea     rdx, [rbp-20]
        lea     rax, [rbp-16]
        mov     rsi, rax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    scanf
        mov     eax, DWORD PTR [rbp-16]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC1
        mov     eax, 0
        call    printf
        mov     eax, DWORD PTR [rbp-20]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC1
        mov     eax, 0
        call    printf
        mov     edx, DWORD PTR [rbp-16]
        mov     eax, DWORD PTR [rbp-20]
        add     eax, edx
        mov     DWORD PTR [rbp-8], eax
        mov     DWORD PTR [rbp-4], 0
        jmp     .L2
.L3:
        mov     eax, DWORD PTR [rbp-8]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC1
        mov     eax, 0
        call    printf
        mov     eax, DWORD PTR [rbp-20]
        mov     DWORD PTR [rbp-16], eax
        mov     eax, DWORD PTR [rbp-8]
        mov     DWORD PTR [rbp-20], eax
        mov     edx, DWORD PTR [rbp-16]
        mov     eax, DWORD PTR [rbp-20]
        add     eax, edx
        mov     DWORD PTR [rbp-8], eax
        add     DWORD PTR [rbp-4], 1
.L2:
        mov     eax, DWORD PTR [rbp-12]
        sub     eax, 2
        cmp     DWORD PTR [rbp-4], eax
        jl      .L3
        mov     eax, 0
        leave
        ret