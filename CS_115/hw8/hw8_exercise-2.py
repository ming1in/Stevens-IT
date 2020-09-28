# runLenPlus - exercise for fancy version of run length encoding
# D. Naumann, Oct 2012, rev Oct 2018 

import math # we need math.log and math.ceil (the ceiling function)

# GOAL: compress/uncompress binary strings using adaptive run-length encoding.

# Builds on a solution for the part of Homework 6, which I refer to 
# as Run Length Encoding (RLE).  If you read carefully and make sure you
# understand each part before going on, you probably won't find this 
# too difficult.  

# Note: we will work with binary strings of characters like '0110', for ease 
# of understanding and testing.  A real-world encoder for a format like 
# zip-files or mp3 files would not use characters!  It would use binary data, 
# where each bit really is one bit, rather than a character code which takes 
# 8 or 16 bits (ASCII or UniCode encoding, respectively).

# The goal is to define functions compressA and uncompressA,
# using run length encoding in an 'adaptive' way.  The possible
# run lengths are based on analysis of the string to be compressed;
# this determines how many bits are used to represent each run length.
# That number must be included in the compressed data, so it can
# be used when un-compressing.

# The compressed format, which I call RLE+, looks like this:
#       bbbwxwxwxwx...
# - bbb is three bits, which represent a number runBits, 0 < runBits < 8
# - each w is a string of runBits bits and encodes the length of a run of 0s
# - each x is a string of runBits bits and encodes the length of a run of 1s
# Note that the wxwxw... part is the same as what is used in Homework 6,
# except in that homework runBits is always 5.

# For example, 010 10 10 01 10  (but without spaces)
#              bbb w  x  w  x
# encodes the string 001101, using runBits=2.

# The compressA function should first scan the input string to determine
# how long is the average run length, avgLen, and then choose a sensible
# number of bits to use for encoding run lengths.  Here is how we will
# choose that number: let runBits be one more than the log of avgLen, 
# but at most 7.  This means we compress using runs of length less 
# than 2**runBits.
# More precisely: 
# let n = math.ceil(math.log(m,2)) + 1 and runBits = min(n,7).

# If runBits is 1, compression is pointless, but we'll do it anyway.

########
# Step 0
# Before working on adaptive RLE, have a look at the code below
# which provides basic RLE.  It's like a solution for Homework 6,
# but the main functions are more general.
########

# Number of bits for data in the Homework 6 RLE format.
# The hw6 assignment refers to this as k, and I refer to it as runBits.
COMPRESSED_BLOCK_SIZE = 5 

MAX_RUN_LENGTH = 2 ** COMPRESSED_BLOCK_SIZE - 1 


