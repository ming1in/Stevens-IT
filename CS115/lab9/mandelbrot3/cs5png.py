import png
import os

def saveRGB(boxed_pixels, filename="out.png"):
    """ need docstrings! """
    print('Starting to save', filename, '...')
    f = open(filename, 'wb')
    W, H = getWH(boxed_pixels)
    w = png.Writer(W, H)
    # print "boxed_pixels are", boxed_pixels
    pixels = unbox(boxed_pixels)
    # print "pixels are", pixels
    w.write(f, pixels)
    f.flush()
    os.fsync(f.fileno())
    f.close()
    print(filename, "saved.")

def unbox(boxed_pixels):
    """ assumes the pixels came from box
        and unboxes them!
    """
    flat_pixels = []
    for boxed_row in boxed_pixels:
        flat_row = []
        for pixel in boxed_row:
            flat_row.extend(pixel)
        flat_pixels.append(flat_row)
    return flat_pixels

def box(L):
    """ boxes the flat pixels in row L
        assumes three channels!
    """
    newL = []
    STRIDE = 4  # since we're using RGBA!
    for i in range(len(L) / STRIDE):
        newL.append(L[STRIDE * i:STRIDE * i + 3])  # since we're providing RGB
    return newL


def getRGB(filename="in.png"):
    """ need docstrings! """
    print("Opening the", filename, " file (each dot is a row)", end='')
    reader = png.Reader(filename)
    # data = reader.read()
    data = reader.asRGBA()
    pixels = data[2]  # this is an iterator...
    PIXEL_LIST = []
    while True:
        try:
            a = pixels.index()
            print(".", end='')
            PIXEL_LIST.append(box(a.tolist()))
        except StopIteration:
            print("\nFile read.")
            break

    return PIXEL_LIST

def getWH(PX):
    """ need docstrings! """
    H = len(PX)
    W = len(PX[0])
    return W, H

def binaryIm(s, cols, rows):
    """ need docstrings! """
    PX = []
    for row in range(rows):
        ROW = []
        for col in range(cols):
            c = int(s[row * cols + col]) * 255
            px = [ c, c, c ]
            ROW.append(px)
        PX.append(ROW)
    saveRGB(PX, 'binary.png')
    # return PX

class PNGImage:

    def __init__(self, width, height):
        """ constructor for PNGImage """
        self.width = width
        self.height = height
        default = (255, 255, 255)
        self.image_data = \
            [ [ default for _ in range(width) ] \
                        for __ in range(height)]

    def plotPoint(self, col, row, rgb=(0, 0, 0)):
        """ plot a single point to a PNGImage """
        # check if rgb is a three-tuple
        if type(rgb) == type((0, 0, 0)) and \
           len(rgb) == 3:
            pass  # ok
        elif type(rgb) == type([0, 0, 0]) and \
           len(rgb) == 3:
            rgb = tuple(rgb)
        else:
            print("in plotPoint(), the color", rgb, "was not in a recognized format.")

        # check if we're in bounds
        if 0 <= col < self.width and 0 <= row < self.height:
            self.image_data[ row ][ col ] = rgb
        else:
            print("in plotPoint(), the (col, row) = (", col, row, ") was not in bounds.")
        return

    def saveFile(self, filename="test.png"):
        """ save the object's data to a file """
        # we reverse the rows so that the y direction
        # increases upwards...
        saveRGB(self.image_data[::-1], filename)
