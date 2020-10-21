#This is a little minesweeper that i made (Not yet finished)
import random
def isInBounds(board,x,y):
    return 0 <= x < len(board) and 0 <= y < len(board[0])

def addValue(board,x,y,value):
    if isInBounds(board,x,y):
        board[x][y] = value

def addValuesAround(board,x,y,value):
    addValue(board,x-1,y,value)
    addValue(board,x,y-1,value)
    addValue(board,x+1,y,value)
    addValue(board,x,y+1,value)

size=input("Please provide the size of the minesweeper: ")
if size%2==1:
    size+=1

board = [[" "]*size for i in range(size)] #Makes EMPTY boar

value="  1 "
bombs = input("Please provide the amount of bombs you want in the game: ")
all_cells = ["nuke"] * bombs + ["    "] * (size - bombs) # adds randomly bombs
random.shuffle(all_cells) # adds randomly bombs
board = [all_cells[i:i+10] for i in range(0, size, 10)] # adds randomly bombs
count=0
for i in range(len(board)):
    for j in range(len(board[i])):
       if board[i][j] == 'nuke':
           addValuesAround(board,i,j,"  1 ")

for item in board:
    print item
