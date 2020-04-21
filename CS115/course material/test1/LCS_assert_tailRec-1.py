# LCS, assert statement, and tail recursion 
from cs115 import *

def LCS(S1, S2):
    """Length of longest common subsequence of two lists."""
    if S1 == "" or S2 == "": return 0
    elif S1[0] == S2[0]:  # do the first symbols match?
        return 1 + LCS(S1[1:], S2[1:])
    else:
        return max(LCS(S1, S2[1:]), LCS(S1[1:], S2))

def testLCS():
    assert LCS("spam","sam!") == 3
    assert LCS("spam","xsam") == 3
    
# programming techique: tail recursion

def exp(n,k):
    """n**k, assuming k>=0 and n is a number"""
    if k == 0: return 1
    else: return n * exp(n,k-1)

def expfast(n,k):
    if k == 0: return 1
    elif k%2==0:
        t = expfast(n, k//2)
        return t*t
    else: return n * expfast(n,k-1)

def testExp():
    assert exp(3,5) == 3**5
    assert exp(2,0) == 2**0
    assert expfast(3,5) == 3**5
    assert expfast(2,0) == 2**0









def exptail(n, k):
    """n**k, computed using tail recursion. Trace this!"""
    def ext(accumulator, n, k):
        if k == 0: return accumulator
        else: return ext(n * accumulator, n, k-1)
    return ext(1, n, k)


# reversing a list - tail recursive version

def reverse(L):
    if L == []: return []
    else: return reverse(L[1:]) + [L[0]]

def reverse_alt(L):
    def rev(acc, L):
        '''.....define this using recursion on L,
        so we can use L==[], L[0], and rev(...,L[1:])'''
        return None 

    return rev([], L)

    # My thinking: I want to define rev so that a trace will
    # look like this:
    #     reverse_alt([a,b,c,d])
    #       rev([], [a,b,c,d])
    #         rev([a], [b,c,d])
    #           rev([b,a],[c,d])
    #             rev([c,b,a],[d])
    #               rev([d,c,b,a],[])
                  

def testReverse():
    assert reverse(['hi','there']) == reverse_alt(['hi','there'])
    assert reverse(range(10)) == reverse_alt(range(10))
    assert reverse([]) == reverse_alt([])

















    

    
    
    
