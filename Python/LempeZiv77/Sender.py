####################################################################
#                                                                  #
#            Lempel-Ziv 77 Algorithm with Sliding Window            #
#                             Members                              #
#                    Antonios Kalampogias P18050                   #
#                          +2 other students                       #
#                                                                  #
#                                                   in Python 3.8  #
####################################################################

from Encoder import LZ77Encode
from scipy.stats import entropy
import socket 

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
#Generate a socket on s
s.bind((socket.gethostname(), 34563)) #bind the socket to local IP and random port 
s.listen(10) 
print("Welcome Sender!\nTo use the algorithm please run along with this the Receiver.py..\n")
print("Waiting for someone to connect...\n")

while True:
    clientSocket, address = s.accept()# if client tries to connect, accept it
    print(f"Connection from {address} has been established!")
    UsrFile = input("Please provide the name of the file you wish to encode with LZ77: ")
    if not UsrFile.endswith(".txt"):
        AwaitInput= input("You did not provide the type of file, is it a text file(.txt)??[Yes/No]\n")
        AwaitInput=AwaitInput.lower()
        if AwaitInput=="yes":
            UsrFile+=".txt"
        else:
            print("You have to provide a valid text file to continue with the algorithm..")
                

    with open(UsrFile, 'r') as file:
        UnencreptedMessage = file.read().replace('\n', ' ')

    # Calculate the entropy of the provided text
    def text_entropy(msg):
        pk = [msg.count(chr(i)) for i in range(256)]
        if sum(pk) == 0:
            text_entropy = None
        else:
            text_entropy = entropy(pk, base=2)
        return str(text_entropy)
        
    


    #To use the LoremIpsumTest to check the algorithm change the name from LoremIpsumTest -> UnencreptedMessage
    LoremIpsumTest= "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model EncodedMessage, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)"
    print("You provided the message, {}".format(UnencreptedMessage),"\n")
    EncodedMessage = str(LZ77Encode(UnencreptedMessage))
    print("Entropy of the provided message is:", text_entropy(UnencreptedMessage),"\n")
    print("Message encoded succesfully with values: {}".format(EncodedMessage),"\n")
    print("Entropy of the Encoded message is:", text_entropy(EncodedMessage),"\n")
    

    EncodedMessage=str.encode(EncodedMessage)
    clientSocket.send(EncodedMessage)
    print("Message has been transmitted.\nExiting..")
    clientSocket.close()
    exit()