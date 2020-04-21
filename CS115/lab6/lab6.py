'''
Created on 3/5/2020
@author:   Ming Lin
Pledge:    I pledge my honor that i have abided by the stevens honor code

CS115 - Lab 6
'''
def isOdd(n):
    '''Returns whether or not the integer argument is odd.'''
    if (n % 2 != 0):
        return True
    return False

def numToBinary(n):
    '''Precondition: integer argument is non-negative.
    Returns the string with the binary representation of non-negative integer n.
    If n is 0, the empty string is returned.'''
    if n == 0:
        return ''
    elif isOdd(n):
        return numToBinary(n // 2) + "1"
    return numToBinary(n // 2) + "0"

def binaryToNum(s):
    '''Precondition: s is a string of 0s and 1s.
    Returns the integer corresponding to the binary representation in s.
    Note: the empty string represents 0.'''
    if s == '':
        return 0
    elif int(s[0]) == 1:
        return binaryToNum(s[1:]) + 2 ** len(s[:-1]) 
    return binaryToNum(s[1:]) + 0

def increment(s):
    '''Precondition: s is a string of 8 bits. 
    Returns the binary representation of binaryToNum(s) + 1.''' 
    n = numToBinary(binaryToNum(s) + 1)
    
    if len(n) > 8:
        return n[-8:]
    elif len(n) < 8:
        return '0' * (8 - len(n)) + n
    else:
        return n

def count(s, n):
    '''Precondition: s is an 8-bit string and n >= 0.
    Prints s and its n successors.'''
    if n < 0:
        return ''
    else:
        print(s)
        count(increment(s), n - 1)    
    return ''

def numToTernary(n):
    '''Precondition: integer argument is non-negative.
    Returns the string with the ternary representation of non-negative integer
    n. If n is 0, the empty string is returned.'''
    if n == 0:
        return ''
    elif n % 3 == 0:
        return numToTernary(n // 3) + '0'
    elif n % 3 == 1:
        return numToTernary(n // 3) + '1'
    return numToTernary(n // 3) + '2'

def ternaryToNum(s):
    '''Precondition: s is a string of 0s, 1s, and 2s.
    Returns the integer corresponding to the ternary representation in s.
    Note: the empty string represents 0.'''
    if s == '':
        return 0
    elif int(s[0]) == 2:
        return ternaryToNum(s[1:]) + 2 * 3 ** len(s[:-1])
    elif int(s[0]) == 1: 
        return ternaryToNum(s[1:]) + 3 ** len(s[:-1])
    return ternaryToNum(s[1:]) + 0
