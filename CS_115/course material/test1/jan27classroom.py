# classroom demonstration Jan 27, 2020

from cs115 import *

def dbl(x):
    """doubles a number"""
    y = 2 * x
    return y

def quad(x): return dbl(dbl(x)) # compact style for classroom only

def add(x,y): return x+y

L1 = [1, 23, 2020, 100] # sample data
L2 = [2,4,6,8,10,11,9]

# sample solutions to classroom exercises (Lecture 1 slides)

def evens(n):
    '''the first n even numbers, assuming n is a non-negative integer'''
    nums = range(n)
    return map(dbl, nums)

def sum(L):
    '''the sum of a list of numbers'''
    return reduce(add, L)

def gauss(n):
    '''sum of 1 + ... + n, assuming n is a positive integer'''
    nums = range(1, n+1)
    return sum(nums)

def sumOfSquares(n):
    '''sum of squares up to n'''
    nums = range(1, n+1)
    squares = map(sqr, nums)
    return sum(squares)

def sqr(n): return n * n 
    


