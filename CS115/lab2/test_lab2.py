'''
Created on Jan 31, 2015

@author: Brian Borowski

CS115 - Lab 2 Test Script
'''
import unittest
import lab2

class Test(unittest.TestCase):

    def testDot(self):
        self.assertEqual(lab2.dot([], []), 0)
        self.assertEqual(lab2.dot([], [6]), 0)
        self.assertEqual(lab2.dot([2], []), 0)
        self.assertEqual(lab2.dot([5, 3], [6]), 30)
        self.assertEqual(lab2.dot([5, 3], [6, 4]), 42)
        self.assertEqual(lab2.dot([1, 2, 3, 4, 5], [1, 2, 3, 4, 5]), 55)

    def testExplode(self):
        self.assertEqual(lab2.explode(''), [])
        self.assertEqual(lab2.explode('a'), ['a'])
        self.assertEqual(lab2.explode('at'), ['a', 't'])
        self.assertEqual(lab2.explode('hello'), ['h', 'e', 'l', 'l', 'o'])

    def testInd(self):
        self.assertEqual(lab2.ind(42, []), 0)
        self.assertEqual(lab2.ind(42, [55, 77, 42, 12, 42, 100]), 2)
        self.assertEqual(lab2.ind(42, range(0, 100)), 42)
        self.assertEqual(lab2.ind('hi', ['hello', 42, True]), 3)
        self.assertEqual(lab2.ind('hi', ['well', 'hi', 'there']), 1)
        self.assertEqual(lab2.ind('i', 'team'), 4)
        self.assertEqual(lab2.ind(' ', 'outer exploration'), 5)

    def testRemoveAll(self):
        self.assertEqual(lab2.removeAll(42, []), [])
        self.assertEqual(lab2.removeAll(42, [55, 77, 42, 11, 42, 88]), [55, 77, 11, 88])
        self.assertEqual(lab2.removeAll(42, [55, [77, 42], [11, 42], 88]), [55, [77, 42], [11, 42], 88])
        self.assertEqual(lab2.removeAll([77, 42], [55, [77, 42], [11, 42], 88]), [55, [11, 42], 88])

    def testMyFilter(self):
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 0, []), [])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 0, [1, 3, 5]), [])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 0, [2, 4, 6]), [2, 4, 6])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 0, [6, 5, 4, 3, 2, 1]), [6, 4, 2])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 1, []), [])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 1, [1, 3, 5]), [1, 3, 5])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 1, [2, 4, 6]), [])
        self.assertEqual(lab2.myFilter(lambda x: x % 2 == 1, [6, 5, 4, 3, 2, 1]), [5, 3, 1])

    def testDeepReverse(self):
        self.assertEqual(lab2.deepReverse([]), [])
        self.assertEqual(lab2.deepReverse([1]), [1])
        self.assertEqual(lab2.deepReverse([1, 2, 3]), [3, 2, 1])
        self.assertEqual(lab2.deepReverse([1, [2, 3], 4]), [4, [3, 2], 1])
        self.assertEqual(lab2.deepReverse([1, 2, 3]), [3, 2, 1])
        self.assertEqual(lab2.deepReverse([1, [2, [3, 4], [5, [6, 7], 8]]]), [[[8, [7, 6], 5], [4, 3], 2], 1])
        self.assertEqual(lab2.deepReverse([1.25, [2, ["hi", 4], [5, [6, 7], 8]]]), [[[8, [7, 6], 5], [4, 'hi'], 2], 1.25])

if __name__ == "__main__":
    unittest.main()
