# python strings are immutable!

def EightBitTwosComplement(num) :
    if num > 127 or num < -128 :
        return "Error"
    else :
        return numToTwosComplement(num)

def numToTwosComplement(num, width = 8) :
    if num >= 0 :
        return addBinary(width * "0", numToBinary(num))
    else :
        return TwosComplement(addBinary(width * "0", numToBinary(-num)))

def EightBitTwosComplementToNum(string) :
    if string[0] == "0" :
        return BinaryToNum(string)
    else :
        return -1 * BinaryToNum(TwosComplement(string))

def BinaryToNum(string) :
    if string == "" :
        return 0
    else :
        return int(string[-1]) + 2*BinaryToNum(string[:-1])

# num2binary(num) takes as input an integer and returns
# a binary string representing the number in binary, most
# significant digit first.

def numToBinary(num) :
    if num == 0 : 
        return ""
    else :
        if num % 2 == 1 :
            return numToBinary(num//2) + "1"
        else :
            return numToBinary(num//2) + "0"
    
def addBinary(string1, string2) :
    return addHelper(string1, string2, 0)

def addHelper(string1, string2, carryin) :
    if string1 == "" and string2 == "" :
        if carryin == 1:
            return str(carryin)
        else:
            return ""
    elif string1 == "" : return addHelper(str(carryin), string2, 0)
    elif string2 == "" : return addHelper(string1, str(carryin), 0)
    else :
        sum = int(carryin + int(string1[-1]) + int(string2[-1])) % 2
        carryout = int(carryin + int(string1[-1]) + int(string2[-1])) // 2
        return addHelper(string1[:-1], string2[:-1], carryout) + str(sum)

def complement(string) :
    if string == "" :
        return ""
    else :
        if string[0] == "1" :
            return "0" + complement(string[1:])
        else :
            return "1" + complement(string[1:])

def TwosComplement(string) :
    return addBinary(complement(string), "1")

