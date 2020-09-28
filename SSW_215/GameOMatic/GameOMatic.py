'''
Ming Lin
I plegde my honor that i have abided by the stevens honor code
'''

import random

#PART 1

def Ddecahedron(rolls):
    dict={}
    
    i = 0
    while i < rolls:
        randNum = random.randrange(1, 13)
        if randNum in dict:
            dict[randNum] += 1
        else:
            dict[randNum] = 1
        i += 1
    return dict

#PART 2

gameDict={}

def Main():
    print('List of Characters' + '\n' +
          '-------------------------------------')
    for character in gameDict:
        print(character.capitalize())
        
    print('Enter a letter to choose an option:' + '\n' +
            'n - Add a new character' + '\n' + 
            'c - Choose a character'
          )
    choice=input()

    if choice == 'n':
        AddNewCharacter()
    elif choice == 'c':
        Character(None)
    else:
        Main()
        
def AddNewCharacter():
    print('Give your character a name:')
    name=input()
    lowerName = name.lower()
    if lowerName != '' and lowerName not in gameDict:
        gameDict[lowerName] = {}
    else:
        print('We already have that character.')
        Main()

    Character(lowerName)
    
def Character(character):
    if character == None:
        print('Enter the name of a character: ')
        characterInput=input()
        character = characterInput.lower()
        
    if character in gameDict:
        print('Enter a letter to choose an option:' + '\n' +
                'u - Use item in inventory' + '\n' + 
                'a - Add item to inventory' + '\n' +
                'n - Select a new character' + '\n' +
                'i - See your inventory'
              )
        
        choice=input()

        if choice == 'u':
            UseItem(character)
        elif choice == 'a':
            AddItem(character)
        elif choice == 'n':
            Main()
        elif choice == 'i':
            Inventory(character)
        else:
            Character(character)
    else:
        Main()

def Inventory(character):
    print(character.capitalize() +'\'s Inventory' + '\n' +
          '-------------------------------------')
    for item in gameDict[character]:
        print(item + ' - ' + str(gameDict[character][item]))
    Character(character)


def AddItem(character):
    print('Enter the name of the item: ')
    item=input()
    itemLower = item.lower()
    print('How much do you want to add: ')
    quantity=input()

    if itemLower != '' and quantity != '':
        if itemLower in gameDict[character]:
            gameDict[character][itemLower] += int(quantity)
        else:
            gameDict[character][itemLower] = int(quantity)
        print(character.capitalize() + ' added ' + quantity + ' ' + itemLower + '(s)' + '\n')
    else:
        print('Please enter a item')
        AddItem(character)

    Character(character)

    
def UseItem(character):
    print('Enter the name of the item: ')
    item=input()
    itemLower = item.lower()
    print('How much do you want to use: ')
    quantity=input()

    if itemLower != '' and quantity != '':
        if itemLower not in gameDict[character]:
            print(character.capitalize() + ' does not have a ' + itemLower + '\n' )
        elif int(quantity) > gameDict[character][itemLower]:
            print(character.capitalize() + ' does not have enough!' + '\n')
        else:
            gameDict[character][itemLower] -= int(quantity)

            print(character.capitalize() + ' used ' + quantity + ' ' + item + '(s)' + '\n')

            if gameDict[character][itemLower] == 0:
                gameDict[character].pop(itemLower)
                print('You have none left' + '\n')
    else:
        print('Please enter a item' + '\n')
        UseItem(character)

    Inventory(character)



    
    
    
