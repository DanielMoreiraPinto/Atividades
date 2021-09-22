import psutil as ps
import argparse as arg
import datetime

"""
Programa escrito por Daniel Moreira Pinto
Disciplina de Sistemas Operacionais 1 (BICT)
Versão final em 25/06/2021

OBS: Este programa utiliza o pacote psutil, que pode não apresentar algumas funções
dependendo do sistema operacional no qual é utilizado ou a versão do pacote instalado.
Documentação do pacote disponível em https://psutil.readthedocs.io/en/latest/
"""

# Retorna o número real de núcleos na CPU
def core_count():
    try:
        num_cores = ps.cpu_count(logical=False)
        return "Número de núcleos na CPU: {}".format(num_cores)
    except:
        print("Ocorreu um erro ao tentar recuperar os núcleos da CPU")


def memory_usage():
    try:
        svmem = ps.virtual_memory()
        used_mb = round(svmem.used / 1048576, 2)
        avaiagle_mb = round(svmem.available / 1048576, 2)
        return "Utilizando {} MB de RAM, {} MB livre".format(used_mb, avaiagle_mb)
    except:
        print("Ocorreu um erro ao tentar recuperar o status da memória RAM")

# Retorna a carga da bateria e informa se está conectada
def battery_status():
    try:
        sbat = ps.sensors_battery()
        if sbat:
            pct_left = round(sbat.percent, 2)
            if sbat.power_plugged and pct_left == 100:
                status = "conectada na fonte"
            elif sbat.power_plugged and pct_left != 100:
                status = "carregando"
            else:
                status = "{} horas restantes".format(round(sbat.secsleft / 3600, 2))
            return "Bateria em {}%, {}".format(pct_left, status)
        return "Bateria não detectada"
    except:
        print("Ocorreu um erro ao tentar recuperar o status da bateria")

# Retorna uma lista com todos os processos em execução e seus estados
def list_processes():
    try:
        iterator = ps.process_iter()
        output = "Processos em execução:\n"
        process_dict_fields = ['pid', 'name', 'status']
        p, i, s, r = 0, 0, 0, 0
        for process in iterator:
            process_dict = process.as_dict(process_dict_fields)
            pid = process_dict['pid']
            name = process_dict['name']
            status = process_dict['status']
            
            output += "{}-> ID: {}, Nome: {}, Estado: {}\n".format(p, pid, name, status)
            output += "----------------------------------------------\n"

            p += 1
            if process_dict['status'] == "idle":
                i += 1
            elif process_dict['status'] == "sleeping":
                s += 1
            else:
                r += 1
        
        output += "{} processos criados, {} em execução, {} parados, {} suspensos".format(p, r, i, s)
        return output
    except:
        print("Ocorreu um erro ao tentar recuperar os processos em execução")

# Recebe a identificação de um processo e retorna seus detalhes
def process_info(process_id):
    try:
        process = ps.Process(process_id)
        process_dict = process.as_dict(['pid', 'name', 'username', 'ppid', 'num_threads', 'create_time', 'status'])
        with process.oneshot():
            pid = process_dict['pid']
            name = process_dict['name']
            username = process_dict['username']
            ppid = process_dict['ppid']
            nthreads = process_dict['num_threads']
            createdin = datetime.datetime.fromtimestamp(process_dict['create_time']).strftime("%Y-%m-%d %H:%M:%S")
            status = process_dict['status']
        return "Processo {} -> Nome: {}, Usuário: {}, Pai: {}, Threads geradas: {}\nProcesso criado em {}, Status: {}\n".format(pid, name, username, ppid, nthreads, createdin, status)
    except (ps.Error, ValueError):
        return "O processo não pode ser acessado"

# Função que define as opções de execução do programa
def main():
    prog_name = "Monitor de Sistema do Daniel"
    prog_description = "Programa de monitoramento de sistema do Daniel Moreira Pinto para o Desafio 1 da disciplina de Sistemas Operacionais 1"
    parser_usage = "Formato de execução: so_1_desafio_1.py [-h] [-a] [-c] [-m] [-b] [-p] [-pi ID_DO_PROCESSO]"
    

    parser = arg.ArgumentParser(prog=prog_name, description=prog_description, usage=parser_usage)

    parser.add_argument('-a', '--all', action='store_true', help='Detalhes da CPU, memória e bateria')
    parser.add_argument('-c', '--cores', action='store_true', help='Detalhes dos núcleos da CPU')
    parser.add_argument('-m', '--memory', action='store_true', help='Detalhes da memória RAM')
    parser.add_argument('-b', '--battery', action='store_true', help='Detalhes da bateria')
    parser.add_argument('-p', '--processes', action='store_true', help='Processos ativos')
    parser.add_argument('-pi', '--process-info', help='Detalhes de um processo', metavar='ID_DO_PROCESSO')
    
    args_dict = vars(parser.parse_args())
    if not any(args_dict.values()):
        print("Nenhum argumento fornecido, execute so_1_desafio_1.py -h para ver opções")

    if args_dict['all'] or args_dict['cores']:
        print(core_count())
    if args_dict['all'] or args_dict['memory']:
        print(memory_usage())
    if args_dict['all'] or args_dict['battery']:
        print(battery_status())
    if args_dict['processes']:
        print(list_processes())
    if args_dict['process_info']:
        print(process_info(int(args_dict['process_info'])))

if __name__ == "__main__":
    main()