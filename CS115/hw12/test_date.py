'''
Created on Apr 17, 2015
Last modified on Apr 18, 2016

@author: Brian Borowski

CS115 - Date Test Script
'''
import unittest
import sys
import date
from io import StringIO

class Test(unittest.TestCase):

    def test01(self):
        d = date.Date(1, 13, 2000)
        d.tomorrow()
        self.assertEqual(str(d), '01/14/2000')
        d = date.Date(2, 27, 2015)
        d.tomorrow()
        self.assertEqual(str(d), '02/28/2015')
        d.tomorrow()
        self.assertEqual(str(d), '03/01/2015')
        d = date.Date(2, 28, 2016)
        d.tomorrow()
        self.assertEqual(str(d), '02/29/2016')
        d = date.Date(12, 31, 2012)
        d.tomorrow()
        self.assertEqual(str(d), '01/01/2013')

    def test02(self):
        d = date.Date(1, 13, 2000)
        d.yesterday()
        self.assertEqual(str(d), '01/12/2000')
        d = date.Date(3, 1, 2015)
        d.yesterday()
        self.assertEqual(str(d), '02/28/2015')
        d.yesterday()
        self.assertEqual(str(d), '02/27/2015')
        d = date.Date(3, 1, 2016)
        d.yesterday()
        self.assertEqual(str(d), '02/29/2016')
        d = date.Date(1, 1, 2014)
        d.yesterday()
        self.assertEqual(str(d), '12/31/2013')

    def test03(self):
        self.assertTrue(date.Date(1, 13, 2000).equals(date.Date(1, 13, 2000)))
        d = date.Date(2, 27, 2015)
        d.tomorrow()
        self.assertTrue(d.equals(date.Date(2, 28, 2015)))
        d.yesterday()
        self.assertTrue(d.equals(date.Date(2, 27, 2015)))
        self.assertFalse(d.equals(date.Date(2, 27, 2016)))
        self.assertFalse(d.equals(date.Date(2, 28, 2015)))
        self.assertFalse(d.equals(date.Date(3, 27, 2015)))

    def test04(self):
        d = date.Date(1, 1, 2014)
        c = d.copy()
        self.assertTrue(d.equals(c))
        d.yesterday()
        c.yesterday()
        self.assertTrue(c.equals(d))

    def test05(self):
        # Reassign stdout for the duration of the test.
        saved_stdout = sys.stdout
        try:
            sys.stdout = StringIO()
            d = date.Date(2, 26, 1980)
            d.addNDays(5)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '02/26/1980\n02/27/1980\n02/28/1980\n02/29/1980\n03/01/1980\n03/02/1980')
            self.assertEqual(str(d), '03/02/1980')
            sys.stdout.close()

            sys.stdout = StringIO()
            d = date.Date(12, 29, 2012)
            d.addNDays(7)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '12/29/2012\n12/30/2012\n12/31/2012\n01/01/2013\n01/02/2013\n01/03/2013\n01/04/2013\n01/05/2013')
            self.assertEqual(str(d), '01/05/2013')
            sys.stdout.close()
        finally:
            sys.stdout.close()
            sys.stdout = saved_stdout

    def test06(self):
        # Reassign stdout for the duration of the test.
        saved_stdout = sys.stdout
        try:
            sys.stdout = StringIO()
            d = date.Date(3, 2, 1990)
            d.subNDays(5)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '03/02/1990\n03/01/1990\n02/28/1990\n02/27/1990\n02/26/1990\n02/25/1990')
            self.assertEqual(str(d), '02/25/1990')
            sys.stdout.close()

            sys.stdout = StringIO()
            d = date.Date(1, 5, 2015)
            d.subNDays(7)
            self.assertEqual(
                sys.stdout.getvalue().strip(),
                '01/05/2015\n01/04/2015\n01/03/2015\n01/02/2015\n01/01/2015\n12/31/2014\n12/30/2014\n12/29/2014')
            self.assertEqual(str(d), '12/29/2014')
            sys.stdout.close()
        finally:
            sys.stdout.close()
            sys.stdout = saved_stdout

    def test07(self):
        self.assertTrue(date.Date(1, 5, 2015).isBefore(date.Date(1, 5, 2016)))
        self.assertTrue(date.Date(1, 5, 2015).isBefore(date.Date(1, 6, 2015)))
        self.assertTrue(date.Date(1, 5, 2015).isBefore(date.Date(2, 5, 2015)))
        self.assertFalse(date.Date(1, 5, 2015).isBefore(date.Date(1, 4, 2015)))
        self.assertFalse(date.Date(1, 5, 2015).isBefore(date.Date(12, 31, 2014)))
        self.assertFalse(date.Date(1, 5, 2015).isBefore(date.Date(1, 5, 2014)))
        self.assertFalse(date.Date(1, 5, 2015).isBefore(date.Date(1, 5, 2015)))

    def test08(self):
        self.assertTrue(date.Date(1, 5, 2015).isAfter(date.Date(1, 4, 2015)))
        self.assertTrue(date.Date(1, 5, 2015).isAfter(date.Date(12, 31, 2014)))
        self.assertTrue(date.Date(1, 5, 2015).isAfter(date.Date(1, 5, 2014)))
        self.assertFalse(date.Date(1, 5, 2015).isAfter(date.Date(1, 5, 2016)))
        self.assertFalse(date.Date(1, 5, 2015).isAfter(date.Date(1, 6, 2015)))
        self.assertFalse(date.Date(1, 5, 2015).isAfter(date.Date(2, 5, 2015)))
        self.assertFalse(date.Date(1, 5, 2015).isAfter(date.Date(1, 5, 2015)))

    def test09(self):
        d1 = date.Date(11, 9, 2011)
        d2 = date.Date(12, 16, 2011)
        self.assertEqual(d2.diff(d1), 37)
        self.assertEqual(d1.diff(d2), -37)
        d1 = date.Date(11, 9, 2011)
        d2 = date.Date(5, 18, 2012)
        self.assertEqual(d2.diff(d1), 191)
        self.assertEqual(d1.diff(d2), -191)
        d1 = date.Date(11, 9, 2011)
        self.assertEqual(d1.diff(date.Date(1, 1, 1899)), 41219)
        self.assertEqual(d1.diff(date.Date(1, 1, 2101)), -32560)

    def test10(self):
        self.assertEqual(date.Date(12, 7, 1941).dow(), 'Sunday')
        self.assertEqual(date.Date(10, 28, 1929).dow(), 'Monday')
        self.assertEqual(date.Date(10, 19, 1987).dow(), 'Monday')
        self.assertEqual(date.Date(1, 1, 2100).dow(), 'Friday')
        self.assertEqual(date.Date(2, 26, 1979).dow(), 'Monday')
        self.assertEqual(date.Date(10, 12, 2013).dow(), 'Saturday')
        self.assertEqual(date.Date(2, 11, 2015).dow(), 'Wednesday')
        self.assertEqual(date.Date(5, 19, 2015).dow(), 'Tuesday')
        self.assertEqual(date.Date(5, 14, 2015).dow(), 'Thursday')

if __name__ == "__main__":
    unittest.main()
