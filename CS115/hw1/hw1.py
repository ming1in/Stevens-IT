# Ming Lin
# I pledge my honor that I have abided by the Stevens Honor System
from cs115 import reduce, map

##Writing your own factorial function

def mult(x, y):
    return x * y

def factorial(n): # function that outputs the factorial of n
    fact = 1
    for num in range(2, n + 1):
        fact *= num
    return fact

##This is "mean"...

def add(x,y ): # function that adds inputs x and y
    return x + y

def mean(l): # function that outputs the average of the inputed array 
    return (reduce(add, l) / len(l))

##Testing for Prime Numbers

def div(k): #outputs true if 42 is divisible by k
    return 42 % k == 0

def divides(n): # replaces 42 with n similar to div functino
    def div(k):
        return n % k == 0
    return div

def prime(n): # function that determines if n is a prime number
    return True not in map(divides(n), range(2,n))

print(prime(4))