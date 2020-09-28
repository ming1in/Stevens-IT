'''
Created on Feb 14, 2015

@author: Brian Borowski

CS115 - Lab 4 Test Script
'''
import unittest
import lab4

class Test(unittest.TestCase):

    def test01(self):
        self.assertEqual(lab4.knapsack(0, []), [0, []])

    def test02(self):
        self.assertEqual(lab4.knapsack(0, [[2, 100], [3, 112], [4, 125]]), [0, []])

    def test03(self):
        self.assertEqual(lab4.knapsack(6, [[1, 4], [5, 150], [4, 180]]), [184, [[1, 4], [4, 180]]])

    def test04(self):
        self.assertEqual(lab4.knapsack(7, [[2, 100], [3, 112], [4, 125]]), [237, [[3, 112], [4, 125]]])

    def test05(self):
        self.assertEqual(lab4.knapsack(76, [[36, 35], [10, 28], [39, 47], [8, 1], [7, 24]]), [100, [[10, 28], [39, 47], [8, 1], [7, 24]]])

    def test06(self):
        self.assertEqual(lab4.knapsack(8, [[36, 35], [10, 28], [39, 47], [8, 1], [7, 24]]), [24, [[7, 24]]])

    def test07(self):
        self.assertEqual(lab4.knapsack(24, [[36, 35], [10, 28], [39, 47], [8, 1], [7, 24]]), [52, [[10, 28], [7, 24]]])

    def test08(self):
        self.assertEqual(lab4.knapsack(25, [[36, 35], [10, 28], [39, 47], [8, 1], [7, 24]]), [53, [[10, 28], [8, 1], [7, 24]]])

if __name__ == "__main__":
    unittest.main()
