.LC0:
        .string "%d %d %d"
.LC1:
        .string "%d"
main:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        lea     rcx, [rbp-16]
        lea     rdx, [rbp-12]
        lea     rax, [rbp-8]
        mov     rsi, rax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    scanf
        mov     eax, DWORD PTR [rbp-8]
        mov     DWORD PTR [rbp-4], eax
        mov     eax, DWORD PTR [rbp-12]
        cmp     DWORD PTR [rbp-4], eax
        jge     .L2
        mov     eax, DWORD PTR [rbp-12]
        mov     DWORD PTR [rbp-4], eax
.L2:
        mov     eax, DWORD PTR [rbp-16]
        cmp     DWORD PTR [rbp-4], eax
        jge     .L3
        mov     eax, DWORD PTR [rbp-16]
        mov     DWORD PTR [rbp-4], eax
.L3:
        mov     eax, DWORD PTR [rbp-4]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC1
        mov     eax, 0
        call    printf
        mov     eax, 0
        leave
        ret