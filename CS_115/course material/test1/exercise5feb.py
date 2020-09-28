# exercise5feb - recursion with lists

'''First, review the following, keeping in
mind that for recursion with a list L we use
these ingredients:
   -- testing whether L == []
   -- the first element, L[0]
   -- recursive call applied to L[1:]
'''

def length(L):
    if L == []: return 0
    else: return 1 + length(L[1:])

def test_length():
    '''test length function, using built-in len.
    Print True for successful test.'''
    L1 = ['a', 'b', 'c']
    print(length(L1) == len(L1))

def reverse(L):
    if L == []:
        return []
    else: return reverse(L[1:]) + [L[0]]

'''Now, here's the exercise.  Remind yourself how the
built-in list-multiplication operator works, e.g.,
find the value of 3*['ha'].  Then implement the following.'''

def repeat(n,x):
    '''make a list of n copies of x,
    assuming n is a non-negative integer.'''
    pass

def test_repeat():
    '''Print True for successful tests of repeat.'''
    print(repeat(3,'ha') == 3*['ha'])
    print(repeat(2, 42) == 2*[42])


'''The following data will be used later for
another exercise.'''

M0 = [3, 5, 19, 2]
M1 = [[2,5], [9, 9, 8], [20]]
M2 = [[2,5], [[3,3],[4,5,6]], 7, M0, M1]

def nestSum(x):
    '''assume L is a list, with elements that
    are either numbers or lists of this kind.'''
    pass


    

