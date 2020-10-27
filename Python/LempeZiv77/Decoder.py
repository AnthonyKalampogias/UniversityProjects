####################################################################
#                                                                  #
#            Lempel-Ziv 77 Algorithm with Sliding Window            #
#                             Members                              #
#                    Antonios Kalampogias P18050                   #
#                          +2 other students                       #
#                                                                  #
#                                                   in Python 3.8  #
####################################################################

def LZ77Decode(y):
    Output=[]
    for pointer in y:
        value = 0 # Each pointer has 3 values, we mainly want the first one that tells if there are characters that we need to check
        if pointer[value] == 0: # if the first value is 0 it means that this character is not previously seen so jsut append it to the output
            Output.append(pointer[value+2])
            continue
        else:
            tmp=[]
            for i in range(len(Output),(len(Output)-pointer[value]),-1):
                # From end of Output until the char that the pointer indicates, [ -> 2 , 2 , 'r' ]
                tmp.append(Output[i-1]) #-1 cause i starts counting from 1 instead of 0
                # tmp array now holds the chars that the pointer says it has
            for t in range(len(tmp) , (len(tmp) - pointer[value+1]) , -1):
                Output.append(tmp[t-1]) #-1 cause t starts counting from 1 instead of 0
            Output.append(pointer[value+2]) #Now that the duplicate string is in add the following char
    DecodedMessage=""
    for item in Output:
        DecodedMessage+=item
    return DecodedMessage
