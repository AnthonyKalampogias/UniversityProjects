#This program finds and prints out the most used letter 
alphabet=["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]
acout=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
txt=the_txt= ""
txt=raw_input("Please give the name of the program: ")
if txt[-4:]!= ".txt":
    txt+=".txt"
f=open(txt,'r+')
s=open(txt,'r+')
for line in f:
   for ch in line:
       for i in range(len(alphabet)):
           if ch == alphabet[i]:
               acout[i]+=1
max=0
maxChar=""
min=acout[0]
minChar=""
for i in range(len(alphabet)):
    if acout[i]!=0:
        print "The letter ",alphabet[i],"has been spotted ",acout[i]
        if acout[i]>max:
            max=acout[i]
            maxChar=alphabet[i]
        if acout[i]<min:
            min=acout[i]
            minChar=alphabet[i]

for line in s:
    for char in line:
        if char == minChar:
            the_txt+=maxChar

        elif char == maxChar:
            the_txt+=minChar
        else:
            the_txt+=char
print "\n", the_txt
f.close()
