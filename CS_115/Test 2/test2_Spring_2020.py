# CS 115 Spring 2020 Test 2

###########################################################################
# RULES: You can use the following:
# Canvas to download+upload the exam
# IDLE to edit and check your solutions
# Zoom for the class meeting; use private chat to Dave (or Hamzah) if needed.
# One sheet of paper with handwritten notes on both sides (don't submit it).
# Blank paper if you find that helpful work working on your solutions  
# No other resources other than your mind.  
# You have one hour.
# 
# HINT: run this file right now to be sure you downloaded it ok.
# 
###########################################################################

###########################################################################
# NAME and PLEDGE
# Ming Lin
# I pledge my honor that I have abided by the stevens honor system.
# You can use docstring comment or # comment, either is fine.
# And remember why you pledge.  Don't cheat.  
###########################################################################


###########################################################################
# Question 1 (15 points)
# Do you think everyone should get some free points due to the stress of COVID19?
#    A: yes 
#    B: yes 
#    C: yes
#
# ANSWER = A
# 
###########################################################################


###########################################################################
# Question 2 (20 points)
# This table defines a boolean function of three arguments.
#
#    x | y | z | f(x,y,z)
#    --------------------
#    0 | 0 | 0 | 1
#    0 | 0 | 1 | 1
#    0 | 1 | 0 | 0
#    0 | 1 | 1 | 0
#    1 | 0 | 0 | 1
#    1 | 0 | 1 | 0
#    1 | 1 | 0 | 0
#    1 | 1 | 1 | 0        
# 
# Part A: Complete the following Python implementation of f, using a single return expression.
# Use the built-in Python functions "and", "or", "not".
# Hint: use the minterm expansion principle.
###########################################################################

def f(x, y, z):
        
	pass # TO-DO 


###########################################################################
# Question 2 Part B: Complete the following function so that it tests f,
# using assert statements.  Make at least three test cases.
###########################################################################

def test_f():
	assert f(0,0,0) == 1# TO-DO
	assert f(0,0,1) == 1
	assert f(0,1,0) == 0
	
	

###########################################################################
# Question 3: (20 points) 
# Part A: What is the binary representation of 63 in eight bits? 
#    A: 10111111 
#    B: 00111111
#    C: 10101101
#    D: 11000001
#
# ANSWER = B
#
###########################################################################

###########################################################################
# Part B: Using six bits, what is the sum of these two binary numbers:
#    001101 + 000111
# Hint: do this by hand on paper.  Two's complement isn't relevant.
# 
# ANSWER = 010100 
#
###########################################################################

###########################################################################
# Part C: Using two's complement with exactly eight bits, what is the 
# representation of negative 27?
# 
# ANSWER = 11100101
###########################################################################

###########################################################################
# Part D: Using two's complement with five bits, what is the largest
# positive number that can be represented, and what is the smallest?
#
# LARGEST = 15
# 
# SMALLEST = -16
#
###########################################################################


###########################################################################
# Question 4: (15 points) 
# Complete the following function, using recursion on L.  That means you 
# can only access L using the expressions L[0], L==[], and L[1:].
# Hint: use the test function to check your work.
###########################################################################

def takeWhile(f, L):
    '''Assume L is a list and f is a one-argument function that returns True or False.
       Return the elements of L that make f true, up to but not including
       the first element that makes f false. Discard the rest.'''

    if L == [] :
        return [] # TO-DO (replace None with your code)

    elif f(L[0]) :
        return [L[0]] + takeWhile(f, L[1:]) # TO-DO
    else:
        return None # TO-DO

def testTakeWhile():

    assert takeWhile((lambda x: x < 4), [1,2,3,8,0,1,4])  ==  [1, 2, 3]

    assert takeWhile((lambda x: x % 2 == 0), [4,2,7,2,8,5]) == [4, 2]


###########################################################################
# Question 5: (15 points) 
# Read the docstring of these functions and study the code.
# Then add code in LNS to memoize it.
###########################################################################

def LNS(S, let):
    '''Assume S is string of letters and let is a letter.
       Return a longest subsequence of S that is non-decreasing and 
       whose elements are all greater than or equal to let.'''
    
    # TO-DO initialize a dictionary

    dict = {}

    def helper(S, let):
        # TO-DO check the dictionary and if found then return value

        if (S, let) in dict:
                return dict[(S, let)]

        if S == "":
            result = ""
        elif S[0] < let:
            result = helper(S[1:], let)
        else:
            use = S[0] + helper(S[1:], max(let, S[0]))
            lose = helper(S[1:], let)
            if len(use) >= len(lose): result = use
            else: result = lose

        # TO-DO update the dictionary

        dict[S, let] = result

        return result

    return helper(S,let)


def testLNS():
    '''Prints success message or throws exception of failed test.
       In cases where there's more than one correct answer, we
       don't just use "==" to check.'''
    assert LNS("", 'b') == ""
    assert LNS("bbbcc", 'c') == "cc"
    assert LNS("adc", 'a') in [ "ac", "ad" ]
    assert LNS("eddcfc", 'a') == "ddf"
    print("testLNS successful")

###########################################################################
# Question 6: (15 points) 
# Write out the trace of function calls starting from myst(2,7).
# Use indentation to indicate which calls are the result of preceding calls.
#
# ANSWER here, in comment. Add lines as needed.
#
#
#
#
# Hint: You are welcome to modify myst and make it trace itself, but make
# sure you show the trace as requested above.
###########################################################################

def myst(n,k):
    if k == 0: return 1
    elif k % 2 == 0:
        t = myst(n, k//2)
        return t*t
    else: return n * myst(n,k-1)





