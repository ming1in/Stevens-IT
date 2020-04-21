# Functions we studied before, and alternate
# definitions using lambda.

from cs115 import *

def divides(m):
    '''returns a function that for given k checks whether k divides m'''
    def div(k):
        return m % k == 0
    return div

def divisibles(n,L):
    '''assume L is a list of integers; return a list of the ones divisible by n'''
    if L==[]:
        return []
    elif divides(L[0])(n):
        return [L[0]] + divisibles(n, L[1:])
    else: return divisibles(n, L[1:])

def divisibles_alt(n,L):
    '''the divisibles function, using lambda'''
    return filter(lambda m: m % n == 0, L)
           

def test_divisibles():
    print ( divisibles(3,[3,6,4,9]) == [3,6,9] )
    print ( divisibles_alt(3,[3,6,4,9]) == [3,6,9] )


''' --------- '''


def increment(n):
    '''Returns a function that takes in k and adds it to n.'''
    def add_to(k):
        return k + n
    return add_to

def inc_all(nums, n):
    '''Add n to every number in a given list of numbers.'''
    return map(increment(n), nums)

def inc_all_alt(nums, n):
    '''the inc_all function, using lambda'''
    return map(lambda k: k + n, nums)

def test_inc_all():
    '''Tests for inc_all. Correct tests print True.'''
    print(inc_all([1, 3, 5], 2) == [3, 5, 7])
    print(inc_all([-2, -1, 0, 1, 2], 10) == [8, 9, 10, 11, 12])
    print(inc_all_alt([1, 3, 5], 2) == [3, 5, 7])
    print(inc_all_alt([-2, -1, 0, 1, 2], 10) == [8, 9, 10, 11, 12])







