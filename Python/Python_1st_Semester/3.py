#This program is to remove comments out of a python program 
txt= raw_input("Please give the name of the program: ")
output_txt= raw_input("Please give the name of the output: ")
if txt[-3:]!= ".py":
    txt+=".py"
if output_txt[-3:]!= ".py":
    output_txt+=".py"
with open(txt, 'r') as f:
    lines = f.readlines()
with open(output_txt, 'w') as f:
    for line in lines:
        if line[0:2] == "#!":
            f.writelines(line)
        elif not line.strip():
            f.writelines(line)
        else:
            line = line.split('#')
            stripped_string = line[0].rstrip()
            if stripped_string:
                f.writelines(stripped_string)
                f.writelines('\n')
