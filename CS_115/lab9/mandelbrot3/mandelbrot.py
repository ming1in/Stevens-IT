# mandelbrot.py
# Lab 9
#
# Name:

# keep this import line...
from cs5png import PNGImage

# start your Lab 9 functions here:

def mult(c,n): #mult uses only a loop and addition to multiply c by the integer n
    result = 0

    for x in range(n): # update the value of result here in the loop
        result = x * n

    return result

print(mult( 6, 7 ))

def update(c,n): #update starts with z=0 and runs z = z**2 + c for a total of n times. It returns the final z.
    z = 0

    for x in range(n):
        z = z**2 + c

    return z

print(update( 1, 3 ))

def inMSet(c,n):
    z = 0 + 0j

    for x in range(n):
        z = z**2+c

        if abs(z) > 2:
            return False

    return True

c = 0 + 0j
print(inMSet(c, 25))

def weWantThisPixel(col,row):
    if col%10 == 0 and row%10 == 0:
        return True
    else:
        return False

def test(): #a function to demonstrate howto create and save a png image
    width = 300
    height = 200
    image = PNGImage(width, height)
    
    for col in range(width):
        for row in range(height):
            if weWantThisPixel( col, row ) == True:
                image.plotPoint(col, row)
    image.saveFile()

def scale(pix, pixelMax, floatMin, floatMax):
    if pix == 0:
        return floatMin
    else: 
        x = pix*1.0 / pixelMax
        y = floatMax - floatMin
        return (x*y) + floatMin
    
print(scale(100, 200, -2.0, 1.0))  #-0.5

def mset():
    width = 300
    height = 200
    image = PNGImage(width, height)
    for col in range(width):
        for row in range(height):
            x = scale(col, width, -2, 1)
            y = scale(row, height, -1.0, 1.0)
            c = x + y*1j
            if inMSet(c,25) == True:
                image.plotPoint(col, row)
    image.saveFile()

mset()    
        
    




