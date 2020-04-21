##Ming Lin
##I pledge my honor that I have abided by the Stevens Honor System

def dot(L, K): #performs dot product of L and K
    if L == [] or K == []:
        return 0
    elif len(L)== 1 or len(K) == 1:
        return L[0] * K[0]
    else:
        return L[0] * K[0] + dot(L[1:], K[1:])

def explode(s): #returns each letter in a string as a array
    if s == "":
        return []
    else:
        return ([s[0]] + explode(s[1:]))

def ind(e, L): #returns what postions e is in the array L
    if L == []:
        return 0
    elif e == L[0]:
        return 0
    if len (L) == 1:
        return 1
    return 1+ ind(e, L[1:])

def removeAll(e, L): #removes e from array L
    if L == []:
        return []
    if L[0] == e:
        return [] + removeAll(e, L[1:])
    return ([L[0]] + removeAll(e, L[1:]))

def myFilter(p, L): #returns a new list that contains all of the elements of L for which the predicate returns True
    if L == []:
        return []
    if p(L[0]):
        return ([L[0]] + myFilter(p, L[1:]))
    return myFilter(p, L[1:])

def deepReverse(L): #returns the reverse of inputed array
    if L == []:
        return []
    elif isinstance (L[-1], list):
        return [deepReverse (L[-1])] + deepReverse (L[:-1])
    return [L[-1]] + deepReverse (L[:-1])

