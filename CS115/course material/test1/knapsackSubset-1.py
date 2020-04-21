# knapsackSubset - 12 Feb 2020
from cs115 import *

def subsetB(target, L):
    '''Given a non-negative target capacity and list L of positive integers,
    determine whether some elements of L add up to exactly
    the target'''

    if target == 0:
        return True
    elif L == []:
        return False
    elif L[0] > target:
        return subsetB(target, L[1:])
    else: # L[0] <= target
        lose = subsetB(target, L[1:])
        use = subsetB(target - L[0], L[1:])
        return use or lose
 
t0 = subsetB(12, [2, 3, 4, 7, 10, 42]) # should be true
t1 = subsetB(8, [2, 3, 4, 7, 10, 42]) # should be false

def testSubsetB():
    assert t0 and not t1
    assert subsetB(0,[2,5,9]) 

def subset(target,L):
    '''Assuming L is a list of positive integers and target
    is a non-negative integer, return the largest sum of elements of L
    that does not exceed target.'''
    if L == []:
        return 0
    elif L[0] > target:
        return subset(target, L[1:])
    else:
        use = L[0] + subset(target-L[0], L[1:])
        lose = subset(target, L[1:])
        return max(use,lose)

assert subset(5, []) == 0
assert subset(5, [5,2,5]) == 5
assert subset(5, [2,2,7,1]) == 5
assert subset(5, [7,3,3,5]) == 5
assert subset(5, [1,1,2]) == 4














