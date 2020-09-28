# Loop exercises - Nov 2018

#Ming Lin
#I pledge my honor that i have abided by the stevens honor system

#################################################################
# Definitions
#
# "Sorted" means ascending order, with duplicates possible. 
#
# L[0:i] <= N means all elements of L[0:i] are at most N.
# This isn't Python, it's just notation used in comments.
# 
# We will use integer lists in tests, but the code should work
# for other types of elements that can be compared.
#################################################################

#################################################################
# Some functions that may be helpful.
#################################################################

# from cs115 import * 
from collections import Counter

def swap(aList, i, j):
    '''swaps the values of aList[i] and aList[j]'''
    temp = aList[i]       
    aList[i] = aList[j] 
    aList[j] = temp

def isSorted(L):
    '''Whether L is sorted.'''
    for i in range(1,len(L)):
        if L[i-1] > L[i]: return False
    return True

def allLE(L,x):
    '''Whether every element of L is less than or equal to x.'''
    for i in range(len(L)):
        if x < L[i]: return False
    return True

#############################################################
# Step 0: Implement this function.
# Notice that it does not return anything.  It just modifies 
# the contents of a list.
# Some tests are provided.
#############################################################

def insertV1(L, i):
    '''Assume L[0:i] is sorted and 0 < i < len(L).
       Shift elements of the list as needed to swap L[i] into 
       position so that L[0:i+1] is sorted.'''

    for k in range(0, i + 1):
        key = L[k]
        j = k - 1

        while j >= 0 and key < L[j]:
            L[j + 1] = L[j]
            j -= 1
        L[j + 1] = key

    return L

    # Use a single loop that checks and swaps as it goes, like this:
    # [0,2,4,6, 3 ,0,5] [0,2,4, 3, 6,0,5] [0,2, 3 ,4,6,0,5]
    # Invariant: L[0:i+1] sorted except possibly L[j-1] versus L[j]

def testInsert(ins):
    '''Assume ins is a function.  Test whether it solves the insert problem.
    For example, testInsert(insertV1).'''

    L = [0,2,4,6,3,0,5]
    ins(L, 4) # in middle
    assert L == [0,2,3,4,6,0,5]

    L = [1,2,3,4,1] # near start
    ins(L,4)
    assert L == [1,1,2,3,4]

    L = [1,2,3,0] # at start
    ins(L,3)
    assert L == [0,1,2,3]

    L = [1,3,5,5] # at end
    ins(L,3)
    assert L == [1,3,5,5]

    L = [4,3] # short list
    ins(L,1)
    assert L == [3,4]

#############################################################
# Step 1: Implement this function.
# Before coding, make sure you understand the description of
# search(L,i,x) by figuring out how it could be used to solve 
# another problem, namely: whether x is in L.
#############################################################

def search(L, i, x): #find where x should go in L
    '''Assuming L[0:i] is sorted and 0 <= i <= len(L),
       return j such that 0 <= j <= i and L[0:j] <= x < L[j:i].'''
    # Linear search: try successive indexes, starting with 0.
    # Invariant: L[0:j] <= x and j <= i
    # If you want to put the invariant as an assertion, use allLE from above.

    for j in range(i):
        if L[j] > x:
            return j
        if j == i-1:
            return i

def testSearch():
    # in middle
    assert search([0,2,4,6,3,0,5], 3, 3) == 2
    # near start
    assert search([1,2,3,4,1], 3, 1) == 1
    # at start 
    assert search([1,2,3,0], 3, 2) == 2
    # at end
    assert search([1,3,5,5], 3, 6) == 3
    # at end, short list 
    assert search([0], 1, 5) == 1
    # at start, short list
    assert search([5], 1, 2) == 0

#############################################################
# Step 2: Implement the following 
##############################################################
    
def insertV2(L, i):
    '''Assume L[0:i] is sorted and 0 < i < len(L).
       Shift elements of the list as needed to swap L[i] into 
       position so that L[0:i+1] is sorted.'''
    
    val = L[i]
    j = search(L, i, val)

    for k in range(i, -1, -1):
        if L[k] > val:
            swap(L,k,k+1)

    # Do this version as follows: save the value of L[i], use the 
    # search function to find where to insert that value, then 
    # shift to make room, and finally put the value in place.