def numToBinary(n):
    '''String with the binary representation of non-negative integer n,
        with no leading zeros; but empty string if n is 0.'''
    if n==0:
        return ''
    elif n % 2 != 0:
        return numToBinary(n//2) + '1'
    else:
        return numToBinary(n//2) + '0'

def binaryToNum(s):
    '''Assuming s is a string of 0s and 1s, return the number that s is a
        binary representation of.  For this purpose, the empty string represents 0.'''
    if s == '':
        return 0
    else:
        return 2 * binaryToNum(s[:-1]) + int(s[-1])

def numToBinPadded(n, runBits):
    '''Assume 0 <= n < 2**runBits.  Return binary rep of n
        padded with leading 0s to have length runBits.
        For example, numToBinPadded(3, 5) is '00011'. '''
    s = numToBinary(n)
    pad = '0'*(runBits - len(s))
    return pad + s

def prefixLen(s):
    '''Assume s is a non-empty string.  Return n such that s begins with n
        copies of s[0].  For example, prefixLen('010') = 1, prefixLen('1110') = 3.'''
    if len(s) == 1: return 1
    elif s[1]==s[0]: return 1 + prefixLen(s[1:])
    else: return 1

def compressX(s, b, runBits):
    '''Assume s is string and b is the number 0 or 1 (not character!).  
        Return RLE of s starting with the num of b's at start of s (which 
        may be none).  Each run length is encoded using exactly runBits bits.'''
    if s=='':
        return ''
    elif s[0] != chr(b + ord('0')):  # doesn't begin with b?
        return numToBinPadded(0, runBits) + compressX(s, 1-b, runBits)
    else:                            # does begin with b
        maxRunLen = 2**runBits - 1
        plen = min(prefixLen(s),  # number of bs at start of s; at least 1, at most maxRunLen
                   maxRunLen)
        return numToBinPadded(plen, runBits) + compressX(s[plen:], 1-b, runBits)

def compress(s):
    '''Return the RLE of s using COMPRESSED_BLOCK_SIZE-bit run lengths.'''
    return compressX(s, 0, COMPRESSED_BLOCK_SIZE)

def uncompressX(s, b, runBits):
    '''Assume s is in wxwx... or xwxw... format, depending on whether
    b is 0 or 1. Return the string that s encodes.'''
    if s=='': return ''
    else:
        n = binaryToNum(s[:runBits])
        prefix = n * chr(b + ord('0'))
        return prefix + uncompressX(s[runBits:], 1-b, runBits)

def uncompress(s):
    '''Return the string that s encodes, assuming RLE with COMPRESSED_BLOCK_SIZE-bit run lengths.'''
    return uncompressX(s, 0, COMPRESSED_BLOCK_SIZE)

########
# Step 1
# Complete the following definition.
# The helper lORL should be recursive, and use the accumulator technique.
########

L0 = '1110001111001010' # runBits will be 2
L1 = '1'                # runBits will be 1
L2 = '111111110101010101010101010101010101010101' # runBits 2
L3 = '110011001100'     # runBits 2
L4 = '000111000111'     # runBits 3 (is this ideal?)
L5 = '0000111100001111' # runBits 3
# The following three are from test_hw6.py and have runBits 6
L6 = '0' * MAX_RUN_LENGTH + '1' * MAX_RUN_LENGTH + '0' * (64 - 2 * MAX_RUN_LENGTH)
L7 = '0' * (MAX_RUN_LENGTH + 1) + '1' * (MAX_RUN_LENGTH + 1) + '0' * (64 - 2 * MAX_RUN_LENGTH - 2)
L8 = '1' * MAX_RUN_LENGTH + '0' * MAX_RUN_LENGTH + '1' * (64 - 2 * MAX_RUN_LENGTH)


def listOfRunLengths(s):
    '''Assume s is a nonempty string.  Return a list of the lengths of its runs.
        For the list L0 above, listOfRunLengths(L0) is [3,3,4,2,1,1,1,1].'''

    def lORL(s, result, curCount, curVal):
        '''Accumulate, in result, the list of run lengths of s, using
            curCount as count of the current run; the current run 
            is a sequence of curVals.'''

        return None # TO DO (my solution is about 6 lines)
            
    return lORL(s[1:], [], 1, s[0]) # don't change this line
    # first run begins with s[0] and has length 1 so far


########
# Step 2
# Complete the following definition.
########

def findRunBits(s):
    '''Returns the number of bits to use for compressing string s.
        Assume s is a nonempty string.
        Specifically, returns n where n is the log of the average
        run length, but at most 7, as described at the beginning of this file.
        The maximum n is 7 because only three bits are available
        for it (the bbb in the compressed format).'''

    return None. # TO DO 
    # My solution is four lines of code, no recursion, but using the
    # built-in sum, min, and len functions as well as log and ceiling.

########
# Step 3 
# Here are compressA and uncompressA using the preceding functions.
# Test them.
########

def compressA(s):
    '''Returns compressed form of s using RLE+ format.'''
    runBits = findRunBits(s) # expect 0 < runBits < 8
    bbb = numToBinPadded(runBits, 3)
    return bbb + compressX(s, 0, runBits)

def uncompressA(s):
    '''Assume s is a string of 0s and 1s in the RLE+ format above.
    Return the string that it encodes.'''
    bbb = s[0:3]                # get runBits
    runBits = binaryToNum(bbb)  # convert it to an integer
    wxs = s[3:]                 # the encoded data 
    return uncompressX(wxs, 0, runBits)



        


