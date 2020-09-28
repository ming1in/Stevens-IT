from turtle import *

currentL = None

COL = -1
ROW = -1

currentXs = []
currentYs = []

def getPos(mouse_x, mouse_y):
    """Returns the row and column clicked by the mouse in a tuple."""
    global currentXs;
    global currentYs;
    global COL
    global ROW

    COL = 0
    ROW = 0

    for i in range(len(currentXs) - 1):
        if currentXs[i] <= mouse_x < currentXs[i + 1]:
            COL = i
    for i in range(len(currentYs) - 1):
        if currentYs[i] <= mouse_y < currentYs[i - 1]:
            ROW = i - 1

    if mouse_x > 0 and COL == 0:
        COL = len(currentXs) - 2
    if mouse_y < 0 and ROW == 0:
        ROW = len(currentYs) - 2

    return (ROW, COL)


clrD = { 0:"white", 1:"red", 2:"blue", 3:"green", 4:"gold" }

def setColor(key, color):
    global clrD
    clrD[key] = color
    return

def colorLookup(clr):
    global clrD
    if clr in clrD:
        return clrD[clr]
    else:
        return clr

def drawsq(ulx, uly, side, clr):
    """Draws a single square, and fills it based on the
        number held in that square's position on the array"""
    delay(0)
    tracer(False)
    up()
    # try setting the color
    pencolor("black")
    # look up the color
    clr = colorLookup(clr)
    # go!
    try:
        fillcolor(clr)
    except:
        print("Color", clr, "was not recognized.")
        print("Using blue instead.")
        fillcolor("blue")

    goto(ulx, uly)
    down()
    seth(0)  # east in normal mode

    begin_fill()
    for s in range(4):
        forward(side)
        right(90)
    end_fill()

    up()


def show1d(L):
    """Shows a 1d list L using turtle graphics """
    # remember this!
    global currentL
    currentL = L

    W = window_width()
    H = window_height()
    if len(L) == 0:
        print("You can't show(L) when L is empty.")
        return

    n = len(L) + 2  # 2 more for a margin on each side

    sq_side = min(W / float(n), H / float(3), 100.0)

    uly = 0 + sq_side / 2.0
    ulx = -sq_side * len(L) / 2.0

    global currentYs
    currentYs = [-uly, uly]
    global currentXs
    currentXs = [ulx]


    clear()
    for clr in L:
        # print "clr is", clr
        drawsq(ulx, uly, sq_side, clr)
        ulx += sq_side
        currentXs.append(ulx)

    return

def show2d(L):
    """Shows a 2d grid L using turtle graphics"""
    # remember this!
    global currentL
    currentL = L

    W = window_width()
    H = window_height()
    if len(L) == 0:
        print("You can't show(L) when L is empty.")
        return

    n = len(L) + 2  # 2 more for a margin on each side

    sq_side = min(W / float(n), H / float(n), 100.0)

    uly = 0 + sq_side * len(L) / 2.0
    ulx = -sq_side * len(L[0]) / 2.0

    global currentYs
    currentYs = [uly]
    global currentXs
    currentXs = [ulx]

    clear()
    for row in L:
        for clr in row:
            # print "clr is", clr
            drawsq(ulx, uly, sq_side, clr)
            ulx += sq_side
            if ulx not in currentXs:
                currentXs.append(ulx)
                # print [ulx, uly]
            # else: print [ulx, uly]
        uly -= sq_side
        currentYs.append(uly)
        ulx = -sq_side * len(L) / 2.0
    return

def show(L):
    """Shows the list or grid L using the graphics"""
    if type(L[0]) == list:
        show2d(L)
    else: show1d(L)
    return

# set the mouse handler...
def lifeMouseHandler(x, y):
    """ This function is called with each mouse click.

        Its inputs are the pixel location of the
        next mouse click: (x,y)

        It computes the column and row (within the board)
        where the click occurred with getPos, and changes the
        color of the clicked square.

        The overall list is shared between turtle graphics
        and the other parts of your program as a global
        variable named currentL. In general, global variables
        make software more complex, but sometimes they are
        necessary.
    """
    getPos(x, y)  # update the position
    if ROW == 0 or ROW == len(currentL) - 1 or COL == 0 or COL == len(currentL[0]) - 1:
        print("Don't click on the border!!! >:O")
    else: currentL[ROW][COL] = 1 - currentL[ROW][COL]
    show(currentL)

