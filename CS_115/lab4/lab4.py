#Ming Lin
#I pledge my honor that I have abided by the stevens honor system

def knapsack(capacity, itemList): #returns both the maximum value and the list of items that make this value, without exceeding the capacity of your knapsack
    if itemList == []:
        return [0, [] ]
    if capacity == 0:
        return [0, [] ]
    if itemList[0][0] > capacity:
        return knapsack(capacity, itemList[1:])
    lose_it = knapsack(capacity, itemList[1:])
    use_it = knapsack(capacity - itemList[0][0], itemList[1:])
    use_it = [itemList[0][1] + use_it[0]] + [[itemList[0]] + use_it[1]]
    if lose_it[0] > use_it[0]:
        return lose_it
    else: 
        return use_it
    
print(knapsack(76, [[36, 35], [10, 28], [39, 47], [8, 1], [7, 24]]))