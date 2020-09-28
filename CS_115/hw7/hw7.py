'''
Name: Ming Lin
I pledge my honor that I have abided by the stevens honor code
'''

FullAdder = {
('0','0','0') : ('0','0'),
('0','0','1') : ('1','0'),
('0','1','0') : ('1','0'),
('0','1','1') : ('0','1'),
('1','0','0') : ('1','0'),
('1','0','1') : ('0','1'),
('1','1','0') : ('0','1'),
('1','1','1') : ('1','1') }

def numToBaseB(N, B): # takes as input a non-negative (0 or larger) integer N and a base B (between 2 and 10 inclusive) and returns a string representing the number N in base B.
    res = N % B

    if N <= 1:
        return str(N)
    return str(numToBaseB(N // B, B)) + str(res)

def baseBToNum(S, B): #return an integer in base 10 representing the same number as S
    if S == '':
        return 0 
    return int(S[0]) * (B **(len(S) - 1)) + baseBToNum(S[1:], B)

def baseToBase(B1,B2,SinB1): #return a string representing the same number in base B2.
    if B1 == '' or B2 == '':
        return ''
    return numToBaseB(baseBToNum(SinB1, B1), B2)

def add(S,T): #takes two binary strings S and T as input and returns their sum, also in binary
    if S == '' and T == '' :
        return str(0)
    res = numToBaseB(baseBToNum(S, 2) + baseBToNum(T, 2), 2)
    return res

def addB(s1, s2): #Takes two strings as input which represent binary numbers
    def helper(x, y, overflow):
        if x == '':
            if y == '':
                if overflow == '0':
                    return ''
                return '1'
            v1,v2 = FullAdder[('0', y[-1], overflow)]
            return helper('', y[:-1], v2) + v1
        else:
            if y == '':
                if overflow == '0':
                    v1,v2 = FullAdder[(x[-1], '0', '0')]
                    return helper(x[:-1], '', v2) + v1
                if overflow == '1':
                    v1,v2 = FullAdder[(x[-1], '0', '1')]
                    return helper(x[:-1], '', v2) + v1
            if y != '':
                if overflow == '0':
                    v1,v2 = FullAdder[(x[-1], y[-1], '0')]
                    return helper(x[:-1], y[:-1], v2) + v1
                if overflow == '1':
                    v1,v2 = FullAdder[(x[-1], y[-1], '1')]
                    return helper(x[:-1], y[:-1], v2) + v1
    return helper(s1, s2, '0')

print(addB("011", "100"))
