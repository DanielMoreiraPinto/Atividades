.LC0:
        .string "%d %d"
.LC2:
        .string "%f"
main:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        lea     rdx, [rbp-20]
        lea     rax, [rbp-16]
        mov     rsi, rax
        mov     edi, OFFSET FLAT:.LC0
        mov     eax, 0
        call    scanf
        mov     eax, DWORD PTR [rbp-16]
        pxor    xmm0, xmm0
        cvtsi2ss        xmm0, eax
        movss   DWORD PTR [rbp-12], xmm0
        mov     eax, DWORD PTR [rbp-20]
        mov     DWORD PTR [rbp-8], eax
        mov     eax, DWORD PTR [rbp-20]
        test    eax, eax
        jns     .L2
        neg     DWORD PTR [rbp-8]
.L2:
        mov     DWORD PTR [rbp-4], 0
        jmp     .L3
.L4:
        mov     eax, DWORD PTR [rbp-16]
        pxor    xmm0, xmm0
        cvtsi2ss        xmm0, eax
        movss   xmm1, DWORD PTR [rbp-12]
        mulss   xmm0, xmm1
        movss   DWORD PTR [rbp-12], xmm0
        add     DWORD PTR [rbp-4], 1
.L3:
        mov     eax, DWORD PTR [rbp-8]
        sub     eax, 1
        cmp     DWORD PTR [rbp-4], eax
        jl      .L4
        mov     eax, DWORD PTR [rbp-20]
        test    eax, eax
        jns     .L5
        movss   xmm0, DWORD PTR .LC1[rip]
        divss   xmm0, DWORD PTR [rbp-12]
        movss   DWORD PTR [rbp-12], xmm0
.L5:
        pxor    xmm2, xmm2
        cvtss2sd        xmm2, DWORD PTR [rbp-12]
        movq    rax, xmm2
        movq    xmm0, rax
        mov     edi, OFFSET FLAT:.LC2
        mov     eax, 1
        call    printf
        mov     eax, 0
        leave
        ret
.LC1:
        .long   1065353216