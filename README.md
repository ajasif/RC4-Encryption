# RC4-Encryption
Implements RC4 Encryption Algorithm

RC4 generates a pseudorandom stream of bits (a keystream). As with any stream cipher, these can be used for encryption 
by comibining it with the plaintext using bit-wise exclusive-or; decryption is performed the same way. To generate the
keystream, the cipher makes use of secret internal state which consists of two parts:
  1. A permutation of all 256 possible bytes 
  2. Two 8-bit index-pointers 

The permutation is intitialized with a variable length key, typically between 40 and 2048 bits, using the key-scheduling
algorithm (KSA). once this has been completed, the stream of bits is generated using the pseudo-random gneration algorithm (PRGA).

Key-scheduling algorithm

The key-scheduling algorithm is used to initalize the permutaiton in the array "S". "keylength" is defined as the number of byptes
in the key and can be in the range 1<= keylength <= 256, typically between 5-16, corresponding to a key length of 40-128 bits. First
, the array "S" is initialized to th eidentity permutation. S is then processed for 256 iterations in a similar way to the main PRGA, 
but also mixes in bytes of hte key at the same time. 

for i fro m0 to 255
  S[i] := i
endfor
j := 0
for i from 0 to 255
  j := (j + S[i] + key[i mod keylength]) mod 256
  swap values of S[i] and S[j]
endfor

For as many iterations as are needed, the PRGA modifies the state and outputs a byte of the keystream. In each iteraiton, the PRGA
increments i, looks up the ith element of S, S[i], and adds that to j, exhchanges the values of S[i] and S[j], and then uses the
sum S[i] +S[j] (modulo 256) as an index to fetch a third element os s, (the keystream value K below) whic is bitwise exclusive OR'ed 
with the next byte of the message to produce the next byte of either ciphertext or plaintext. Each element of S is swapped with another
element at least once every 256 iterations. 

i := 0
j := 0
while GeneratingOutput:
  i := (i + 1) mod 256
  j := (j + S[i]) mod 256
  swap values of S[i] and S[j]
  K := S[(S[i] + S[j]) mod 256]
  output K
endwhile
