class Boletim:
    
    def __init__(self):
        self.notas = []
        self.media = 0
    
    def calc_media(self):
        self.media = round(sum(self.notas) / len(self.notas), 1)
            
    def add_nota(self, nota: float):
        try:
            nota = float(nota)
        except:
            raise TypeError("Nota deve ser um número real")
        if nota < 0 or nota > 10:
            raise Exception("Nota inválida")
        if len(self.notas) >= 3 and nota > min(self.notas):
            self.notas[self.notas.index(min(self.notas))] = nota
        else:
            self.notas.append(nota)
        self.calc_media()
        
    def situacao(self):
        if self.media >= 7:
            return "Aprovado"
        return "Reprovado"
        
    def __str__(self):
        return 'Notas: {}\nMédia: {}'.format(self.notas, self.media)