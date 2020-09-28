# Jan 31 classroom

from cs115 import *

def exclaim(s):
    '''assuming s is a string, add ! at end'''
    return s + '!'

def exclaimAll(strs):
    return map(exclaim, strs)

def suffixAll(s,strs):
    '''add s at the end of each string in strs'''
    return map(makeSuffix(s) ,strs)

def test():
    print(suffixAll('!', ['global','warming']) == ['global!','warming!'])


def makeSuffix(char):
    def suff(s):
        return s + char
    return suff



