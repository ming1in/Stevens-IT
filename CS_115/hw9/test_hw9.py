'''
Created on Apr 1, 2015

@author: Brian Borowski

CS115 - Hw 9 Test Script
'''
import unittest
import hw9

class Test(unittest.TestCase):

    def test01(self):
        self.assertEqual(hw9.questifyAlt([]), [])
        self.assertEqual(hw9.questifyAlt(['yeah']), ['yeah?'])
        self.assertEqual(hw9.questifyAlt(['yeah', 'really', 'no way']), ['yeah?', 'really?', 'no way?'])

    def test02(self):
        self.assertEqual(hw9.catenateLoop([]), '')
        self.assertEqual(hw9.catenateLoop(['this']), 'this')
        self.assertEqual(hw9.catenateLoop(['this', 'function', 'actually', 'works']), 'thisfunctionactuallyworks')

    def test03(self):
        self.assertEqual(hw9.letterScoreLoop('a', hw9.scrabbleScores), 1)
        self.assertEqual(hw9.letterScoreLoop('f', hw9.scrabbleScores), 4)
        self.assertEqual(hw9.letterScoreLoop('q', hw9.scrabbleScores), 10)
        self.assertEqual(hw9.letterScoreLoop('z', hw9.scrabbleScores), 10)

    def test04(self):
        self.assertEqual(hw9.wordScoreLoop('', hw9.scrabbleScores), 0)
        self.assertEqual(hw9.wordScoreLoop('test', hw9.scrabbleScores), 4)
        self.assertEqual(hw9.wordScoreLoop('zebra', hw9.scrabbleScores), 16)
        self.assertEqual(hw9.wordScoreLoop('manufacturing', hw9.scrabbleScores), 21)

    def test05(self):
        self.assertEqual(hw9.wordsWithScoreLambda([], hw9.scrabbleScores), [])
        self.assertEqual(hw9.wordsWithScoreLambda(['a'], hw9.scrabbleScores), [['a', 1]])
        self.assertEqual(hw9.wordsWithScoreLambda(['python', 'is', 'awesome'], hw9.scrabbleScores), [['python', 14], ['is', 2], ['awesome', 12]])
        self.assertEqual(hw9.wordsWithScoreLambda(hw9.aDictionary, hw9.scrabbleScores), [['a', 1], ['am', 4], ['at', 2], ['apple', 9], ['bat', 5], ['bar', 5], ['babble', 12], ['can', 5], ['foo', 6], ['spam', 8], ['spammy', 15], ['zzyzva', 39]])

    def test06(self):
        self.assertEqual(hw9.wordsWithScoreLoop([], hw9.scrabbleScores), [])
        self.assertEqual(hw9.wordsWithScoreLoop(['a'], hw9.scrabbleScores), [['a', 1]])
        self.assertEqual(hw9.wordsWithScoreLoop(['python', 'is', 'awesome'], hw9.scrabbleScores), [['python', 14], ['is', 2], ['awesome', 12]])
        self.assertEqual(hw9.wordsWithScoreLoop(hw9.aDictionary, hw9.scrabbleScores), [['a', 1], ['am', 4], ['at', 2], ['apple', 9], ['bat', 5], ['bar', 5], ['babble', 12], ['can', 5], ['foo', 6], ['spam', 8], ['spammy', 15], ['zzyzva', 39]])

if __name__ == "__main__":
    unittest.main()
