char passok=0;

void teste_pass(char* digitado) {
    char vetor2[] = "a%$F@r&L&%qA$#@uM%#@iE$%@tN%$#eG@$#t0$#@u%$@)r)(*&a";
 
    char misterio[148] = {0};

    int i=0;
    while(i<11) {
        if((int)digitado[i] == (int)vetor2[i<<2])
            i++;
        else    
            i = 22;
    }
    passok = (i == 11);
}

int main() {
    
    int i=0, codigoSecreto = 20122020;
    char algo2, digitado[20];
    algo2 = getchar();
    while(algo2 != '\r' && i < 20) {
        digitado[i] = algo2;
        i++;
        algo2 = getchar();
    }
    teste_pass(digitado);
    //passok = 1;
    if(passok == 1) {
        printf("%d", (codigoSecreto >> 2));
    }

    return 1;
}