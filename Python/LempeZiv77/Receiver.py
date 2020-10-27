####################################################################
#                                                                  #
#            Lempel-Ziv 77 Algorithm with Sliding Window            #
#                             Members                              #
#                    Antonios Kalampogias P18050                   #
#                          +2 other students                       #
#                                                                  #
#                                                   in Python 3.8  #
####################################################################
from Decoder import LZ77Decode
import socket, ast


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.connect((socket.gethostname(), 34563))
while True:
    n=1024
    receivedMSG = s.recv(n)
    receivedMSG=receivedMSG.decode("utf-8")
    print("You received the message:\n"+receivedMSG)
    receivedMSG = ast.literal_eval(receivedMSG) # To send the pointers it had to be a string so we convert it back to a list
    receivedMSG = LZ77Decode(receivedMSG)

    print("Decoded the message succesfully:\n",receivedMSG)
    print("Message has been received.\nExiting..")
    s.close()
    exit()