import numpy as np
import pandas as pd
import os 

abspath = os.path.abspath(__file__)
dname = os.path.dirname(abspath)
os.chdir(dname)

# python script.py
    
def definir_codigos():
    codes = pd.read_csv('codes.csv', header=None).to_numpy()
    income = pd.read_csv('income.csv', header=None).to_numpy()
    literacy = pd.read_csv('literacy.csv', header=None).to_numpy()

    inc = []
    lit = []
    for row in income:
        for code in codes:
            if row[1] == code[1]:
                inc.append([code[0], row[2]])
                break
    for row in literacy:
        for code in codes:
            if row[1] == code[1]:
                lit.append([code[0], row[2]])
                break
    inc = np.array(inc)
    lit = np.array(lit)
    #print(inc)
    with open('income2.csv', 'w') as csv_file:
        for row in inc:
            #print(row[0] + ";" + str(row[1]).replace(".", ","))
            csv_file.write(row[0] + ";" + str(row[1]).replace(".", ",") + "\n")
    with open('literacy2.csv', 'w') as csv_file:
        for row in lit:
            #print(row[0] + ";" + str(row[1]).replace(".", ","))
            csv_file.write(row[0] + ";" + str(row[1]).replace(".", ",") + "\n")
    
def separar_amostra():
    ### Coletando e formatando os dados ###

    bb_pricing = pd.read_csv('bb_pricing.csv', header=None).to_numpy()
    md_pricing = pd.read_csv('md_pricing.csv', header=None).to_numpy()
    bb_speed = pd.read_csv('bb_speed.csv', header=None).to_numpy()
    income = pd.read_csv('income2.csv', header=None, sep=';').to_numpy()
    literacy = pd.read_csv('literacy2.csv', header=None, sep=';').to_numpy()
    print(income)
    print()
    print(literacy)

    ### Fazendo o join das tabelas ###

    #np.random.shuffle(bb_pricing)
    #np.random.shuffle(md_pricing)
    #np.random.shuffle(bb_speed)
    #print(bb_pricing)

    codes_bbp = []
    codes_mdp = []
    codes_bbs = []
    codes_inc = []
    codes_lit = []
    for row in bb_pricing:
        codes_bbp.append(row[0])
    for row in md_pricing:
        codes_mdp.append(row[0])
    for row in bb_speed:
        codes_bbs.append(row[0])
    for row in income:
        codes_inc.append(row[0])
    for row in literacy:
        codes_lit.append(row[0])
    codes_bbp = np.array(codes_bbp)
    codes_mdp = np.array(codes_mdp)
    codes_bbs = np.array(codes_bbs)
    codes_inc = np.array(codes_inc)
    codes_lit = np.array(codes_lit)
    #print(codes_inc)

    bbp_dict = dict(bb_pricing)
    bbs_dict = dict(bb_speed)
    inc_dict = dict(income)
    lit_dict = dict(literacy)
    print(lit_dict)
    print()
    print(inc_dict)
    
    # Uso o md_pricing como base por ter mais países
    final_csv = []
    i = 0
    for row in md_pricing:
        #if i >= 150:
            #break
        #i += 1
        if row[0] in codes_bbp and row[0] in codes_bbs and row[0] in codes_inc and row[0] in codes_lit:
            key = row[0]
            final_csv.append([key, bbp_dict[key], row[1], bbs_dict[key], inc_dict[key], lit_dict[key]])

    with open('internet_data.csv', 'w') as csv_file:
        for row in final_csv:
            csv_file.write(row[0] + ";" + row[1] + ";" + row[2] + ";" + row[3] + ";" + row[4] + ";" + row[5] + "\n")
    # Codigo; preco_bl; preco_dm; velocidade_bl; renda; alfabetizaçao

#definir_codigos()
separar_amostra()