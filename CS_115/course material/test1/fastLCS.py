# fastLCS (From HMC book; DN last rev 26 Sept 2018)

# LCS as in the book

def LCS(S1, S2):
    """Length of longest common subsequence."""
    if S1 == "" or S2 == "": return 0
    elif S1[0] == S2[0]:  
        return 1 + LCS(S1[1:], S2[1:])
    else:
        return max(LCS(S1, S2[1:]), LCS(S1[1:], S2))

# same as LCS, but "refactored", i.e., rewritten to make it easier to adapt

def LCSX(S1, S2):
    if S1 == "" or S2 == "": return 0
    elif S1[0] == S2[0]: 
        return 1 + LCSX(S1[1:], S2[1:])
    else:
        chopS1 = LCSX(S1[1:], S2)
        chopS2 = LCSX(S1, S2[1:])
        answer = max(chopS1, chopS2)
        return answer


# Now comes the one that uses memoization to avoid redundancy.

memo = {}  # dictionary for use in fastLCS; keys are tuples (str1,str2)

def fastLCS(S1, S2):

    if (S1, S2) in memo:
        return memo[(S1, S2)]
    elif S1 == "" or S2 == "":
        memo[(S1, S2)] = 0      # put (S1,S2):0 into table ("remember" it)
        return 0
    elif S1[0] == S2[0]:
        answer = 1 + fastLCS(S1[1:], S2[1:])
        memo[(S1, S2)] = answer # remember this
        return answer 
    else:
        chopS1 = fastLCS(S1[1:], S2)
        chopS2 = fastLCS(S1, S2[1:])
        answer = max(chopS1, chopS2)
        memo[(S1, S2)] = answer # remember this
        return answer


def testLCS(S1, S2):
    '''Check that fastLCS agrees with LCS; return true if so, else false.
    Try this with longish strings to get an idea of the difference in timing.'''
    print("S1 is ", S1, " S2 is ", S2)
    print("Using LCS - be patient")
    slow = LCS(S1,S2)
    print("Using fastLCS")
    fast = fastLCS(S1,S2)
    print("Slow result ", slow, "fast result ", fast)

def test(): 
    print(testLCS("Are we done yet?", "Are birds in flight"))


# Refactored version in which the memo is not a global variable

def fastLCSalt(S1, S2):
    memo = {}
    def fLCS(S1, S2):
        if (S1, S2) in memo:
            return memo[(S1, S2)]
        elif S1 == "" or S2 == "":
            memo[(S1, S2)] = 0      
            return 0
        elif S1[0] == S2[0]:
            answer = 1 + fLCS(S1[1:], S2[1:])
            memo[(S1, S2)] = answer 
            return answer 
        else:
            chopS1 = fLCS(S1[1:], S2)
            chopS2 = fLCS(S1, S2[1:])
            answer = max(chopS1, chopS2)
            memo[(S1, S2)] = answer 
            return answer
    return fLCS(S1, S2)



