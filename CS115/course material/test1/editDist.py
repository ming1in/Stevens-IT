'''edit distance and other examples
   DN Sept 20, 2018
'''
from cs115 import *

def ED(first, second):
   ''' Returns the edit distance between the strings first and second.'''
#   print("ED("+first+","+second+")")
   if first == '': 
      return len(second)
   elif second == '':
      return len(first)
   elif first[0] == second[0]:
      return ED(first[1:], second[1:])
   else:
      substitution = 1 + ED(first[1:], second[1:])
      deletion = 1 + ED(first[1:], second)
      insertion = 1 + ED(first, second[1:])
      return min(substitution, deletion, insertion)

def testED():
   '''Run a few simple tests which should print True'''
   print("Testing ED")  
   print(ED("spam", "xsam") == 2)
   print(ED("foo", "") == 3)
   print(ED("foo", "bar") == 3)
   print(ED("hello", "below") == 3)
   print(ED("yes", "yelp") == 2)

''' exam review '''

def reverse(lst):
   def rev(lst,acc):
      if lst==[]: return acc
      else: return rev(lst[1:], [lst[0]]+acc)
   return rev(lst,[])

# Trace the calls in reverse([2,3,4])












L = ['a', 'b', 'c', 'd', 'e']
M = [1, 2, 4, 8, 16]
N = L[ M[1]: ] 
# WHAT IS THE VALUE OF N after these statements have been executed?

P = [1, 3, 5, 10, 20]
Q = filter(lambda x: x > 20, map(lambda x: x ** 2, P))
# WHAT IS THE VALUE OF Q after these statements have been executed?

def collatz(n):
    print(n, end=' ') # prints n, then a space; doesn't go to next line
    if n == 1:
        return
    if n % 2 == 0:
        collatz(n // 2)
    else:
        collatz(3 * n + 1)
# Consider evaluation of collatz(5).  What numbers are printed?
# Show a trace of collatz(5); just the calls to collatz.

# PROBLEM: implement this function.
def makeAt(strs):
   '''Assuming strs is a list of non-empty strings, return the
   ones that begin with 'a' or 'A', but with that replaced by @.
   Use map, filter, lambda.'''
   return None 

def testMakeAt():
   assert makeAt(["the","answer","is","a","filter","of","a","map","Alice"]) \
          == ["@nswer","@","@","@lice"]
   assert makeAt(["nothing","here"]) == []



# SPOILER ALERT for makeAt
#    startA = filter(lambda w: w[0]=='a' or w[0]=='A', strs)
#    return map(lambda w: '@' + w[1:], startA)
