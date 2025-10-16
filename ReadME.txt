# File Encryption README

## Overview

This project implements a simple file encryption and decryption system using the methods provided in the project file. The encryption process includes features such as binary conversion, XOR operations, and an S-box substitution, as stated to be used.\

This project allowed us to figure out how to read and write files using BufferedReader/FileReader, how to convert text to binary, how to create algorithims that mix up the binary in order to create a pattern only being able to be deciphered by those who know the algorithm, and reverses the algorithim process in order to retain the original text.\

In the code, there are many different supporting functions in order to encrypt and decrypt text, as shown below:

binaryToDecimal converts binary to string using Integer.parseInt;

readEncryptedBlocksFromFile reads each line from a file, trims it, and returns it to a list of lines if its not empty;\

fileTo64BitBlocks reads the file line by line, converts each character to a binary representation, pads the binary to ensure its 8 bits long for the f function, then appends the binary to a buffer until it reaches 64 bits;

binaryToText splits the binary into 8 bit chunks, converts each byte into the corresponding ascii character (only in the letter range) and appends the result to a string;

stringTo56Bit converts a string into a 56 bit binary string using hash codes, and pads the string if it isnt 56 bits long;

splitStringInHalf splits a string in half;\

splitStackFunction splits a string in four equal parts and returns them in reverse order as a stack of strings;

shiftIt sfits input string (moves first character to end);\

xorit does a exclusive or operation between the two binary strings x and y and returns the result;\

substitutionS splits the binary string x in half, converts each half to a decimal index, and uses the index to look for a value in the 2D array S;\

permuteIt rearranges the charcters of input x based off the perumutation pattern provided;\

functionF does what it is described to do in the project file;\

splitStringFunction splits a string into four different parts and returns as an array of strings;\

encryptBlock carries out the encryption method provided to us (takes the 64 bit block and carries out the encryption algorithm);\

decryptBlock reverses the encryption method to turn the encrypted binary back into text;\

keyScheduleTransform generates 10 subkeys from the 56 bit array (does an algorithm on the 56 bit);\

encryption takes the binary input string and key as an input, generates the subkeys, and uses the subkeys to actually encrypt the input string using encryptBlock;\

decryption does the reverse;\

I (Teyshaun) was resposible for this readme file, structuring the zip file, function f, substitution, permutation, xorit and shiftit.
Nate Cummings was resposible for everything else.


