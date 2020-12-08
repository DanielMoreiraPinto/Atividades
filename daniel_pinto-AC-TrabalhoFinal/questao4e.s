.LC0:
        .string "%d"
main:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        lea     rax, [rbp-8]
        mov     rsi, rax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    scanf
        mov     eax, DWORD PTR [rbp-8]
        mov     DWORD PTR [rbp-4], eax
        mov     eax, DWORD PTR [rbp-8]
        sub     eax, 1
        mov     DWORD PTR [rbp-8], eax
        jmp     .L2
.L3:
        mov     eax, DWORD PTR [rbp-8]
        mov     edx, DWORD PTR [rbp-4]
        imul    eax, edx
        mov     DWORD PTR [rbp-4], eax
        mov     eax, DWORD PTR [rbp-8]
        sub     eax, 1
        mov     DWORD PTR [rbp-8], eax
.L2:
        mov     eax, DWORD PTR [rbp-8]
        cmp     eax, 1
        jg      .L3
        mov     eax, DWORD PTR [rbp-4]
        mov     esi, eax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    printf
        mov     eax, 0
        leave
        ret