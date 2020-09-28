'''
Ming Lin
SSW215: Nim Assignment

I pledge my honor that I have abided by the Stevens Honor System
'''

options = ["1","2","3"]

def nim(x):

    stack = x

    while stack > 0:
    
        player1 = 0

        while player1 not in options:
            player1 = input("Player 1: you may only remove up to 3 sticks,enter the amount you want to remove: ")
            
        stack = stack - int(player1)

        if stack <= 0:
            print("Winner: Player2")
            break
        else:
            print("There are now " + str(stack) + " straw(s) left")


        player2 = 0

        while player2 not in options:
            player2 = input("Player 2: you may only remove up to 3 sticks,enter the amount you want to remove: ")
            
        stack = stack - int(player2)

        if stack <= 0:
            print("Winner: Player1")
            break
        else:
            print("There are now " + str(stack) + " straw(s) left")
   
    endGame = " "
    while endGame not in ["Y","N"]:
            endGame = input("Game Over! Would you like to play again(Y/N): ")

    if endGame == "Y":
        nim(21)
    else:
        print("Thanks for playing!")
        
nim(21)