##################################################
# Step 3: Here are two versions of insertion sort.
# Run the tests to be sure that your insertV1 and
# insertV2 work correctly.
##################################################

def insertSortV1(L):
    '''Sort L in place, using insertV1.'''
    for i in range(1,len(L)):
        assert isSorted(L[0:i])
        insertV1(L,i)
    assert isSorted(L)

def insertSortV2(L):
    '''Same as V1 but using insertV2.'''
    for i in range(1,len(L)):
        assert isSorted(L[0:i])
        insertV2(L,i)
    print(L)
    assert isSorted(L)
    


import random # for testing

def randList(N):
    '''A list of N randomly chosen numbers in the range 0..50.'''
    L = [0]*N
    for i in range(N):
        L[i] = random.randrange(50)
    return L

def testV1():
    testSort(insertSortV1)

def testV2():
    testSort(insertSortV2)

def testSort(sortFun):
    
    def test(L):
        print(L)
        sortFun(L)
        print("sorted?", L)
        assert isSorted(L)

    test([]) # empty
    test([3]) # one element
    test(range(7)) # already sorted
    test(randList(5))
    test(randList(5))
    test(randList(10))
    test(randList(20))



#############################################
# Step 4: Implement letterCounts.
# Hints and sample output are given below.
# Two test files, small_file.txt and dict.txt,
# are provided for testing.
#############################################

def letter(s):
    '''Assuming s is a one-character string, check whether it is 
    a letter and if so return its lower-case form.  Otherwise
    return "!" '''
    if 'a' <= s <= 'z': return s
    elif 'A' <= s <= 'Z': return s.lower()
    else: return "non-letter"

def letterCounts(fname):
    '''Assuming fname is the name of a text file in the current directory,
    find how many occurrences there are of each letter in the alphabet, and
    how many non-letters there are.  For this purpose, count lower and upper
    case letters the same.  Print the list of pairs (n,c) where c is a letter
    and n is the count for that letter.  Omit (n,c) if n==0. For non-letters,
    c should be "non-letter". Print the list in descending order by counts.'''

    letterDict = {
        'a': 0, 'b': 0, 'c': 0, 'd': 0, 'e': 0, 'f': 0, 'g': 0, 'h': 0, 'i': 0,
        'j': 0, 'k': 0, 'l': 0, 'm': 0, 'n': 0, 'o': 0, 'p': 0, 'q': 0, 'r': 0,
        's': 0, 't': 0, 'u': 0, 'v': 0, 'w': 0, 'x': 0, 'y': 0, 'z': 0, 'non-letter': 0
    }
    result = []
    file = open(fname, 'r')

    for line in file:
        for char in line:
            checkedChar = letter(char)
            
            if checkedChar in letterDict:
                letterDict[checkedChar] += 1
    
    for l in letterDict:
        if letterDict[l] != 0:
            result += [(letterDict[l], l)]

    result.sort()
    result.reverse()
    return result
    

# HINTS

# Use a dictionary to keep track of the counts, with lower case letters
# as keys.  Also one special key, "non-letter".

# You can use a for-loop to get the letters in a string, like this: 
# for c in "abc":

# Pairs like (7,'a') can be compared using <, which will compare 
# the first element and if they're equal compare the second.  (Try it!)

# You may use the built-in .sort() operation, and also .reverse() 
# But for more fun, use your own insertSortV2. And write your own
# reverse function using a loop.

# For reading a file and processing it line by line, see demo_file_io.py

# Here is the complete output of letterCounts("small_file.txt")
# [(3, 'non-letter'), (3, 'l'), (2, 't'), (1, 'x'), (1, 's'),
#   (1, 'm'), (1, 'i'), (1, 'f'), (1, 'e'), (1, 'a')]

# Here is part of the output of letterCounts("dict.py"):
# [(17293, 'non-letter'), (1927, 'e'), (1606, 'a'), (1331, 'o'), (1250, 'r')...

