'''
Created on _______________________
@author:   _______________________
Pledge:    _______________________

CS115 - Hw 6
'''
# Number of bits for data in the run-length encoding format.
# The assignment refers to this as k.
COMPRESSED_BLOCK_SIZE = 5

# Number of bits for data in the original format.
MAX_RUN_LENGTH = 2 ** COMPRESSED_BLOCK_SIZE - 1

# Do not change the variables above.
# Write your functions here. You may use those variables in your code.
def compress(S):
    """This function returns the compressX plus 5 zeros if n starts with 1"""
    if S[0] == '1':
        return '00000' + compressX(S, '1')
    return compressX(S, '0')

def uncompress(C):
    """This function checks if the compressed string equals the string we are going to uncompress"""
    return uncompressX(C,'0')

def compression(S):
    """This function returns the ratio of the compressed size to the original size for string s"""
    return (1.0 * len(compress(S)) / len(S))
    
def isOdd(n):
    """Returns whether or not the integer argument is odd."""
    if n % 2 == 0:
        return False
    return True 

def numToBinary(n):
    """Precondition: integer argument is non-negative. Returns the string with the binary representation of non-negative integer n.
    If n is 0, the empty string is returned."""
    if n == 0:
        return ''
    elif isOdd(n):
        return numToBinary(n // 2) + '1'
    return numToBinary(n // 2) + '0'

def binaryToNum(s):
    """Precondition: s is a string of 0s and 1s. Returns the integer corresponding to the binary representation in s.
    Note: the empty string represents 0."""
    if s == '':
        return 0
    elif int(s[0]) == 1:
        return binaryToNum(s[1:]) + 2 ** len(s[:-1]) 
    return binaryToNum(s[1:]) + 0

def prefixLen(n, b): 
    """Precondition: s is a nonempty string
    Returns the length of the prefix, that is, the number of characters that are
    the same at the start of a string."""
    if n == '':
        return 0
    if b != n[0]:
        return 0
    if b == n[0]:
        return 1 + prefixLen(n[1:], b)

def numToBinaryPadded(n):
    """Precondition: 0 <= n * 2 COMPRESSED_BLOCK_SIZE.
    Returns a binary representation of n padded with leading 0's to have length COMPRESSED_BLOCK_SIZE."""
    size = COMPRESSED_BLOCK_SIZE - len(numToBinary(n))
    return '0' * size + str(numToBinary(n))

def compressX(s, b):
    """This function takes a binary string of length 64 as input and returns another binary string of run-length encoding as output"""
    if s == '':
        return ''
    if prefixLen(s, b) > 31:
        return numToBinary(31) + compressX(s[31:], str(1-int(b)))
    return numToBinaryPadded(prefixLen(s, b)) + compressX(s[prefixLen(s, b):], str(1-int(b)))

def uncompressX(s,b): 
    """This function "inverts" or "undoes" the compressing in my compress function"""
    if s == '':
        return ''
    return binaryToNum(s[:COMPRESSED_BLOCK_SIZE]) * b + uncompressX(s[COMPRESSED_BLOCK_SIZE:], str(1 - int(b)))
