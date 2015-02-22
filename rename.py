#!/usr/bin/python

"""rename.py: Renames the output files of convert.sh"""

import os

with open("sdfs.sdf") as f:
    content = f.readlines()

j = 1
for i in range(1, len(content)):
    if content[i - 1] == "> <Key>\n":
        print(j)
        oldFileName = "out" + str(j) + ".png"
        newFileName = content[i][:-1] + ".png"
        os.rename(oldFileName, newFileName)
        j = j + 1
