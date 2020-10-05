Sistema operacional: Ubuntu 20.04.1 LTS 64-bit
Versão gcc: 9.3.0 (Ubuntu 9.3.0-10ubuntu2) 
Ambiente utilizado: Visual Studio Code (execução em terminal Ubuntu)

Não foi possível comṕilar o programa, a compilação gera o seguinte erro apesar do header ser devidamente definido:

/usr/bin/ld: /tmp/ccvBv0PK.o: in function `main':
usuario.c:(.text+0xd9): undefined reference to `colCriar'
/usr/bin/ld: usuario.c:(.text+0x153): undefined reference to `colInserir'
/usr/bin/ld: usuario.c:(.text+0x196): undefined reference to `meuQsort'
/usr/bin/ld: usuario.c:(.text+0x1e5): undefined reference to `colBuscarPorIndice'
/usr/bin/ld: usuario.c:(.text+0x1f7): undefined reference to `colRetirar'
/usr/bin/ld: usuario.c:(.text+0x268): undefined reference to `colInserir'
/usr/bin/ld: usuario.c:(.text+0x2ab): undefined reference to `meuQsort'
/usr/bin/ld: usuario.c:(.text+0x2fa): undefined reference to `colBuscarPorIndice'
/usr/bin/ld: usuario.c:(.text+0x30c): undefined reference to `colRetirar'
/usr/bin/ld: usuario.c:(.text+0x360): undefined reference to `colDestruir'
collect2: error: ld returned 1 exit status