# nim template DNaumann (2018), for assignment nim_hw11.txt

'''
Ming Lin
I pledge my honor that I have abided by the stevens honor system
'''

# Global variables used by several functions
piles = []         # list containing the current pile amounts
num_piles = 0      # number of piles, which should equal len(pile)


def play_nim():
    """ plays game of nim between user and computer; computer plays optimally """
    
    init_piles()
    display_piles()
    while True:
        user_plays()
        display_piles()
        if sum(piles) == 0:

            # TODO print a message telling the user they won
            print('You won!')

            break
        computer_plays()
        display_piles()
        if sum(piles) == 0:

            # TODO print a message telling the user the computer won
            print('I win ... AGAIN')

            break


def init_piles():
    """ Assign initial values to the global variables 'num_piles' and
        'piles'
        User chooses number of piles and initial size of each pile.
        Keep prompting until they enter valid values."""
    global piles
    global num_piles

    num_piles = int(input('How many piles do you want to play with? '))

    i = 0

    while i < num_piles:
        amountInPile = int(input('How many in pile ' + str(i) + ' ? '))
        piles.append(amountInPile)
        i += 1

        
def display_piles():
    """ display current amount in each pile """
    global piles
    global num_piles

    i = 0

    while i < num_piles:
        print('pile ' + str(i) + '  = ' + str(piles[i]))
        i += 1
        

def user_plays():
    """ get user's choices and update chosen pile """
    global piles
    
    print("Your turn ...")
    p = get_pile()
    amt = get_number(p)
    piles[p] = piles[p] - amt


def get_pile():
    """ return user's choice of pile
        Keep prompting until the choice is valid, i.e.,
        in the range 0 to num_piles - 1. """
    global piles
    global num_piles

    chosenPile = int(input('Which pile? '))

    if chosenPile in range(num_piles):
        return chosenPile
    else:
        return get_pile()
        
    pass # TODO


def get_number(pnum):
    """ return user's choice of how many to remove from pile 'pnum'
        Keep prompting until the amount is valid, i.e., at least 1
        and at most the amount in the pile."""
    global piles

    chosenNum = int(input('How many? '))
    pileAmount = piles[pnum]
    
    if chosenNum in range(1, pileAmount + 1):
        return chosenNum
    else:
        return get_number(pnum)
    
    pass # TODO


def game_nim_sum():
    """ return the nim-sum of the piles """
    global piles
    global num_piles

    nimSum = 0

    for x in piles:
        nimSum = nimSum ^ x

    return nimSum

    pass # TODO 


def opt_play():
    """ Return (p,n) where p is the pile number and n is the amt to
        remove, if there is an optimal play.  Otherwise, (p,1) where
        is the pile number of a non-zero pile.

        Implement this using game_nim_sum() and following instructions
        in the homework text."""
    global piles
    global num_piles

    nimSum = game_nim_sum()
    
    p = 0
    for x in piles:
        pileSum = x ^ nimSum

        if pileSum < x:
            n = x - pileSum
            return [p, n]
        p += 1

    i = 0
    while pile[i] == 0:
        i += 1
        
    return [i, 1]
        
    pass # TODO 


def computer_plays():
    """ compute optimal play, update chosen pile, and tell user what was played

        Implement this using opt_play(). """
    global piles
    global num_piles

    print('My turn ... prepare to be dazzled!!!')

    x = opt_play()

    piles[x[0]] = piles[x[0]] - x[1] 

    print('I remove ' + str(x[1]) + ' from pile ' + str(x[0]))

    pass # TODO

    


#   start playing automatically
if __name__ == "__main__" : play_nim()
