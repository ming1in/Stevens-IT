import unittest
import lab1

class Test(unittest.TestCase):    
    def testInverse1(self):        
        self.assertAlmostEqual(lab1.inverse(1), 1, 6)    
    def testInverse2(self):        
        self.assertAlmostEqual(lab1.inverse(2), 0.5, 6)    
    def testInverse3(self):        
        self.assertAlmostEqual(lab1.inverse(3), 0.3333333333333333, 6)    
    def testInverse4(self):        
        self.assertAlmostEqual(lab1.inverse(-3), -0.3333333333333333, 6)    
    def testE1(self):        
        self.assertAlmostEqual(lab1.e(1), 2, 6)    
    def testE2(self):        
        self.assertAlmostEqual(lab1.e(2), 2.5, 6)    
    def testE3(self):        
        self.assertAlmostEqual(lab1.e(10), 2.718281801146385, 10)    
    def testE4(self):        
        self.assertAlmostEqual(lab1.e(100), 2.7182818284590455, 10)    
    def testError1(self):        
        self.assertAlmostEqual(lab1.error(1), 0.7182818284590451, 10)    
    def testError2(self):        
        self.assertAlmostEqual(lab1.error(2), 0.2182818284590451, 10)    
    def testError3(self):        
        self.assertAlmostEqual(lab1.error(10), 2.7312660133560485e-08, 10)    
    def testError4(self):        
        self.assertAlmostEqual(lab1.error(100), 4.440892098500626e-16, 10)

if __name__ == "__main__":    
    unittest.main()