####################################################################
#                                                                  #
#            Lempel-Ziv 77 Algorithm with Sliding Window            #
#                             Members                              #
#                    Antonios Kalampogias P18050                   #
#                          +2 other students                       #
#                                                                  #
#                                                   in Python 3.8  #
####################################################################


def LZ77Encode(x):
    # First we separate each character of the string
    C = [i for i in x]

    LAB = []            # Look Ahead Buffer
    SB = [0,0,0,0]      # Search Buffer
    # Add the first 4 values in the SB. later we will add the first letter to be able in order to read the SB in reverse
    
    OP = [[0,0,C[0]]]       # Output values
    # On start SB is empty, so the first character to be added will be alone in the 1st value

    SB.append(C[0])         # Place at least one value in SB to be able to use the algorithm
    C.pop(0)                # Also Pop that character
    
    # Initialise, add values to lookahead buffer (aka LAB)
    for i in range(0,5):
        LAB.append(C[0])
        C.pop(0)
        #This might look like the C[0] is duplicated in both LAB,SB but check lines 26-27 above
    
    # while there are more characters to be checked
    while not len(C) <= 0:
        InserterList = []               
        # Insert List will hold temporary the characters that create a similar string from SB TO LAB
        #LABi, SBi will be the indexes for the 2 List, need to be reset after each use
        LABi=0
        SBi = 4
                
        ValidsMetr=1
        #start counting how many characters are valid for this substring
         # Remember for the Search Buffer in reverse 
        if LAB[LABi] in SB:
             # Check if the indexed look ahead character is in the search buffer
            
            #If there is one index in SB it is easy, but what if there are multiple? 
            #We simply find every possible string that can be generated and sort them by their length in an array 
                # and then take the biggest one as our result 
            counter = SB.count(LAB[LABi]) # count how many chars are in the SB
            if counter == 1: # if just one find its index
                SBi= SB.index(LAB[LABi])
                    
                InserterList.append(LAB[LABi])          

                # Do the rest of the chars in the search buffer that are after the valid character
                for x in range( SBi + 1 , len(SB) , 1 ): 
                    if SB[x] == LAB[LABi+ValidsMetr]: # To save some time and reduntant code we can use the valid characters found as an adder to LAB to continue with the alogrithm
                        InserterList.append(LAB[LABi+ValidsMetr])
                        ValidsMetr+=1
                    else:
                        break
                        

            else:
                SBi=1 # temporarily use SBi 
                tempArray=[] # we want to store the "indexes" of the chars that are valid here
                for char in range(len(SB)):
                    if SB[char] == LAB[LABi]:
                        tempArray.append(char) # note that here we want the location and NOT the value
                # We have the indexes of the chars, now lets generate the substrings
                for i in range(len(tempArray)):
                    InserterList.append(str(tempArray[i]) + " " +SB[tempArray[i]]) 
                    # Append InsertList with the found char to later edit with more valid chars
                        # and its index in the SB for easy manipulation later
                    while True:
                        if tempArray[i]+SBi>=5:
                            SBi-=1 #In case the list goes out of range before error modify loop to stop it from breaking
                        if SB[tempArray[i]+SBi] == LAB[LABi+ValidsMetr]:
                            InserterList[i]+= LAB[LABi+ValidsMetr]
                            ValidsMetr+=1
                            SBi+=1
                        else:
                            # Reset
                            ValidsMetr=1
                            SBi=1
                            break
                        if (tempArray[i]+SBi)==len(SB): #if it is the last char in SB, check in LAB or stop the Loop
                            if LAB[LABi] == LAB[LABi+ValidsMetr]:
                                InserterList[i]+= LAB[LABi+ValidsMetr]
                                ValidsMetr+=1
                                for LABz in range(LABi,int(len(LAB)/2)): # from the current index to the middle of the list because after that we don't have other chars to " compare "
                                   if LAB[LABz] == LAB[LABi+ValidsMetr]:
                                        InserterList[i]+= LAB[LABi+ValidsMetr]
                                        ValidsMetr+=1
                        
                            
                            #Outside inner if
                            SBi-=1 # If the string doesn't continue in LAB save the loop from breaking
                            LABi=0 # Reset LABi in the end to let the rest of the program use it
                            if ValidsMetr == 5 : 
                            # if ValidsMetr is 5 it will cause an out of range error because our lists last indexes are 4
                                ValidsMetr-=1 
                                # So when the flag is true we will later give back ValidsMetr its original value  
 
                
                # We now have the substrings, lets sort them by length and see which one is best
                InserterList = sorted(InserterList, key=len)
                BestSubStr= InserterList[-1] # BestSubStr now has the best string to use,
                                                # But it has the SB index at the start 

                BestSubStr = BestSubStr.split(' ') # BestSubStr is now spit on [0]= index [1]=the str
                InserterList.clear
                InserterList= [i for i in BestSubStr[1]]
                SBi = int(BestSubStr[0])




            if len(InserterList)>=len(LAB): # If the following char is not in LAB take it from C 
                NextPointer = len(InserterList) - len(LAB)
                OP.append([5-SBi,len(InserterList),C[NextPointer]])
            else:
                OP.append([5-SBi,len(InserterList),LAB[LABi+len(InserterList)]])
            for t in range(0,len(InserterList)+1,1):
                SB.pop(0)
                SB.append(LAB[0])
                LAB.pop(0)
                LAB.append(C[0])
                C.pop(0)
                if len(C)<=0: # When C comes to 0 values we save it from throwing an error by breaking this loop
                    C_is_empty=True
                    if t<len(InserterList):
                        while t!= -1 :
                            SB.pop(0)
                            SB.append(LAB[0])
                            LAB.pop(0)
                            t-=1
                    break
            
            
        else:
            OP.append([0,0,LAB[0]])
            SB.pop(0)
            SB.append(LAB[0])
            LAB.pop(0)
            LAB.append(C[0])
            C.pop(0)
            if len(C)<=0:
                C_is_empty=True
                break


    if C_is_empty and len(LAB) != 0: # if there are still characters that are not checked lets do those 
        EmptyLAB=False
        while len(LAB) != 0:         # same proccess as before just without appending and popping from C
            InserterList = []               
            LABi=0
            SBi = 4
            if LAB[LABi] in SB and len(LAB) >= 2: # if just one char left it can't be a bigger string than 1 char so we skip the same proccess
                counter = SB.count(LAB[LABi]) # count how many chars are in the SB
                if counter == 1:
                    SBi= SB.index(LAB[LABi])
                        
                    InserterList.append(LAB[LABi])          

                    # 5 is for the size of SB to indicate how many spots to execute the comparison on
                    # Do the rest of the chars in the search buffer that are after the valid character
                    for x in range( SBi + 1 , 5 , 1 ): 
                        if SB[x] == LAB[LABi+ValidsMetr]: # To save some time and reduntant code we can use the valid characters found as an adder to LAB to continue with the alogrithm
                            InserterList.append(LAB[LABi+ValidsMetr])
                            if (ValidsMetr+1) == len(LAB):
                                EmptyLAB=True
                                break
                            else:
                                ValidsMetr+=1
                        else:
                            break
                            

                else:
                    SBi=1 # temporarily use SBi 
                    tempArray=[]
                    for char in range(len(SB)):
                        if SB[char] == LAB[LABi]:
                            tempArray.append(char) # note that here we want the location and NOT the value
                    # We have the indexes of the chars, now lets generate the substrings
                    for i in range(len(tempArray)):
                        InserterList.append(str(tempArray[i]) + " " +SB[tempArray[i]]) 
                        # Append InsertList with the found char to later edit with more valid chars
                            # and its index in the SB for easy manipulation later
                        while True:
                            if (tempArray[i]+SBi)==len(SB): #if it is the last char in SB save the loop from breaking
                                SBi-=1
                            if SB[tempArray[i]+SBi] == LAB[LABi+ValidsMetr]:
                                InserterList[i]+= LAB[LABi+ValidsMetr]
                                ValidsMetr+=1
                                SBi+=1
                            else:
                                # Reset
                                ValidsMetr=1
                                SBi=1
                                break
                    # We now have the substrings, lets sort them by length and see which one is best

                    InserterList = sorted(InserterList, key=len)
                    BestSubStr= InserterList[-1] # BestSubStr now has the best string to use,
                                                    # But it has the SB index at the start 

                    BestSubStr = BestSubStr.split(' ') # BestSubStr is now spit on [0]= index [1]=the str
                    InserterList.clear
                    InserterList= [i for i in BestSubStr[1]]
                    SBi = int(BestSubStr[0])

                if EmptyLAB:
                    OP.append([5-SBi,len(InserterList),''])
                else:
                    OP.append([5-SBi,len(InserterList),LAB[LABi+len(InserterList)]])
                for t in range(0,len(InserterList)+1,1):
                    SB.pop(0)
                    SB.append(LAB[0])
                    LAB.pop(0)
                    if len(LAB)==0:
                        break

            else:
                OP.append([0,0,LAB[0]])
                SB.pop(0)
                SB.append(LAB[0])
                LAB.pop(0)

    return OP   

