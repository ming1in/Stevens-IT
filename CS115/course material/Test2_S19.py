
#Question 1

##names = ['homer', 'marge','bart','lisa']
##
##rating = {}
##rating[ names[0] ] = 100
##rating['bart'] = rating['homer'] - 10
##rating['lisa'] = 80
##rating['marge'] = rating['bart'] + 50
##rating['homer'] += 10
##
##print( rating[names[1]])


#Question 3

##INT_TO_HEX_DIGIT = {0 : '0', 1 : '1', 2 : '2', 3 : '3', 4 : '4',
##                    5 : '5', 6 : '6', 7 : '7', 8 : '8', 9 : '9',
##                    10 : 'A', 11 : 'B', 12 : 'C', 13 : 'D', 14 : 'E',
##                    15 : 'F' }
##
##def binaryToNum(binary):
##    if binary == '':
##        return 0
##
##    print('binary[1:] = ' + binary[1:])
##    print('binary[-1] = ' + binary[-1])
##    return 2*binaryToNum(binary[1:]) + int(binary[-1])
##
##def binaryToHex(binary):
##    if binary == '':
##        return ''
##    print('binary[:4] = ' + binary[:4])
##    print('binaryToNum(binary[:4]) = ' + str(binaryToNum(binary[:4])))
##    return binaryToHex(binary[:4]) + INT_TO_HEX_DIGIT[binaryToNum(binary[:4])]
##
##binaryToHex('1010')

#Question 7

def seq(n):
    '''Returns the nth number in a special sequence using memoization.'''

    def seq_memo(n, memo):
        if n in memo:
            return memo[n]
        
        if n <= 1 or n % 3 == 0:
            result = n
        else:
            result = seq_memo(n - 1, memo) + seq_memo(n - 2, memo)
            
        memo[n] = result
        return result
    
    return seq_memo(n, {})

seq(5)



