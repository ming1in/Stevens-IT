'''
cs115.py
Provides range, map, filter, and reduce functions in Python 2.7 style for use
with Python 3.

USAGE:
First line of your programs should be:
from cs115 import *

Authors:       Zachary Caldarola, Brian Borowski
Date:          Aug. 31, 2015
Last modified: Jan. 18, 2016
'''
import functools

def __helprange(start, end, step=1):
    lst = []
    if step < 0:
        while start > end:
            lst += [start]
            start += step
    elif step > 0:
        while start < end:
            lst += [start]
            start += step
    else:
        raise ValueError('range() step argument cannot be zero.')
    return lst

def range(*args):
    '''range(stop) -> list of integers
       range(start,stop[,step]) -> list of integers
       Like list(range(...)) in Python 3.'''
    num_args = len(args)
    if num_args == 1: return __helprange(0, args[0])
    if num_args == 2: return __helprange(args[0], args[1])
    if num_args == 3: return __helprange(args[0], args[1], args[2])
    raise TypeError('range() must have 1, 2, or 3 arguments.')

def map(function, sequence):
    '''map(function, sequence) -> list
       Like list(map(...)) in Python 3.'''
    return [function(x) for x in sequence] if function != None \
        else [item for item in sequence if item]

def filter(function, iterable):
    '''filter(function, iterable) -> list
       Like list(filter(...)) in Python 3.'''
    return [item for item in iterable if function(item)] if function != None \
        else [item for item in iterable if item]

def reduce(function, iterable, initializer=None):
    return functools.reduce(function, iterable, initializer) \
        if initializer != None \
        else functools.reduce(function, iterable)

if __name__ == '__main__':
    print(range(0, 10, 2))
    print(map(lambda x: x * x, [1, 2, 3, 4, 5, 6]))
    print(reduce(lambda x, y: x + y, [1, 2, 3, 4, 5, 6]))
    print(filter(lambda x: x % 2 == 0, [1, 2, 3, 4, 5, 6]))
