'''
Created on 2/10/2020
@author:   Ming Lin
Pledge:    I pledge my honor that I have abided by the Stevens Honor System

CS115 - Hw 2
'''
import sys
from cs115 import map, reduce, filter

# Allows up to 10000 recursive calls.
# The maximum permitted limit varies from system to system.
sys.setrecursionlimit(10000)

scrabbleScores = \
   [ ['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2],
     ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], ['m', 3], ['n', 1],
     ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1],
     ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10] ]

Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar', 'babble', 'can', 'foo',
              'spam', 'spammy', 'zzyzva']

def  letterScore(letter, scorelist): #returns the score for the letter inputed
  if letter == []:
    return 0
  if letter == scorelist[0][0]:
    return scorelist[0][1]
  return letterScore(letter, scorelist[1:])

def wordScore(S, scorelist): #returns the total score for a word
  if S == '':
    return 0
  return letterScore(S[0], scorelist) + wordScore(S[1:], scorelist)

def possiblewords(dictionary, Rack): #returns highest scoring words made from Rack
    def possible(word, Rack):
        if word == '':
            return True
        if word[0] not in Rack:
            return False
        return possible(word[1:], remove(word[0], Rack))
    return filter(lambda word: possible(word, Rack), dictionary)

def remove(e, L): #removes positions of L until e is equal to L
    if L == []:
        return []
    if e == L[0]:
        return L[1:]
    return [L[0]] + remove(e, L[1:])  

def scoreList(Rack): #takes a list of lower-case letters and returns a list of all of the words/score that can be made from the letters
    return map(givewordscore, possiblewords(Dictionary, Rack))

def givewordscore(word): #assigns the word score to the word
    return [word, wordScore(word, scrabbleScores)]

def bestWord(Rack): #take Rack and returns a list with two elements, highest scoring word and the score
    def better_word(x,y):
        if x[1]>y[1]:
            return x
        return y 
    z = scoreList(Rack)
    if z == []:
        return ['', 0]
    return reduce(better_word, z)

print(possiblewords(Dictionary, ['a', 's', 'm', 't', 'p']))