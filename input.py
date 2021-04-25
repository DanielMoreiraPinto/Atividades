import numpy as np
import random
import csv
import pandas as pd
import string 

numbers = []
for i in range(930000):
    numbers.append(random.randint(0, 9999999))
numbers = np.array(numbers)
pd.DataFrame(numbers).to_csv("int_intarr.csv", header=False)

csvFields = ["key", "val"]
with open('string_double.csv', 'w', newline='') as write_obj:
    dict_writer = csv.DictWriter(write_obj, fieldnames=csvFields)
    dict_writer.writeheader()
    for i in range(30000):
        doub = round(random.uniform(0.0, 100.0), 3)
        stri = ''.join(random.choice(string.ascii_lowercase) for j in range(20))
        row = {"key":stri, "val":doub}
        dict_writer.writerow(row)

csvFields = ["key", "val"]
with open('double_string.csv', 'w', newline='') as write_obj:
    dict_writer = csv.DictWriter(write_obj, fieldnames=csvFields)
    dict_writer.writeheader()
    for i in range(30000):
        doub = round(random.uniform(0.0, 100.0), 3)
        stri = ''.join(random.choice(string.ascii_lowercase) for j in range(20))
        row = {"key":doub, "val":stri}
        dict_writer.writerow(row)
