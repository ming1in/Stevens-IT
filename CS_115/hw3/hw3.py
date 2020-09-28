'''
Created on 2/19/2020
@author:   Ming Lin
Pledge:    I pledge my honor that I have abided by the Stevens Honor System

CS115 - Hw 3
'''
# Be sure to submit hw3.py.  Remove the '_template' from the file name.

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 0
' Implement the function giveChange() here:
' See the PDF in Canvas for more details.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
# your code goes here

def giveChange(amount, coins): # returns the minimum number of coin and which coins were used
    if amount == 0:
        return 0
    if coins == []:
        return [float("inf"), []]
    if amount < coins [0]:
        return giveChange(amount, coins[1:])
    use_it = giveChange(amount - coins[0], coins) 
    use_it2 = [1 + use_it[0], use_it[1] + [coins[0]]]
    lose_it = giveChange(amount, coins[1:])
    if use_it2[0] < lose_it[0]:
        return use_it2
    return lose_it

# Here's the list of letter values and a small dictionary to use.
# Leave the following lists in place.
scrabbleScores = \
   [ ['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2],
     ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], ['m', 3], ['n', 1],
     ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1],
     ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10] ]

Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar', 'babble', 'can', 'foo',
              'spam', 'spammy', 'zzyzva']

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 1
' Implement wordsWithScore() which is specified below.
' Hints: Use map. Feel free to use some of the functions you did for
' homework 2 (Scrabble Scoring). As always, include any helper
' functions in this file, so we can test it.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def letterScore(letter, scorelist): # takes a letter and returns its score
    if scorelist == []:
        return 0
    if letter == scorelist[0][0]:
        return scorelist[0][1]
    return letterScore(letter, scorelist[1:])

def wordScore(word, scorelist): #takes a word and outputs its score
    if word == '':
        return 0
    return letterScore(word[0], scorelist) + wordScore(word[1:], scorelist)

def wordsWithScore(dct, scores):
    '''List of words in dct, with their Scrabble score.    
    Assume dct is a list of words and scores is a list of [letter,number]    
    pairs. Return the dictionary annotated so each word is paired with its    
    value. For example, wordsWithScore(Dictionary, scrabbleScores) should    
    return [['a', 1], ['am', 4], ['at', 2] ...etc... ]    '''

    return map(lambda x: [x, wordScore(x, scrabbleScores)], Dictionary)
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 2
' For the sake of an exercise, we will implement a function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def take(n, L):
    '''Returns the list L[0:n].'''
    if L == []:
        return []
    if n == 0:
        return []
    return [L[0]] + take(n - 1, L[1:])

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 3
' Similar to problem 2, will implement another function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
def drop(n, L):
    '''Returns the list L[n:].'''
    if L == []:
        return []
    if n == 0:
        return L
    return drop(n-1, L[1:])


