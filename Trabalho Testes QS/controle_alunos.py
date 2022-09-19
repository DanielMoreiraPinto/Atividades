from boletim import Boletim
from registro_alunos import RegistroAlunos

registro = RegistroAlunos()
while True:
    print("Sistema de registro de boletins, escolha uma opção:")
    print("1 - Cadastrar boletim")
    print("2 - Listar boletins")
    print("3 - Sair")
    op = input()
    
    if op == '1':
        try:
            boletim = Boletim()
            print("Digite as notas do aluno:")
            for i in range(3):
                nota = input(f"Nota {i+1}: ")
                boletim.add_nota(nota)
            print(str(boletim) + "\n")
            if boletim.situacao() == "Reprovado":
                print("Aluno em recuperação")
                nota = input("Insira a nota de recuperação: ")
                boletim.add_nota(nota)
            print(str(boletim) + "\n" + f"Aluno {boletim.situacao()}")
            registro.cadastrar_boletim(boletim)
        except Exception as e:
            print("Erro: " + str(e))
        
    elif op == '2':
        print(registro)
        
    elif op == '3':
        break
    
    else:
        print("Opção inválida")