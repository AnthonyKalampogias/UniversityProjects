# Lempel Ziv 77 Algorithm

## by Antonios Kalampogias in python 3.8

​	This project was in colaboration with 2 other students

## How to use

It's pretty simple, all you have to do is run the file *Sender.py* like so

```shell
python3.8 Sender.py
```

It will now wait for a connection that we will explain below,

Then run the *Receiver.py* by

```shell
python3.8 Receiver.py
```

Checking back at the terminal we have *Sender.py* running it will now ask for a text file to send to the *Receiver*

This project uses the LZ77 encoding algorithm to

1. Import a text file from the user
2. Encode the text file using the LZ77 algorithm
3. Calculate the entropy of the original and the encoded text
4. Create a socket connection on the users pc
5. Receive on another socket the encrypted text and decode it

In the folder you will see 4 files

- Encoder
- Decoder
- Sender
- Receiver



### Sender.py 

​	will be the main file for our encryption process

Here the code generates a socket connection on the users pc and upon connection from another socket (we will later discuss about this) it calls the encode function from the *Encoder.py* file to encode the message and then transmit the encoded message to the inbound connection

### Encoder.py

Here you will find the code that is used to encode the provided text,

There are many detailed comments in the code but I higly suggest to check out the following link by an [MIT math class](http://www-math.mit.edu/~shor/PAM/lempel_ziv_notes.pdf) 

### Receiver.py

This is the second part of the project that upon startup will try to connect to the port *Sender.py* is on, if this statement is true *Sender* will begin the execution of the encode function and will send to the Receiver the encoded message

### Decoder.py

This files main function *LZ77Decode* is called by *Receiver.py* when it receives a file so that it can decode it and reveal the message