'''
fastED from lab5
'''

#program function trace calls with indentations

def fastED(first, second):
    '''Returns the edit distance between the strings first and second. Uses
    memoization to speed up the process.'''
    def fastED_help(first, second, memo):
        if (first, second) in memo:
            return memo[(first, second)]
        elif first == '':
            result = len(second)
        elif second == '':
            result = len(first)
        elif first[0] == second[0]:
            result = fastED_help(first[1:], second[1:], memo)
        else:
            substitution = 1 + fastED_help(first[1:], second[1:], memo)
            deletion = 1 + fastED_help(first[1:], second, memo)
            insertion = 1 + fastED_help(first, second[1:], memo)
            result = min(substitution, deletion, insertion)
        memo[(first, second)] = result
        return result    
    return fastED_help(first, second, {})

