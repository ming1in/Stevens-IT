'''
Created on Apr 20, 2016

@author: Brian Borowski
Modified by Kaitlyn Sharo 4/30/20 for Naumann's class
CS115 - Car Test Script
'''
import unittest

from car import *

class Test(unittest.TestCase):

    def test01(self):
        c = Car('Honda', 'Accord', 21.5, 18)
        self.assertTrue(isinstance(c, Car))
        self.assertFalse(isinstance(c, HybridCar))
        self.assertAlmostEqual(c.get_mpg(), 21.5)
        self.assertAlmostEqual(c.get_tank_capacity(), 18)
        c.set_mpg(22.5)
        self.assertAlmostEqual(c.get_mpg(), 22.5)
        c.set_tank_capacity(19.3)
        self.assertAlmostEqual(c.get_tank_capacity(), 19.3)
        self.assertEqual(str(c), 'Honda Accord, MPG: 22.5, tank capacity: 19.3')

    def test02(self):
        c = Car('Honda', 'Civic', 29.5, 13)
        self.assertTrue(isinstance(c, Car))
        self.assertFalse(isinstance(c, HybridCar))
        self.assertAlmostEqual(c.get_total_range(), 383.5)
        self.assertEqual(str(c), 'Honda Civic, MPG: 29.5, tank capacity: 13')
        c.set_mpg(30.5)
        c.set_tank_capacity(14.2)
        self.assertAlmostEqual(c.get_total_range(), 433.10)
        self.assertEqual(str(c), 'Honda Civic, MPG: 30.5, tank capacity: 14.2')

    def test03(self):
        hc = HybridCar('Toyota', 'Prius', 50.2, 8.8, 4.4, 25.8)
        self.assertTrue(isinstance(hc, Car))
        self.assertTrue(isinstance(hc, HybridCar))
        self.assertAlmostEqual(hc.get_mpg(), 50.2)
        self.assertAlmostEqual(hc.get_tank_capacity(), 8.8)
        hc.set_mpg(48)
        hc.set_tank_capacity(9.1)
        self.assertAlmostEqual(hc.get_mpg(), 48)
        self.assertAlmostEqual(hc.get_tank_capacity(), 9.1)
        self.assertEqual(str(hc), 'Toyota Prius, MPG: 48, tank capacity: 9.1, battery kWh: 4.4, miles/kWh: 25.8')

    def test04(self):
        hc = HybridCar('Toyota', 'Prius', 51.2, 7.8, 4.4, 25.8)
        self.assertTrue(isinstance(hc, Car))
        self.assertTrue(isinstance(hc, HybridCar))
        self.assertAlmostEqual(hc.get_battery_range(), 113.52)
        self.assertAlmostEqual(hc.get_total_range(), 512.88)
        self.assertEqual(str(hc), 'Toyota Prius, MPG: 51.2, tank capacity: 7.8, battery kWh: 4.4, miles/kWh: 25.8')

if __name__ == '__main__':
    unittest.main()

