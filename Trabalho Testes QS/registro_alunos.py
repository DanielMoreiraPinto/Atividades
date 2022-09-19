from boletim import Boletim

class RegistroAlunos:
    
    def __init__(self):
        self.boletins = []
    
    def cadastrar_boletim(self, boletim: Boletim):
        if len(self.boletins) >= 5:
            raise Exception("Sistema de alunos lotado")
        self.boletins.append(boletim)
        
    def __str__(self):
        string = ""
        for i, boletim in enumerate(self.boletins):
            string += f"Aluno {i+1} \n" + str(boletim) + "\n"
        return string
        
        
    