#Ming Lin
#I pledge my honor that I have abided by the Stevens Honor System

def change(amount, coins): #returns the minimum amount of coins required to make up the amount
    if amount == 0:
        return 0
    if coins == [] or amount < 0:
        return float("inf")
    useIt = change(amount - coins[0], coins) + 1
    loseIt = change(amount, coins[1:])
    return min(useIt, loseIt)

print(change(48, [1, 7, 24, 42]))
    