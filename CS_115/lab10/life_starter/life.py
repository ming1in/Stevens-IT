#
# life.py - Game of Life lab
#
# Name: Ming Lin
# Pledge: I pledge my honor that i have abided by the stevens honor system
#

import random
import sys

def createOneRow(width):
    """Returns one row of zeros of width "width"...  
       You should use this in your
       createBoard(width, height) function."""
    row = []
    for col in range(width):
        row += [0]
    return row

def createBoard(width, height):
    """ returns a 2d array with "height" rows and "width" cols"""
    A = []
    for row in range(height):
        A += [createOneRow(width)]

    return A

def printBoard(A):
    """ this function prints the 2d list-of-lists A without 
    spaces (using sys.stdout.write) """

    for row in A:
        for col in row:
            sys.stdout.write(str(col))
        
        sys.stdout.write('\n')

def diagonalize(width, height):
    """" creates an empty board and then modifies it
so that it has a diagonal strip of "on" cells. """

    A = createBoard(width, height)

    for row in range(height):
        for col in range(width):
            if row == col:
                A[row][col] = 1
            else:
                A[row][col] = 0

    return A

def innerCells(w, h):
    """returns a 2d array of all live cells - with the value of 1 - except 
    for a one-cell-wide border of empty cells (with the value of 0) around 
    the edge of the 2d array. """

    A = createBoard(w, h)

    for row in range(1, h-1):
        for col in range(1, w-1):
            A[row][col] = 1

    return A

def randomCells(w,h):
    """ returns an array of randomly-assigned 1's and 0's except that the 
    outer edge of the array is still completely empty (all 0's)"""

    A = createBoard(w,h)

    for row in range(1, h-1):
        for col in range(1, w-1):
            A[row][col] = random.choice([0,1])
    
    return A

def copy(A):
    """ makes a deep copy of the 2d array A. """
    w = len(A[0])
    h = len(A)
    newboard = createBoard(w, h)

    for row in range(h):
        for col in range(w):
            newboard[row][col] = A[row][col]

        return newboard

def innerReverse(A):
    """takes an old 2d array (or "generation") and then
    creates a new generation of the same shape and size. 
    However, the new generation should be the "opposite" 
    of A's cells everywhere except on the outer edge"""

    w = len(A[0])
    h = len(A)
    boardN = createBoard(w, h)

    for row in range(1, h - 1):
        for col in range(1, w - 1):
            boardN[row][col] = 1 - A[row][col]
    return boardN 

def next_life_generation(A):
    """ take in a 2d array A, representing the "old" generation of 
    cells, and it should output the next generation of cells, each either 
    0 or 1, based on John Conway's rules for the Game of Life"""

    w = len(A[0])
    h = len(A)
    finalBoard = createBoard(w, h)

    for row in range(1, h - 1):
        for col in range(1, w - 1):
            count = countNeighbors(row, col, A)
            if A[row][col] == 1:
                if count < 2 or count > 3:
                    finalBoard[row][col] = 0
                else:
                    finalBoard[row][col] = 1
            if A[row][col] == 0:
                if count == 3:
                    finalBoard[row][col] = 1
                else:
                    finalBoard[row][col] = 0
    return finalBoard

def countNeighbors(row, col, A):
    """ returns the number of live neighbors for a cell in
    the board A at a particular row and col. """

    sum = 0

    for r in range (row - 1, row + 2):
        for c in range (col - 1, col + 2):
            sum += A[r][c]
    sum -= A[row][col]
    return sum

# A = [ [0,0,0,0,0], [0,0,1,0,0], [0,0,1,0,0], [0,0,1,0,0], [0,0,0,0,0]]

# printBoard(A)

# A2 = next_life_generation(A)

# printBoard(A2)

# A3 = next_life_generation( A2 )

# printBoard(A3)