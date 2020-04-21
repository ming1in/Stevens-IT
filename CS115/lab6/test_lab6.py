'''
Created on Feb 28, 2015
Last modified on Jan 23, 2016

@author: Brian Borowski

CS115 - Lab 6 Test Script
'''
import unittest
import sys
import lab6
from io import StringIO

class Test(unittest.TestCase):

    def test01(self):
        self.assertEqual(lab6.isOdd(0), False)
        self.assertEqual(lab6.isOdd(1), True)
        self.assertEqual(lab6.isOdd(42), False)
        self.assertEqual(lab6.isOdd(43), True)
        self.assertEqual(lab6.isOdd(14312), False)
        self.assertEqual(lab6.isOdd(21617), True)

    def test02(self):
        self.assertEqual(lab6.numToBinary(0), '')
        self.assertEqual(lab6.numToBinary(1), '1')
        self.assertEqual(lab6.numToBinary(4), '100')
        self.assertEqual(lab6.numToBinary(10), '1010')
        self.assertEqual(lab6.numToBinary(42), '101010')
        self.assertEqual(lab6.numToBinary(100), '1100100')
        self.assertEqual(lab6.numToBinary(2587194), '1001110111101000111010')

    def test03(self):
        self.assertEqual(lab6.binaryToNum(''), 0)
        self.assertEqual(lab6.binaryToNum('0'), 0)
        self.assertEqual(lab6.binaryToNum('1'), 1)
        self.assertEqual(lab6.binaryToNum('100'), 4)
        self.assertEqual(lab6.binaryToNum('1011'), 11)
        self.assertEqual(lab6.binaryToNum('00001011'), 11)
        self.assertEqual(lab6.binaryToNum('101010'), 42)
        self.assertEqual(lab6.binaryToNum('1100100'), 100)
        self.assertEqual(lab6.binaryToNum('1001110111101000111010'), 2587194)

    def test04(self):
        self.assertEqual(lab6.increment('00000000'), '00000001')
        self.assertEqual(lab6.increment('11111111'), '00000000')
        self.assertEqual(lab6.increment('00000001'), '00000010')
        self.assertEqual(lab6.increment('00000111'), '00001000')
        self.assertEqual(lab6.increment('00101101'), '00101110')
        self.assertEqual(lab6.increment('11101101'), '11101110')

    def test05(self):
        # Reassign stdout for the duration of the test.
        saved_stdout = sys.stdout
        try:
            sys.stdout = StringIO()
            lab6.count('00010000', 0)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '00010000')
            sys.stdout.close()
            sys.stdout = StringIO()
            lab6.count('00000000', 4)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '00000000\n00000001\n00000010\n00000011\n00000100')
            sys.stdout.close()
            sys.stdout = StringIO()
            lab6.count('11111110', 5)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '11111110\n11111111\n00000000\n00000001\n00000010\n00000011')
        finally:
            sys.stdout.close()
            sys.stdout = saved_stdout

    def test06(self):
        self.assertEqual(lab6.numToTernary(0), '')
        self.assertEqual(lab6.numToTernary(1), '1')
        self.assertEqual(lab6.numToTernary(4), '11')
        self.assertEqual(lab6.numToTernary(10), '101')
        self.assertEqual(lab6.numToTernary(42), '1120')
        self.assertEqual(lab6.numToTernary(100), '10201')
        self.assertEqual(lab6.numToTernary(2587194), '11212102222000')

    def test07(self):
        self.assertEqual(lab6.ternaryToNum(''), 0)
        self.assertEqual(lab6.ternaryToNum('1'), 1)
        self.assertEqual(lab6.ternaryToNum('11'), 4)
        self.assertEqual(lab6.ternaryToNum('101'), 10)
        self.assertEqual(lab6.ternaryToNum('1120'), 42)
        self.assertEqual(lab6.ternaryToNum('10201'), 100)
        self.assertEqual(lab6.ternaryToNum('11212102222000'), 2587194)

if __name__ == "__main__":
    unittest.main()
