'''
Created on Feb 3, 2015

@author: Brian Borowski

CS115 - Hw 2 Test Script
'''
import unittest
import operator
import hw2

class Test(unittest.TestCase):

    def test01(self):
        self.assertEqual(hw2.letterScore('a', hw2.scrabbleScores), 1)
        self.assertEqual(hw2.letterScore('c', hw2.scrabbleScores), 3)
        self.assertEqual(hw2.letterScore('q', hw2.scrabbleScores), 10)
        self.assertEqual(hw2.letterScore('t', hw2.scrabbleScores), 1)
        self.assertEqual(hw2.letterScore('x', hw2.scrabbleScores), 8)

    def test02(self):
        self.assertEqual(hw2.wordScore('a', hw2.scrabbleScores), 1)
        self.assertEqual(hw2.wordScore('spam', hw2.scrabbleScores), 8)
        self.assertEqual(hw2.wordScore('apple', hw2.scrabbleScores), 9)
        self.assertEqual(hw2.wordScore('computer', hw2.scrabbleScores), 14)
        self.assertEqual(hw2.wordScore('wow', [['o', 10], ['w', 42]]), 94)

    def test03(self):
        scores = hw2.scoreList(['a', 's', 'm', 't', 'p'])
        # Sort by scores
        scores.sort(key=operator.itemgetter(1))
        self.assertEqual(scores, [['a', 1], ['at', 2], ['am', 4], ['spam', 8]])

    def test04(self):
        scores = hw2.scoreList(['r', 's', 't', 'n', 'l', 'e', 'a'])
        # Sort by scores
        scores.sort(key=operator.itemgetter(1))
        self.assertEqual(scores, [['a', 1], ['at', 2]])

    def test05(self):
        scores = hw2.scoreList(['b', 't', 'f', 'c', 'a', 'o'])
        # Sort by scores
        scores.sort(key=operator.itemgetter(1))
        self.assertEqual(scores, [['a', 1], ['at', 2], ['bat', 5]])

    def test06(self):
        scores = hw2.scoreList(['a', 's', 'm', 'o', 'f', 'o'])
        # Sort by scores
        scores.sort(key=operator.itemgetter(1))
        self.assertEqual(scores, [['a', 1], ['am', 4], ['foo', 6]])

    def test07(self):
        self.assertEqual(hw2.bestWord(['a']), ['a', 1])

    def test08(self):
        self.assertEqual(hw2.bestWord(['a', 's', 'm', 't', 'p']), ['spam', 8])

    def test09(self):
        self.assertEqual(hw2.bestWord(['g', 'y', 'e']), ['', 0])

    def test10(self):
        self.assertEqual(hw2.bestWord(['b', 'b', 'b', 'l', 'r', 'a', 'e']), ['babble', 12])

if __name__ == "__main__":
    unittest.main()
