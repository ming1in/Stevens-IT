# Ming Lin
# I pledge my honor that I have abided by the Stevens Honor System

from cs115 import map, reduce, range 
import math

def inverse(n):
    #returns reciprocal of n
    return 1/n

def e(n):
    #approxiates mathematical value e using a taylor expansion
    return reduce(add, map(inverse, map(math.factorial,range(n + 1))))

def add(x,y):
    #performs addition of x and y
    return x + y

def error(n):
    #returns the absolute value of the difference betweeen e and the approximation of the e by fun e(n)
    return abs(math.e - e(n))
