import unittest
from boletim import Boletim
from registro_alunos import RegistroAlunos

class TestSistema(unittest.TestCase):
    
    def test_cadastrar_boletins_deve_cadastrar_ate_5_boletins(self):
        # Cenário
        registro = RegistroAlunos()
        boletins = []
        for i in range(5):
            boletim = Boletim()
            boletim.add_nota(i)
            boletim.add_nota(i+1)
            boletim.add_nota(i+2)
            boletins.append(boletim)
        # Ação
        for boletim in boletins:
            registro.cadastrar_boletim(boletim)
        # Verificação
        for i, boletim in enumerate(boletins):
            self.assertIn(boletim, registro.boletins)
            self.assertEqual(boletim.notas, registro.boletins[i].notas)
            self.assertEqual(boletim.media, registro.boletins[i].media)
      
    def test_calc_media_deve_calcular_media_de_boletim(self):
        # Cenário
        boletim = Boletim()
        # Ação
        boletim.add_nota(2)
        boletim.add_nota(4)
        boletim.add_nota(7)
        # Verificação
        self.assertEqual(boletim.media, 4.3)
      
    def test_add_nota_deve_sobrescrever_menor_nota_se_ja_existirem_3_notas(self):
        # Cenário
        boletim = Boletim()
        boletim.add_nota(1)
        boletim.add_nota(2)
        boletim.add_nota(3)
        # Ação
        boletim.add_nota(4)
        boletim.add_nota(5)
        # Verificação
        self.assertEqual(boletim.notas, [4.0, 5.0, 3.0])
          
if __name__ == '__main__':
    unittest.main()